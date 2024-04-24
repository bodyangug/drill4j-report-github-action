FROM amazoncorretto:8

COPY . .

RUN chmod +x gradlew && \
    ./gradlew clean build && \
     cp ./build/libs/app.jar /opt/app.jar

ENTRYPOINT java -jar /opt/app.jar $GITHUB_EVENT_PATH $1
