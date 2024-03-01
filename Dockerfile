FROM maven:3.9.6-eclipse-temurin-21-alpine
COPY target/wordle-0.0.1-SNAPSHOT.jar wordle-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/wordle-0.0.1-SNAPSHOT.jar"]