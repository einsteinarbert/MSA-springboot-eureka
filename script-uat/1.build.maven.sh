#!/bin/bash
set -e
cecho() {
    RED="\033[0;31m"
    GREEN="\033[0;32m"
    YELLOW="\033[1;33m"
    # ... ADD MORE COLORS
    NC="\033[0m" # No Color
    # ZSH
    # printf "${(P)1}${2} ${NC}\n"
    # Bash
    printf "${!1}${2} ${NC}\n"
}

cd ../
home=`pwd`
cecho "YELLOW" "HOME: $home"
cd gateway-zuul ; mvn clean package -DskipTests; docker build -t gateway-service:1.0 .
cd $home
cd game-api ; mvn clean package -DskipTests; docker build -t game-api:1.0 .
cd $home
cd Ayakashi-admin ; mvn clean package -DskipTests; docker build -t admin-service:1.0 .
cd $home
cecho "GREEN" "BUILD ALL SUCCESSFULLY"

