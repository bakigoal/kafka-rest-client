#!/usr/bin/env bash

DOCKER_IMAGE=bakigoal/kafka-rest-client:0.0.1
echo removing docker image: "$DOCKER_IMAGE"
docker image rm -f bakigoal/"$DOCKER_IMAGE"

echo Building docker "$DOCKER_IMAGE"
./gradlew bootBuildImage --imageName "$DOCKER_IMAGE" && docker push "$DOCKER_IMAGE"
