FROM openjdk:alpine
VOLUME /tmp

COPY target/*.jar /app.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://app-mongo:27017/app-demo","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]