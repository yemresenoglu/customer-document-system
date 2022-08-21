# Customer Document System

### Summary

The assessment consists of an API to be used for system user can add and view customers and files.
There are customers at the base of the system, each customer can have more than one file.

## Used Technologies & Arhitecture & Methods:

• Monolithic Architecture 
• Java 17
• Maven
• Spring Boot
• Spring Data JPA
• Spring Security
• Restful API
• JUnit
• PostgreSQL 14
• Docker


## Fuctions
•  Customer Manangement
•  Document Management
•  User Registration Management

## API Endpoints
•  */customers
•  */documents
•  */users
•  */auth



## GET
• /customers/all
• /documents/all


## POST

• /customers/save
• /documents//{idNumber}/upload
• /users/save
• /auth/login
• /auth/register
• /auth/refresh


## PUT

• /customer/{idNumber}
• /customer/{idNumber}/updateInfo
• /documents/{documentNumber}

## DELETE
• /customers/{idNumber}
• /documents/{documentNumber}

JUnit test coverage is 100% as well as integration tests are available.


### Run & Build


• Docker Compose

For docker compose usage, docker images already push to docker.io
You just need to run `docker-compose up` command









