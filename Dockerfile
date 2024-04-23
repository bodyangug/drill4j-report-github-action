FROM amazoncorretto:8

RUN yum update -y
COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean build
RUN ls
RUN ls build
RUN ls build/libs
COPY build/libs/app.jar app.jar
RUN chmod +x ./app.jar

ENTRYPOINT java -jar app.jar $GITHUB_EVENT_PATH $1
