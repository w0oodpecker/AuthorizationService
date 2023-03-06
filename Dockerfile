FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8080

COPY /out/artifacts/AuthorizationService_jar/AuthorizationService.jar AuthApp.jar

CMD ["java", "-jar", "AuthApp.jar"]
