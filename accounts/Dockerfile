FROM openjdk:23-slim-bullseye

MAINTAINER Jim

#copy jar file to Docker Image
COPY target/accounts-1.0.0.jar accounts-1.0.0.jar

#execute the jar
ENTRYPOINT ["java", "-jar", "accounts-1.0.0.jar"]