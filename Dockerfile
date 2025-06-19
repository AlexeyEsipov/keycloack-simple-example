FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /workspace
COPY . .
RUN ./gradlew build

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=builder /workspace/build/libs/*.jar app.jar
COPY .env.properties .

ENV ENV_FILE=.env

ENTRYPOINT ["sh", "-c", "set -a && source $ENV_FILE && java -jar app.jar"]