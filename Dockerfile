FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8080

COPY /target/AuthorizationService-0.0.1-SNAPSHOT.jar AuthApp.jar

CMD ["java", "-jar", "AuthApp.jar"]
