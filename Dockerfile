# Etapa 1: Build
FROM maven:3-eclipse-temurin-21 AS build
LABEL authors="Rayka-Hyushi, JoaoAlisson1 & Liche008"

# Define diretório de trabalho
WORKDIR /app

# Copia pom.xml e baixa dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o resto do código
COPY src ./src

# Compila a aplicação
RUN mvn clean package -DskipTests

# Etapa 2: Runtime (mais leve)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copia o .jar compilado
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta onde vai rodar a aplicação
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]