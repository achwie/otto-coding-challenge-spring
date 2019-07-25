#!/bin/bash
#
# Runs the Otto Challenge Spring App from the according Docker volume (of course
# it must have been built beforehand). 
#

docker run \
        -v $(pwd)/../..:/media/hostfs \
        -v otto-challenge-spring-app:/media/otto-challenge-spring-app \
        -p 8080:8080 \
        openjdk:12-alpine \
        java -jar /media/otto-challenge-spring-app/otto-challenge-spring-0.0.1-SNAPSHOT.jar
 