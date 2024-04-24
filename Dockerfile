FROM gradle:7.4.2-jdk8 AS build_stage
WORKDIR /home/gradle
COPY . .
RUN gradle build
RUN pwd
RUN ls
FROM amazoncorretto:8
COPY --from=build_stage /home/gradle/drill4j-report-github-action/build/libs/app.jar /opt/

ARG githubEventPathParam=$GITHUB_EVENT_PATH
ARG whoToGreetParam=$1

ENTRYPOINT java -jar /opt/app.jar $githubEventPathParam $whoToGreetParam
