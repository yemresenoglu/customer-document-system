FROM openjdk:latest

COPY ./target/customer-document-system-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch customer-document-system-0.0.1-SNAPSHOT.jar'

ARG JAR_FILE=target/customer-document-system-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","customer-document-system-0.0.1-SNAPSHOT.jar"]