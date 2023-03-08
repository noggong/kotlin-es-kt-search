FROM gradle:8.0.1 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:11-jre-slim-buster
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/demo-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/app/demo-0.0.1-SNAPSHOT.jar"]
