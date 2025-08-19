# Module
- [Modules With Maven](#modules-with-maven)
- [Modules With Java](#modules-in-java)

## Modules With Maven
- The First I have Maven Project with 2 module 
- `Common` have NotificationService
- `OrderProcess` have OrderProcessService and after process service method will call notification to notice for client
```text
- Parent Module (Parent Module)
    - Common (Child Module)
    - OrderProcess (Child Module)
```

#
### 1. Create Order Project
![Create Order Project.png](../../../../resources/modules/maven/Create%20Order%20Project.png)

#
### 2. Create Two Modules
- `new` -> `Module..` and
![Create Order Process Modules.png](../../../../resources/modules/maven/Create%20Order%20Process%20Modules.png)

#
#### Project Structure
![Project Structure.png](../../../../resources/modules/maven/Project%20Structure.png)

#
### 3. POM Setting
- Order Process Modules Must Call Notification Service Should we must add dependency of Common To use that service

**Order(Parent) POM:** [pom.xml](modules_with_maven/Order/pom.xml)
```Java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>Order</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>
    <modules>
        <module>Common</module>
        <module>OrderProcess</module>
    </modules>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

</project>
```

**Common POM:** [pom.xml](modules_with_maven/Order/Common/pom.xml)
```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
        <groupId>org.example</groupId>
        <artifactId>common</artifactId>
        <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

</project>
```

**Order Process POM:** [pom.xml](modules_with_maven/Order/OrderProcess/pom.xml)
```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

        <groupId>org.example</groupId>
        <artifactId>order_process</artifactId>
        <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>common</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

</project>
```
[NotificationService.java](modules_with_maven/Order/Common/src/main/java/org/example/service/NotificationService.java)
```java
package org.example.service;

public class NotificationService {

    public void push(){
        System.out.println("Push Notification");
    }
}
```
[OrderProcessService.java](../../../../../../../../JavaScore/Order/OrderProcess/src/main/java/org/example/service/OrderProcessService.java)
```java
package org.example.service;

public class OrderProcessService {

    private static final NotificationService notificationService = new NotificationService();
    public void process(){
        //implement Process Order
        System.out.println("Process Order");
        notificationService.push();
    }
}
```
[Main.java](../../../../../../../../JavaScore/Order/OrderProcess/src/main/java/org/example/Main.java)
```java
package org.example;

import org.example.service.OrderProcessService;

public class Main {

    private static final OrderProcessService orderProcessService = new OrderProcessService();
    public static void main(String[] args) {
        orderProcessService.process();
    }
}
```

**Result:**
```text
Process Order
Push Notification
```




#
#### Complain
- You can see Parent Project will `modules` tag to `manage` Children module
``` java
    <modules>
        <module>Common</module>
        <module>OrderProcess</module>
    </modules>
```

- and Each Child Module will have  `groupId`, `artifactId`(this tags is unique) and `version`
- If two modules same `artifactId`, Maven can't build (Error)
```java
<groupId>org.example</groupId>
<artifactId>order_process</artifactId>
<version>1.0-SNAPSHOT</version>
```

```java
<groupId>org.example</groupId>
<artifactId>common</artifactId>
<version>1.0-SNAPSHOT</version>
```

- And Finally Add `Common Module` to OrderProcess
```java
<dependency>
    <groupId>org.example</groupId> <!--GroupId config in common module -->
    <artifactId>common</artifactId> <!--artifactId config in common module -->
    <version>1.0-SNAPSHOT</version> <!--version tag -->
</dependency>
```



- And Maven `clean` and `install`, you can use service in Common Module

Example Here: [Order](modules_with_maven/Order) , [Main.java](modules_with_maven/Order/OrderProcess/src/main/java/org/example/Main.java)

--------------------
<br/>

## Modules in java