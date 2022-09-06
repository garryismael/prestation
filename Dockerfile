FROM openjdk:17-alpine
ADD target/prestation-api.jar prestation-api.jar
ENTRYPOINT ["java", "-jar","prestation-api.jar"]
EXPOSE 8080