FROM java:8
WORKDIR /
ADD build/libs/voting.jar voting.jar
EXPOSE 8080
CMD java -jar voting.jar