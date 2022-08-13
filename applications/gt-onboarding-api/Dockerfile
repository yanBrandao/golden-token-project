FROM maven:3.8.6-openjdk-18-slim as build

WORKDIR /build

COPY . .

## Comment this line for fast build
# RUN mvn clean install


FROM amazoncorretto:18-alpine-jdk as app

WORKDIR /app

COPY --from=build /build/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]