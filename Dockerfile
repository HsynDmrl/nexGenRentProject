FROM adoptopenjdk/openjdk17:jre-17.0.3_8-slim

ENV DATABASE=${env.DATABASE}
ENV DATABASE_USER=${env.DATABASE_USER}
ENV DATABASE_PASSWORD=${env.DATABASE_PASSWORD}

RUN apt-get update
RUN apt-get install -y maven

COPY . .

RUN mvn clean package  # Maven ile JAR oluşturma

FROM adoptopenjdk/openjdk17:jre-17.0.3_8-slim

EXPOSE 8080

COPY --from=build /target/nexGenCarRental-0.0.1-SNAPSHOT.jar nexGenRent.jar  # JAR dosyasının konumu düzeltildi

CMD ["java", "-jar", "nexGenRent.jar"]
