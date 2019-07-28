#!/bin/bash
#
# Builds the Docker container that contains the application
#

docker build \
        -t achwie/otto-challenge-spring:0.1 \
        -f Dockerfile \
        ../../target