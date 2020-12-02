FROM adoptopenjdk/openjdk11
WORKDIR /
ADD target/nedvana-tweet-service-0.0.1-SNAPSHOT.jar //
EXPOSE 8083
ENTRYPOINT ["java","-jar","/nedvana-tweet-service-0.0.1-SNAPSHOT.jar"]