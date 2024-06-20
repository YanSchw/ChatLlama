# Use an official Maven image to build the application
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

# Copy the pom.xml file and download dependencies
COPY pom.xml /app/
RUN mvn dependency:go-offline

# Copy the source code into the container
COPY src /app/src

RUN mvn clean package -P production

# Use a base image with only JDK to run the application
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app/spring-app.jar

EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/spring-app.jar"]
