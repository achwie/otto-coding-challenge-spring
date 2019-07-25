FROM openjdk:12-alpine

LABEL author="Achim Wiedemann"
LABEL description="Image with OpenJDK and Maven to run builds"

ADD settings.xml /opt/

RUN wget -P /tmp/ https://www-eu.apache.org/dist/maven/maven-3/3.6.1/binaries/apache-maven-3.6.1-bin.tar.gz && \
        tar xzvf /tmp/apache-maven-3.6.1-bin.tar.gz -C /opt/ && \
        rm /tmp/apache-maven-3.6.1-bin.tar.gz
