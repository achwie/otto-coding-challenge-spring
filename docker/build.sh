#!/bin/bash
#
# Builds the Spring App Docker container
#


pushd build
./build.sh && ./run.sh
popd
