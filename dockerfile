# Étape 1: Construction du backend
FROM eclipse-temurin:21-jdk AS backend-build

# Définir le répertoire de travail
WORKDIR /app

# Copier uniquement les fichiers nécessaires pour la résolution des dépendances
COPY app/build.gradle settings.gradle ./
COPY gradle gradle/
COPY gradlew ./
RUN chmod +x gradlew

# Télécharger les dépendances (cette étape sera mise en cache si les fichiers gradle ne changent pas)
RUN --mount=type=cache,target=/root/.gradle ./gradlew dependencies

# Copier le reste du code source et construire
COPY app/src src/
RUN --mount=type=cache,target=/root/.gradle ./gradlew clean build -x test

# Étape 2: Construction du frontend
FROM node:18-slim AS frontend-build

# Définir le répertoire de travail
WORKDIR /frontend

# Copier uniquement package.json et package-lock.json
COPY Reservation-UI/package*.json ./

# Installer les dépendances avec mise en cache
RUN --mount=type=cache,target=/root/.npm \
    npm install --legacy-peer-deps

# Copier le reste des fichiers du frontend
COPY Reservation-UI/ ./

# Supprimer les fichiers SSR et corriger les styleUrls
RUN rm -rf server.ts src/server.ts server.ngtypecheck.ts && \
    find src/app -name "*.component.ts" -exec sed -i 's/styleUrl: \(.*\)/styleUrls: [\1]/' {} \; && \
    sed -i 's/"server": "src\/main.server.ts",//g; s/"prerender": true,//g; s/"ssr": {[^}]*},//g' angular.json

# Construire l'application en mode production
RUN --mount=type=cache,target=/root/.npm \
    npm run build

# Étape 3: Image finale
FROM eclipse-temurin:21-jre

# Installer les dépendances minimales nécessaires
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Définir le répertoire de travail
WORKDIR /app

# Copier uniquement les artefacts nécessaires
COPY --from=backend-build /app/build/libs/*.jar app.jar
COPY --from=frontend-build /frontend/dist/reservation-ui/browser /app/public

# Configuration de l'application
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

# Vérification de la santé de l'application
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Exposer le port
EXPOSE 8080

# Démarrer l'application
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]