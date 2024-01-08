FROM maven:3.8.5-openjdk-17 AS contruction
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=contruction /target/info-fusion-back-0.0.1-SNAPSHOT.war info-fusion-back.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","info-fusion-back.war"]