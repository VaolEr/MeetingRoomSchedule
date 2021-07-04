## Meeting Room Schedule app

This app was designed for creating event's schedule for meeting room.



### Stack:
    Java 8, Spring Boot, Spring Security, Spring Data JPA, Hybernate, PostgrteSQL, Liquibase, Thymeleaf + Bootstrap

### System requirements:
    Java 8

### Prepare to use:
1. Download all project files to your PC;
2. Use mvn to prepare project: clean install or clean package;
3. If you are using IDE, then just run project. 

### App endpoints info:
- App endpoints:
    - `/login` - returns the login page;
    - `/timetable` - returns the schedule;
    - `/timetable/events/{id}` - returns the event info by event id;

### Getting access to app:
- Users available for tests:

  | Login | Password | 
  | :---: | :---: | 
  |`Admin`|`admin`|
  |`User`| `user`|
  |`JohnDoe`| `JohnDoe`|

