# tasks-rest-api-spring-boot
Technologies, used to run application:
 - Java 15
 - Maven 3.6.3
 - PostgreSQL 13
 
 # Steps to setup
 1. Clone this repository
 
 `git clone https://github.com/SemmSemm/tasks-rest-api-spring-boot.git`
 
 2. Configure PostgreSQL
 
 First of all, create new database named "tasks". Then, in application.properties change the spring datasource username and password to your Postgres auth data. In case of using different port for Postgres, change port in datasource url.
 
 3. Run the application
 
 Go into root directory of the project and execute following command
 
 `mvn spring-boot:run`
 
Alternative solution, is to package the application as JAR file and run

`mvn clean package`

and then

`java -jar target/demo-0.0.1-SNAPSHOT.jar`

Application will start on the default port 8080.

# Swagger UI

Application implements also API documentation, which you can access by following url

`http://localhost:8080/swagger-ui`

Screenshots of swagger API documentation

![alt text](https://github.com/SemmSemm/tasks-rest-api-spring-boot/blob/master/img/Swagger-API.PNG)

![alt_text](https://github.com/SemmSemm/tasks-rest-api-spring-boot/blob/master/img/SWAGGER-API-REQUEST.PNG)
