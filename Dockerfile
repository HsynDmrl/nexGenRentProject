FROM ubuntu:latest AS build

ENV DATABASE=${env.DATABASE}
ENV DATABASE_USER=${env.DATABASE_USER}
ENV PASSWORD=${env.DATABASE_PASSWORD}

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN mvn clean test
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jre-slim

EXPOSE 8080

COPY --from=build /build/libs/demo-1.jar nexGenRent.jar

CMD ["java", "-jar", "nexGenRent.jar"]
