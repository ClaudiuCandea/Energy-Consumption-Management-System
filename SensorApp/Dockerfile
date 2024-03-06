FROM openjdk:17-jdk-alpine

COPY target/SensorApp-0.0.1-SNAPSHOT.jar app-1.0.0.jar
COPY src/main/resources/sensor.csv /app/sensor.csv

ENTRYPOINT ["java","-jar","app-1.0.0.jar"]