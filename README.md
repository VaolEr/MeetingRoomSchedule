## Meeting Room Schedule app

This app presents the Schedule of Meeting Room Events.

### Stack:
    Java 8, Spring Boot, Spring Security, Spring Data JPA, Hybernate, PostgrteSQL, Liquibase, Thymeleaf + Bootstrap 4

### System requirements:
    Java 8, server port 8080 (by default, but can be changed), PosgreSQL server on port 5432.

### Prepare to use:
1. Download all project files to your PC using git or as archive;
2. In `application.properties` database have property `createDatabaseIfNotExist=true`. In theory this will create database in your postgres server.
3. In `application.properties` change `DATASOURCE_USERNAME` AND  `DATASOURCE_PASSWORD` for your own values.
4. Just run project from IDE and go to `localhost:8080` in browser. If this port is busy you can change it in `application.properties` field `server.port`.
5. After first running test data will be populated in database by Liquibase.  Week 26 is start point of test data. 

### Getting access to app:
- Users available for tests:

  | Login | Password | 
  | :---: | :---: | 
  |`Admin`|`admin`|
  |`User`| `user`|
  |`JohnDoe`| `JohnDoe`|

### Known issues:

1. For correct work return buttons you need to press one of week number after login;
2. It is bad to have a lot of pages in project. I tried bootstrap Modal, but nowadays don't know how to put data from controller to it;
3. It is not very informative that my events are displaying only in its start time, not in their full time period.
