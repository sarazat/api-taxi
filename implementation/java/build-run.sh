#!/usr/bin/env bash
RUNNING_PATH="$(pwd)"
cd ../../api-doc/
npm install
node build/prepare.js
echo "$RUNNING_PATH"
cd "$RUNNING_PATH"
mvn clean install
cd vehicle-api
mvn spring-boot:run
