FROM eclipse-temurin:21
MAINTAINER neel
COPY target/website-1-0.0.1-SNAPSHOT.jar website-1.0.0.jar
ENTRYPOINT ["java","-jar","/website-1.0.0.jar"]