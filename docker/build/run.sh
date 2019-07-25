#!/bin/bash
#
# Runs a maven build of the project inside a Docker container. 
#
# Mounts two volumes to actually build the project:
#  * The dir with the source code from the docker host so maven can build
#  * A docker volume for the maven repo to speed up subsequent builds
#

docker run \
        -v $(pwd)/../..:/media/hostfs \
        -v m2repo:/media/m2repo \
        achwie/otto-challenge-spring:0.1 \
        /opt/apache-maven-3.6.1/bin/mvn -f /media/hostfs/ -s /opt/settings.xml package

docker run \
        -v $(pwd)/../..:/media/hostfs \
        -v otto-challenge-spring-app:/media/otto-challenge-spring-app \
        achwie/otto-challenge-spring:0.1 \
        cp /media/hostfs/target/otto-challenge-spring-0.0.1-SNAPSHOT.jar /media/otto-challenge-spring-app/
 