Implementation of the [Otto Cloud Software Engineer Code Challenge](https://github.com/aacml/Recruiting/wiki/Cloud-Software-Engineer-Code-Challenge) with Java and Spring Boot.

# Docker environment

## Building

The project comes with a special Docker build container which contains a Maven installation in case you don't have one installed. In order to use the Docker container:

```
$ cd docker
$ build.sh
```

Depending on how you set up Docker you might have to run the `build.sh` and `run.sh` scripts with `sudo`.

The `build` directory contains a Dockerfile to build a container that will mount the local FS into the container and use that mount to build the project via the container's Maven installation. The built executable JAR is saved into a Docker volume.

## Running

To run the Spring App you can also use Docker. After the app has been built (see steps above) it can be executed:  

```
$ cd docker
$ build.sh
```

The `runtime` directory contains a script to run the Spring app from the Docker volume created during the previous build step.  