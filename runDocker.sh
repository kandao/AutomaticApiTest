#!/bin/bash

# Build the WireMock docker image
docker build -t wiremock-server wireMockResource

# Lists all images that are dangling and has no pointer to it
docker images --filter dangling=true 

# Removes all those images.
docker rmi `docker images --filter dangling=true -q` 

# Run WireMock docker image
docker run -it --rm -p 8080:8080 wiremock-server