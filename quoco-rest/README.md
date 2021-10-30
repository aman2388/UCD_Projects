# Lab 5 Rest
This project is run using Java 8. 

I have set unique port numbers for each services. The numbers are as follows:
 - Auldfellas: 8081
 - DodgyDrivers: 8082
 - Girlpower: 8083
 - Broker: 8080

The brokers implementation was similar to the services task. However, in the application.properties
file, I created an ArrayList that stores the given arguments by the user and 
then create a Bean to return such lists.

To run the application on docker, follow these three steps on the cmd:
1. $ docker-compose build
2. $ docker-compose up
3. In a new cmd window type the following $ mvn compile spring-boot:run -pl client

To run the application on the localhost, follow these steps:
1. $ mvn compile spring-boot:run -pl "services"
2. $ mvn compile spring-boot:run -pl broker -D spring-boot.run.arguments="localhost:8081 localhost:8082 localhost:8083"
3. $ mvn compile spring-boot:run -pl client