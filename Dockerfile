# Docker multi-stage build
 
# 1. Building the App with Maven
FROM maven:3.8.3-openjdk-17
 
ADD . /cxfbootsimple
WORKDIR /cxfbootsimple
 
# Just echo so we can see, if everything is there :)
RUN ls -l
 
# Run Maven build
RUN mvn clean install
 
 
# 2. Just using the build artifact and then removing the build-container
FROM openjdk:17-jdk-slim
 
MAINTAINER Jonas Hecht

# set work directory
WORKDIR /usr/src/prestation

# Add Spring Boot app.jar to Container
COPY target/prestation-api.jar .
 
# Fire up our Spring Boot app by default
CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]