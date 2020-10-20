FROM openjdk:8-jdk-alpine
EXPOSE 9090
ADD target/Middleware.jar middleware.jar
ENTRYPOINT {"java","-jar -Dspring.profiles.active=qa","/middleware.jar"}