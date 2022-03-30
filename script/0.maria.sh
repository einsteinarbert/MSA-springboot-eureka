#!/bin/bash

docker run --detach --name db \
 --env MARIADB_USER=lukyluke \
 --env MARIADB_PASSWORD=lukyluke \
 --env MARIADB_ROOT_PASSWORD=lukyluke  mariadb:latest
