FROM maven:3.8.3-jdk-11-slim AS build
WORKDIR /build
COPY . /build
RUN mvn clean package -Dsnyk.skip

FROM openjdk:11-jre-slim AS app
WORKDIR /app
COPY --from=build /build/target/segments-explorer-api-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "segments-explorer-api-0.0.1-SNAPSHOT.jar"]