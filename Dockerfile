FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/clinica-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet /app/Wallet
EXPOSE 8080

CMD [ "java", "-jar", "app.jar" ]