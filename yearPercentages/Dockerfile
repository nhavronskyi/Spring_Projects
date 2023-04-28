FROM maven
RUN mvn clean package

FROM openjdk:17.0.2-jdk-slim-buster
COPY target/yearPercentages-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]