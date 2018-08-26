# devops-docker
Continuous integration and deployment example with docker

## Pre-Reqs

- docker 17 +
- docker-compose 1.16 +

## Generate maven image

In the root directory execute the following command:
```
docker build -t maven:alpine maven/.
```
This command will generate the maven image that will be use to build the source code.

You can check if the image has been created executing the following command
```
docker images
```
## Build the project

### Execute unit test
In the project root's directory execute the following commands:
```
docker-compose -f docker-ci.yml run --rm maven clean test
```
## Build the project

### Generate jar
In the project root's directory execute the following commands:
```
docker-compose -f docker-ci.yml run --rm maven clean package
```

## Package the application in docker container

Execure the following command:
```
docker-compose build
```

This command will generate a docker image called rauccapuclla/devops-docker

## Run the application using docker-compose

Just execute the following command:
```
docker-compose up -d
```
### Test if the application is running
```
curl http://localhost:8080
```
The response should be "Applicacion de laboratorio V1"

