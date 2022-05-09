#!/bin/bash
docker-compose -p uat down
docker-compose -p uat up -d
