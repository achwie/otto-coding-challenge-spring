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



# My approach

The obvious thing is that we need to parse a tree structure and transform it. I decided to use the visitor pattern here, since it's a good fit for this kind of problem (see package `achwie.challenge.otto.core.node`). While the parent filter was quite straight forward, the sorting by field names was more tricky. I wanted this to be flexible and not hardcode field names. I also wanted to make it work with numbers and strings alike so I had to get a little creative here (see `PropertyComparator`). Since I wanted all of this to be testable with simple unit tests I started coding without any container environment and only added the spring-stuff at the very end which worked very well.