# syntax=docker/dockerfile:1
##
## Build stage
##
#FROM maven:3.6.0-jdk-11-slim AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean package
#
##
## Package stage
##
#FROM adoptopenjdk:16-jdk-hotspot
#COPY --from=build /home/app/target/meetingroomschedule-0.0.1-SNAPSHOT.jar /usr/local/lib/meetingroomschedule-0.0.1-SNAPSHOT.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/usr/local/lib/meetingroomschedule-0.0.1-SNAPSHOT.jar"]
FROM adoptopenjdk:16-jdk-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]