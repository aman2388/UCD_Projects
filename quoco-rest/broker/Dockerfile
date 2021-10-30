FROM openjdk:8-jre-alpine
ENV AF="localhost:8081", DD="localhost:8082", GP="localhost:8083"
COPY target/*-1.0.jar /broker-1.0.jar
CMD java -jar /broker-1.0.jar $AF $DD $GP