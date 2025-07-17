# Stage 1: Build with Maven image
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Final runtime image
FROM eclipse-temurin:17-jdk

RUN apt-get update && apt-get install -y wget unzip && \
    wget https://releases.hashicorp.com/terraform/1.8.5/terraform_1.8.5_linux_amd64.zip && \
    unzip terraform_1.8.5_linux_amd64.zip && \
    mv terraform /usr/local/bin/ && \
    terraform -version

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
COPY terraform /app/terraform

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
