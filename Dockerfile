FROM openjdk:8
ADD target/motor-vehicles-factory.jar motor-vehicles-factory.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "motor-vehicles-factory.jar"]