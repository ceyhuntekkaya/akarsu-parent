# Build stage
FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app

# Uygulama için değişkenler
ENV PORT=8080
ENV SPRING_PROFILES_ACTIVE=prod

# Build aşamasından JAR dosyasını kopyala
COPY --from=build /app/target/*.jar app.jar

# Port ayarı
EXPOSE ${PORT}

# Uygulamayı çalıştır
ENTRYPOINT ["java", "-jar", "app.jar"]

# JVM ayarları (opsiyonel)
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Sağlık kontrolü
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:${PORT}/actuator/health || exit 1