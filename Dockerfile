FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/urlshortener-0.0.1-SNAPSHOT.jar urlshortener-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/urlshortener-0.0.1-SNAPSHOT.jar"]