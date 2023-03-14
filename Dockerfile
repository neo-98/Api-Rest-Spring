FROM amazoncorretto:11-alpine-jdk
VOLUME /tmp
EXPOSE 8080
COPY target/*.jar democrud-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app.jar"]