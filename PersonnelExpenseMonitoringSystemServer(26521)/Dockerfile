# Use Maven to build the application
FROM maven:3.8.4-openjdk-8-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use OpenJDK to run the application
FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=build /app/target/PersonnelExpenseMonitoringSystemServer-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

# RMI Port
EXPOSE 5000

# Start the server
ENTRYPOINT ["java", "-jar", "app.jar"]
