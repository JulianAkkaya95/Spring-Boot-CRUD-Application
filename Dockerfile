FROM openjdk:11
ARG JAR_FILE=target/Spring-Boot-CRUD-Application-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE 9000
ENTRYPOINT ["java","-jar","/app.jar"]