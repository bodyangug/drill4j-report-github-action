FROM amazoncorretto:8

RUN yum update -y
COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean build

RUN chmod +x build/libs/app.jar

ENTRYPOINT java -jar build/libs/app.jar $GITHUB_EVENT_PATH $1
