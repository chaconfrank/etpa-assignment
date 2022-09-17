# EPTA Assignment

    |-- com.epta.assigment
    |   |-- application
    |   |   |-- command
    |   |   |   |-- [domain command]
    |   |   |   |   |-- [use cases]
    |   |   |-- handler
    |   |   |   |-- [domain handler]
    |   |   |   |   |-- [use cases]
    |   |   |-- mediator
    |   |-- domain
    |   |-- infrastructure
    |   |   |-- repository
    |   |   |-- web

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