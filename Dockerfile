FROM adoptopenjdk/openjdk17:jre-17.0.3_8-slim

ENV DATABASE=${env.DATABASE}
ENV DATABASE_USER=${env.DATABASE_USER}
ENV DATABASE_PASSWORD=${env.DATABASE_PASSWORD}

RUN apt-get update
RUN apt-get install -y maven

COPY . .

RUN mvn clean test
RUN ./gradlew bootJar --no-daemon

FROM adoptopenjdk/openjdk17:jre-17.0.3_8-slim

EXPOSE 8080

COPY --from=build /build/libs/demo-1.jar nexGenRent.jar

CMD ["java", "-jar", "nexGenRent.jar"]
