# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre

#RUN apk add --update fontconfig freetype

RUN apk --no-cache add msttcorefonts-installer fontconfig && update-ms-fonts && fc-cache -f

# Refer to Maven build -> finalName
ARG JAR_FILE=target/fair-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
#ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar","app.jar"]

ENTRYPOINT ["java",  "-jar","app.jar"]