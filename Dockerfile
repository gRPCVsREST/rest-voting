FROM java:8
WORKDIR /
ADD build/libs/rest-voting.jar rest-voting.jar
EXPOSE 8080
CMD java -jar rest-voting.jar