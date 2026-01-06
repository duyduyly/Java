package org.example.step_function.utils;

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
