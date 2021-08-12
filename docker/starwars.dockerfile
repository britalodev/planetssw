FROM openjdk:8-jdk

WORKDIR /app

COPY ../target/planetssw-0.0.1-SNAPSHOT.jar /app/planetssw-app.jar

ENTRYPOINT ["java","-jar","planetssw-app.jar"]