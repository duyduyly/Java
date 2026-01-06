# Step Function

- Break a complex operation into small, independent steps that execute in order.
- The Step Function pattern (often called Stepwise / Pipeline pattern) is a behavioral design pattern used to organize a process as a sequence of clear, ordered steps, where each step performs one responsibility and passes its result to the next step.
- When:
  - You have multi-stage processing
  - Each stage can be changed, added, removed, or reordered
  - You want readable, testable, and maintainable workflows
- Step:
  - Has one responsibility
  - Knows nothing about other steps
  - Operates on a shared context / state

  
### Example Service
- Withdraw money from a bank account
- Function:
  - Validate request
  - Validate And Get account 
    - get account from repository
    - check account exists
    - check pin is correct
    - check account is active
    - check withdrawal limit within allowed range
    - check Balance is sufficient
    
  - Process withdrawal
    - deduct amount from balance
    - deduct Fee from balance
  - Notify SMS Notification
  - Notify Email Notification


### Single Threaded Example
- Loop and step through each step sequentially in a single thread.
- Basic and straightforward implementation.
- But will slow.

#### Flow 
```text
step 1(Validate Request)
    |
step 2(Get Account)
    |
step 3(Validate And Get Account)
    |
step 4(Process Withdrawal)
    |
step 5(Notify SMS Notification)
    |
step 6(Notify Email Notification)
    |
   end  
```


### Multiple Threaded Example
- Can can test Here:
  - [WithdrawalService.java](service/WithdrawalService.java)
- Need understand steps single threaded first.
- And then know which steps can run in parallel.

#### Flow 
```text
step 1(Validate Request)
         |
step 2(Get Account)
         |
step 3(Validate And Get Account)
         |
step 4(Process Withdrawal)
         |
step 5(Console Notification)
         |     
Push to RabbitMQ Queue(withdraw.queue)
         |                     
step 6(SMS Notification)        
         |   
Push to RabbitMQ Queue(withdraw.queue) 
         |    
        end


-------------------------------- 
                         
                         
                 RabbitMQ Listener(withdraw.queue)                                             
                           /        \
handle SMS Notification Message   handle Notice Email Notification Message
```


### Use RabbitMQ to implement parallel steps
- don't support retry (just requeue), so you must handle retry timeout and retryCount if you want.

#### RabbitMQ Status:
- Ack (in code basicAck) (Success)
```java
basicAck(long l, boolean b);
//@param deliveryTag(l): the message's delivery tag
//@param multiple(b): false to ack a single message; true to ack all messages
```
- Nack (in code basicNack) (fail)
```java
basicNack(long l, boolean b, boolean c);
//@param deliveryTag(l): the message's delivery tag
//@param multiple(b): false to nack a single message; true to nack all messages
//@param requeue(c): true to requeue the message; false to discard it
```

- Reject (in code basicReject) (fail)
```java
basicReject(long l, boolean b);
//@param deliveryTag(l): the message's delivery tag
//@param requeue(b): true to requeue the message; false to discard it
```

- [RabbitMQUtils.java](utils/RabbitMQUtils.java)
#### Code

```maven
    <dependencies>
        <!-- https://mvnrepository.com/artifact/com.rabbitmq/amqp-client -->
        <dependency>
            <groupId>com.rabbitmq</groupId>
            <artifactId>amqp-client</artifactId>
            <version>5.25.0</version>
        </dependency>

        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.4.14</version>
        </dependency>
```

```java
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public class RabbitMQUtils {

  private final String globalQueueName;
  private final Connection connection;
  private final Channel channel;

  public RabbitMQUtils(String queueName) throws IOException, TimeoutException {
    this.globalQueueName = queueName;
    this.connection = RabbitConfig.factory().newConnection();
    this.channel = connection.createChannel();
    channel.basicQos(1);
  }

  public void sendMessage(String message) {
    try {
      channel.queueDeclare(
              globalQueueName,
              true,   // durable
              false,
              false,
              null
      );

      channel.basicPublish(
              "",
              globalQueueName,
              null,
              message.getBytes()
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  //retry in memory
  public void receiveMessage(Consumer<String> handleMessage) throws IOException {
    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      long tag = delivery.getEnvelope().getDeliveryTag();
      String message = new String(delivery.getBody());

      int maxRetry = 3;
      int attempt = 0;
      boolean success = false;

      while (attempt < maxRetry && !success) {
        try {
          attempt++;
          handleMessage.accept(message); //outer message handler
          success = true;
        } catch (Exception e) {
          System.err.println("Retry " + attempt + " failed: " + e.getMessage());
          try {
            Thread.sleep(5000); //wait before retry
          } catch (InterruptedException ignored) {
          }
        }
      }

      if (success) {
        channel.basicAck(tag, false);
      } else {
        //reject message after max retries
        channel.basicReject(tag, false);
      }
    };

    channel.basicConsume(
            globalQueueName,
            false,
            deliverCallback,
            consumerTag -> {
            }
    );
  }

  //header retry
    public void receiveMessage2(Consumer<String> handleMessage) throws IOException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            long tag = delivery.getEnvelope().getDeliveryTag();
            String message = new String(delivery.getBody());

            Map<String, Object> headers = delivery.getProperties().getHeaders();
            int retry = headers == null ? 0 : (int) headers.getOrDefault("x-retry", 0);

            if (retry >= 3) {
                channel.basicReject(tag, false);
                return;
            }

            try {
                handleMessage.accept(message);
                channel.basicAck(tag, false);
            } catch (Exception e) {
                // publish lại message với retry + 1
                AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                        .headers(Map.of("x-retry", retry + 1))
                        .build();

                channel.basicPublish("", globalQueueName, props, delivery.getBody());
            }
        };

        channel.basicConsume(
                globalQueueName,
                false,
                deliverCallback,
                consumerTag -> {
                }
        );
    }


  public void closeRabbit() throws IOException, TimeoutException {
    channel.close();
    connection.close();
  }

  private static class RabbitConfig {
    public static ConnectionFactory factory() {
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("localhost");
      factory.setPort(5672);
      factory.setUsername("guest");
      factory.setPassword("guest");
      factory.setVirtualHost("/");
      return factory;
    }
  }
}
```

#### Docker RabbitMQ Setup
- Download and start RabbitMQ server from https://www.rabbitmq.com/download.html
```text
docker run -d --hostname my-rabbit --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

- start RabbitMQ server on docker
```text
docker run -d -p 15672:15672 rabbitmq:3-management

--start 2 port--
docker run -d -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

