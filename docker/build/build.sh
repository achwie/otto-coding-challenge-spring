#!/bin/bash
#
# Builds the Docker container that will be used to build the project via Maven.
#

docker build \
        -t achwie/otto-challenge-spring:0.1 \
        -f Dockerfile \
        context