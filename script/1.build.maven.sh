#!/bin/bash
set -e
cd ../
home=`pwd`
echo HOME: $home
cd gateway-zuul ; mvn clean package -DskipTests; docker build -t gateway-service:1.0 .
cd $home
cd game-api ; mvn clean package -DskipTests; docker build -t game-api:1.0
cd $home
cd Ayakashi-admin ; mvn clean package -DskipTests; docker build -t admin-service:1.0 .
cd $home
echo "BUILD SUCCESSFULLY"

