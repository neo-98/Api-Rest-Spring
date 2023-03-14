FROM amazoncorretto:11-alpine-jdk
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/democrud-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]