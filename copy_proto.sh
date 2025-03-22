#!/bin/bash

# Define source and destination paths
SRC_PATH="./diamond_py/src/protos/service.proto"
DEST_PATH="./diamond-backend/diamond/src/main/proto/service.proto"

# Copy the file
cp "$SRC_PATH" "$DEST_PATH"

echo "Proto file copied successfully."
