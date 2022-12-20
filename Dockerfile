FROM openjdk:17
ADD ./target/weather-0.0.1-SNAPSHOT.jar weather.jar
ENTRYPOINT ["java", "-jar", "weather.jar"]