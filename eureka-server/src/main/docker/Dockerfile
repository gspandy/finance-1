FROM java:8
VOLUME /tmp
RUN mkdir /app
ADD eureka-server-0.0.1.jar /app/app.jar
ADD runboot.sh /app/
RUN bash -c 'touch /app/app.jar'
WORKDIR /app
RUN chmod a+x runboot.sh
EXPOSE 8761
CMD /app/runboot.sh
RUN echo "Asia/Shanghai" > /etc/timezone;