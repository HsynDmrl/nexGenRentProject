# Stage 1: Build Stage
FROM adoptopenjdk/openjdk17:jre-17.0.3_8-slim AS build

# Set environment variables
ARG DATABASE
ARG DATABASE_USER
ARG DATABASE_PASSWORD

ENV DATABASE=${DATABASE}
ENV DATABASE_USER=${DATABASE_USER}
ENV DATABASE_PASSWORD=${DATABASE_PASSWORD}

# Copy the Maven project
COPY . /app
WORKDIR /app

# Install Maven and build the application
RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean package -DskipTests

# Stage 2: Run Stage
FROM adoptopenjdk/openjdk17:jre-17.0.3_8-slim

# Expose port
EXPOSE 8080

# Set the working directory
WORKDIR /app

# Copy only necessary files from the build stage
COPY --from=build /app/target/nexGenCarRental-0.0.1-SNAPSHOT.jar nexGenRent.jar

# Run the application
CMD ["java", "-jar", "nexGenRent.jar"]
