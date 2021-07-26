# Spring-Boot-CRUD-Application
Simple Spring Boot CRUD example with a user resource.

## Prerequisites
- Java 11
- docker
- mvn

## Installation
I've only tested it with Intellij and the Docker image below.
There are two profiles, the standard profile, which is also used for development, and one other for production, called prod.
### dev
- port 8000   
- database credentials:
  - user: root
  - password: root
- Swagger 
  - http://localhost:8000/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
- H2 console
  - http://localhost:8000/h2-console

### prod
The Swagger documentation and h2 Console are disabled for production.
- port 9000
- database credentials (env)
  - DB_USER
  - DB_PASSWORD  
    
## Database
H2 was used as the embedded database.  
Console:  
http://localhost:8000/h2-console

I used Flyway to migrate the table.

## Build Docker Image
To build a docker image follow the steps below.
``` $ mvn package ```  
``` $ docker build . -t app ```

## Testing
For testing I used JUnit and Karate.
The test coverage is created with jacoco.

``` mvn clean test jacoco:report ```

## Documentation
There is a Swagger documentation (dev profile), which is available after starting the application.  
http://localhost:8000/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
