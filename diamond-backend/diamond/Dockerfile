FROM amazoncorretto:23
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# copy the proto file from the python service directory
# RUN ["cp", "../../diamond_py/src/protos/service.proto", "./src/main/proto/service.proto"]

# generate gRPC/protobuf code
# RUN ["mvn", "clean", "compile"]
# RUN ["mvn", "protobuf:compile"]
# RUN ["mvn", "protobuf:compile-custom"]

ENTRYPOINT ["java","-jar","/app.jar"]