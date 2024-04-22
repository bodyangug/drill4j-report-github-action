FROM openjdk:8-alpine

RUN apk upgrade --update && \
	apk add bash curl

COPY executeMakePrComments /executeMakePrComments
RUN chmod +x /executeMakePrComments

COPY docker/run-scripts.sh /run-scripts.sh
RUN chmod +x /run-scripts.sh

ENTRYPOINT /bin/bash /run-scripts.sh
