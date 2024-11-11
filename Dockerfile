FROM eclipse-temurin:21
WORKDIR /app
COPY target/reqlicit-api.jar reqlicit-api.jar
EXPOSE 8085
CMD ["java","-jar","reqlicit-api.jar"]