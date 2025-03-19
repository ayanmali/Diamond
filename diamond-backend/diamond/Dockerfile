FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# copy the proto file from the python service directory
RUN cp ../../diamond_py/src/protos/service.proto ./service.proto

# generate gRPC/protobuf code
# RUN mvn clean compile
# RUN mvn protobuf:compile
# RUN mvn protobuf:compile-custom

# mvn clean install