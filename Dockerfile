from openjdk:8-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/system-of-distribution-of-labor-resources-by-personal-characteristics-1.0-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} spring-project-system.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod", "-jar","/system-of-distribution-of-labor-resources-by-personal-characteristics.jar"]
