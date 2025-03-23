FROM python:3.13
WORKDIR /app
# Installing dependencies
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copying the source code into the container
COPY ./src ./src

# Generating the gRPC code
WORKDIR /app/src
RUN python -m grpc_tools.protoc \
    -I . \
    --python_out=. \
    --grpc_python_out=. \
    protos/service.proto

WORKDIR /app
ENTRYPOINT [ "python", "src/service.py" ]