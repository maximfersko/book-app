FROM maven:3.8.6-amazoncorretto-8 AS builder

WORKDIR /build

COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests -q

FROM amazoncorretto:8-alpine

WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
