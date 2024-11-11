FROM eclipse-temurin:21
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} reqlicit-api-1.0.0.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "reqlicit-api-1.0.0.jar"]