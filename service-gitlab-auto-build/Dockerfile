FROM openjdk:8-jdk-alpine

VOLUME /tmp

COPY  /target/service-gitlab-auto-build-1.0-SNAPSHOT.jar app.jar

ENV PORT 5000

EXPOSE $PORT

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=${PORT}","-jar","/app.jar"]
