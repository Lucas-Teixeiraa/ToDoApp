# Use a imagem base do OpenJDK
FROM openjdk:17-jdk-slim AS builder

# Defina o diretório de trabalho
WORKDIR /app

# Copie os arquivos de configuração do Gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle/ gradle/

# Copie o script gradlew e o diretório src
COPY gradlew .
COPY src/ src/

# Dê permissão de execução para o script gradlew
RUN chmod +x gradlew

# Execute o build do Gradle
RUN ./gradlew build --no-daemon

# Etapa final - imagem do runtime
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o jar gerado para o novo contêiner
COPY --from=builder /app/build/libs/*.jar app.jar

# Comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]
