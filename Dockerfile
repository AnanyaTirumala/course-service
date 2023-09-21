FROM openjdk
LABEL maintainer = "abc@mail.com"
EXPOSE 8083
WORKDIR /app
COPY target/course-service.jar /app/course-service.jar
ENTRYPOINT ["java", "-jar", "course-service.jar"]