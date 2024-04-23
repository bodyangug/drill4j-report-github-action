FROM amazoncorretto:8

RUN yum update -y
COPY docker .

RUN chmod +x gradlew
RUN ./gradlew clean build
COPY ../build/libs/*.jar /app.jar
RUN chmod +x ./app.jar

ENTRYPOINT java -jar app.jar $GITHUB_EVENT_PATH $1
