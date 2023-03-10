### configuration (Test DB)

**application.yml**

```yaml
group:
  email: email

spring:
  datasource:
    driver-class-name: org.h2.Driver
    url: "jdbc:h2:mem:test"
  jpa:
    hibernate:
      ddl-auto: create
      
# Swagger Config
  springdoc:
    swagger-ui:
      path: swagger.html
      operations-sorter: method
    #  version: v1
    paths-to-match: /api/**
    
# MailSender Config
  mail:
    host: smtp.naver.com
    port: 587
    username: username
    password: password
    properties:
      mail:
        smtp:
          auth: true
```
### Execute
```shell
$ ./gradlew clean build -x test
$ cd build/libs
$ java -jar *.jar
```