#!/usr/bin/env bash

cp /executeMakePrComments $GITHUB_WORKSPACE

echo 'Make comments in PR...'
./executeMakePrComments $GITHUB_EVENT_PATH $INPUT_REPOTOKEN
echo '----------------------'
echo ''
