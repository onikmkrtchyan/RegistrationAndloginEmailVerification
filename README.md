# Spring Boot Refresh Token with JWT example

Build JWT Refresh Token in the Java Spring Boot Application. You can know how to expire the JWT, then renew the Access Token with Refresh Token.


## Dependency
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<scope>runtime</scope>
</dependency>

<dependency>
	<groupId>io.jsonwebtoken</groupId>
	<artifactId>jjwt</artifactId>
	<version>0.9.1</version>
</dependency>
```

## Configure Spring Datasource, JPA, App properties
Open `src/main/resources/application.properties`

```properties
spring.datasource.url= jdbc:mysql://localhost:3306/testdb?useSSL=false
spring.datasource.username= root
spring.datasource.password= 123456

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto= update

# App Properties
onik.app.jwtSecret= bezKoderSecretKey
onik.app.jwtExpirationMs= 3600000
onik.app.jwtRefreshExpirationMs= 86400000
```

## Run Spring Boot application
```
mvn spring-boot:run
```
