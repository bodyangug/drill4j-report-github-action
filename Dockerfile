FROM gradle:7.4.2-jdk8 AS build_stage
WORKDIR /home/gradle
COPY . .
RUN gradle build

FROM amazoncorretto:8
COPY --from=build_stage /home/gradle/build/libs/app.jar /opt/

ARG githubEventPathParam=$GITHUB_EVENT_PATH
ARG whoToGreetParam1=$1
ARG whoToGreetParam0=$0

RUN echo 'Test githubEventPathParam'
RUN echo $githubEventPathParam
RUN echo 'Test whoToGreetParam1'
RUN echo $whoToGreetParam1
RUN echo 'Test whoToGreetParam0'
RUN echo $whoToGreetParam0

ENTRYPOINT java -jar /opt/app.jar $githubEventPathParam $whoToGreetParam
