mvn clean package -DskipTests;
docker build -t 123.31.41.13:8297/gateway-game:1.0 -f DockerfileReuse .
docker push 123.31.41.13:8297/gateway-game:1.0

