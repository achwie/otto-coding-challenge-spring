#!/bin/bash
#
# Runs the Otto Challenge Spring App (of course it must have been built before). 
#

docker run \
        -p 8080:8080 \
        achwie/otto-challenge-spring:0.1
 