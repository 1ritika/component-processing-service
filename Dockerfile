FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8081
ADD target/*.jar component-processing-service-777.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /component-processing-service-777.jar" ]