## MSA
- base on: 
  - https://github.com/OmarElGabry/microservices-spring-boot
  - https://roytuts.com/spring-cloud-gateway-security-with-jwt-json-web-token/

## Install:
- maven and git:
  `sudo apt-get install maven git`
- jdk-11: 
  `sudo apt-get install openjdk-11-jdk`
- docker, docker-compose
  `sudo apt-get install docker docker-compose`
## build:
0. clone project <br/>
   - `git clone  https://mindshift.backlog.jp/git/DMSMUKOUSHI/DMSMUKOUSHISV.git`
1. Goto ./script
2. Create folder for save volume mariadb, for example: `/home/ubuntu/deploy/volumes/mysql`
3. Edit `0.maria.sh` and insert path above
4. `./0.maria.sh`
5. `./1.build.maven.sh`
6. `sudo ./3.start.sh`
