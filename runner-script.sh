#!/bin/bash
mvn clean package -DskipTests
docker buildx b . -t wallet:latest
docker-compose up
