FROM openjdk:17-jdk-slim as build
RUN mkdir -p "/workspace"
WORKDIR "/workspace"
COPY . /workspace
RUN ./mvnw clean compile -T 4 -DskipTests --settings .mvn/conf/settings.xml
RUN ./mvnw package -DskipTests --settings .mvn/conf/settings.xml

FROM openjdk:17-jdk-slim
LABEL "author"="lye"
RUN mkdir -p "/workspace"
WORKDIR "/workspace"
COPY --from=build /workspace/target/native-image-demo-0.0.1-SNAPSHOT.jar /workspace/app.jar
ENV JAVA_OPTS="--add-opens=java.base/java.time=ALL-UNNAMED"
EXPOSE 8080
EXPOSE 9898
ENTRYPOINT java ${JAVA_OPTS} -jar app.jar
