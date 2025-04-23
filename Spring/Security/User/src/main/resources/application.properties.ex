spring.application.name=User
server.servlet.contextPath=/spring-demo
spring.datasource.url= jdbc:mysql://localhost:3306/db_name?useSSL=false
spring.datasource.username= username
spring.datasource.password= password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

#
spring.jpa.hibernate.ddl-auto=none
spring.liquibase.change-log=classpath:sql/db.changelog-master.xml