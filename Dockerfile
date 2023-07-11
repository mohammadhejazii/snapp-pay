FROM openjdk:17.0.2-slim
#FROM maven:alpine
MAINTAINER hejazi

# image layer
#WORKDIR /app
#ADD pom.xml /app
#RUN mvn verify clean --fail-never

# Image layer: with the application
#COPY . /app
#RUN mvn -v
#RUN mvn clean install -DskipTests

ADD wallet-application/target /app
WORKDIR /app
ENTRYPOINT ["java", "-Dspring.profiles.active=develop", "-Xms200m", "-Xmx1024m", "-jar", "wallet-application.jar"]


