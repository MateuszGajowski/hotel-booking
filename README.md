# hotel-booking

Simple, sample app for hotel room booking system.

This app is mainly configured for only development environment.
This app is using H2 memory database so no need to setup this.
Database schema is created via Spring (schema.sql, data.sql) [this solution is only good for simple DEV case]

To access H2 GUI: http://localhost:8080/h2

### How to run
mvn spring-boot:run

### How to start acceptance tests
mvn clean verify -P integration-test
