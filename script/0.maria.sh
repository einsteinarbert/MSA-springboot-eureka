#!/bin/bash
docker rm -f db

docker run --detach --name db -p 3306:3306 \
   -v /home/ubuntu/deploy/volumes/mysql:/var/lib/mysql \
 --env MARIADB_USER=lukyluke \
 --env MARIADB_PASSWORD=lukyluke \
 --env MARIADB_ROOT_PASSWORD=lukyluke  mariadb:latest
