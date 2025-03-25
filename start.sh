#!/bin/bash

# Recompiling the Java JAR
cd diamond-backend/diamond
mvn clean install
mvn package
cd ../..

# Starting the Docker containers
docker-compose up --build
