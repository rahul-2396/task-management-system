FROM openjdk:21-jdk-alpine

WORKDIR /app

COPY out/artifacts/taskmanagement_jar/taskmanagement.jar /app/taskmanagement.jar

ENTRYPOINT ["java", "-jar", "/app/taskmanagement.jar"]


