FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/finance-0.1.0.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
CMD ["redis-server","/etc/redis/redis.conf"]
EXPOSE 8080