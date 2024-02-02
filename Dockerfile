# Multi-stage Dockerfile

# Stage 1: Build Stage
FROM adoptopenjdk/openjdk17:jre-17.0.3_8-slim AS build

# Set environment variables
ENV DATABASE=${env.DATABASE}
ENV DATABASE_USER=${env.DATABASE_USER}
ENV DATABASE_PASSWORD=${env.DATABASE_PASSWORD}

# Update and install Maven
RUN apt-get update
RUN apt-get install -y maven

# Copy the application files
COPY . .

# Build the application
RUN mvn clean package

# Stage 2: Run Stage
FROM adoptopenjdk/openjdk17:jre-17.0.3_8-slim

# Expose port
EXPOSE 8080

# Copy only necessary files from the build stage
COPY --from=build /target/nexGenCarRental-0.0.1-SNAPSHOT.jar nexGenRent.jar

# Run the application
CMD ["java", "-jar", "nexGenRent.jar"]
