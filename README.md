Implementation of the [Otto Cloud Software Engineer Code Challenge](https://github.com/aacml/Recruiting/wiki/Cloud-Software-Engineer-Code-Challenge) with Java and Spring Boot.

# Docker environment

## Building

The project comes with a special Docker build container which contains a Maven installation in case you don't have one installed. In order to use the Docker container:

```
$ cd docker
$ build.sh
```

Depending on how you set up Docker you might have to run the `build.sh` and `run.sh` scripts with `sudo`.

The `build` directory contains a Dockerfile to build a container that will mount the local FS into the container and use that mount to build the project via the container's Maven installation.

## Running

To run the Spring App you can also use Docker. After the app has been built (see steps above) it can be executed:  

```
$ cd docker
$ run.sh
```

The `runtime` directory contains a script to run the Spring app in a Docker container.  



# My approach

The obvious thing is that we need to parse a tree structure and transform it. I decided to use the visitor pattern here, since it's a good fit for this kind of problem (see package `achwie.challenge.otto.core.node`). While the parent filter was quite straight forward, the sorting by field names was more tricky. I wanted this to be flexible and not hardcode field names. I also wanted to make it work with numbers and strings alike so I had to get a little creative here (see `PropertyComparator`). Since I wanted all of this to be testable with simple unit tests I started coding without any container environment and only added the spring-stuff at the very end which worked very well.

For the Docker environment, the build environment (see `docker/build`) builds the project and stores an executable JAR in the host file system. The JAR will then be used to build the actual application container (see `docker/runtime`). Convenience script for building and running the Spring application are provided (`docker/build.sh` and `docker/run.sh`). The build environment uses a Docker volume in which it stores the local Maven repository to speed up subsequent builds.