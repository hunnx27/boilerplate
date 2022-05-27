FROM amazoncorretto:11
CMD ["./mvnw", "clean", "package"]
VOLUME /tmp
ARG JAR_FILE_PATH=target/*.jar
COPY ${JAR_FILE_PATH} app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Duser.timezone=${TZ}" ,"-jar","/app.jar"]
