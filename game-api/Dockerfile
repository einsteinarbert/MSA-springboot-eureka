FROM openjdk:11.0.1

ADD ./target/*.jar application.jar

ENTRYPOINT java -Duser.timezone=GMT+0 -jar /application.jar
