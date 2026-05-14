# Use Maven to build the application
FROM maven:3.8.4-openjdk-8-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use OpenJDK to run the application (Headless mode for demo)
FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=build /app/target/PersonnelExpenseMonitoringSystemClient-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

# Since this is a Swing App, it requires a GUI display to run.
# In a Docker environment, we package it for portability.
CMD ["java", "-Djava.awt.headless=true", "-jar", "app.jar"]
