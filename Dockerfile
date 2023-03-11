
FROM openjdk:11
EXPOSE 8080
ADD target/ebanking_backend-docker.jar ebanking_backend-docker.jar
ENTRYPOINT ["java","-jar","/ebanking_backend-docker.jar"]