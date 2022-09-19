# EPTA Assignment

    |-- com.epta.assigment
    |   |-- application
    |   |   |-- mediator
    |   |   |-- [domain]
    |   |   |   |-- command
    |   |   |   |-- dto
    |   |   |   |-- handler
    |   |   |   |-- query
    |   |-- domain
    |   |-- infrastructure
    |   |   |-- controller
    |   |   |-- exception
    |   |   |-- repository

## Requirements
You will need:

    Java JDK 11
    Maven 3.1.1 or higher
    Git

## Dependencies
There are a number of third-party dependencies used in the project. Browse the Maven pom.xml file for details of
libraries and versions used.

## Building the project
Clone the project and use Maven to build the server

`$ mvn clean install` 

## Database
We are using an in-memory database H2

URL: `http://localhost:8082/h2-console` <br>
User: `sa` <br>
Password: `Cambiar123*`

## Swagger 
URL: `http://localhost:8082/swagger-ui/index.html`

