#!/bin/bash

# Copy proto file from Python project to Java project
echo "Copying proto file"
./copy_proto.sh

# Generate requirements.txt file
echo "Generating requirements.txt"
./py-reqs.sh

# Compile the Java project into a .jar executable
echo "Compiling Java project"
cd diamond-backend/diamond
mvn clean install
mvn package
# mvn compile

# Back to root of directory
echo "Starting containers"
cd ../..
docker-compose up --build
