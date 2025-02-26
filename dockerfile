# Étape 1: Construction du backend
FROM eclipse-temurin:21-jdk AS backend-build

WORKDIR /app

COPY app/build.gradle settings.gradle ./
COPY gradle gradle/
COPY gradlew ./
RUN chmod +x gradlew

RUN --mount=type=cache,target=/root/.gradle ./gradlew dependencies

COPY app/src src/
RUN --mount=type=cache,target=/root/.gradle ./gradlew clean build -x test

# Étape 2: Construction du frontend
FROM node:18-slim AS frontend-build

WORKDIR /frontend

# Copier uniquement package.json et package-lock.json
COPY Reservation-UI/package*.json ./

# Installer les dépendances
RUN --mount=type=cache,target=/root/.npm \
    npm install --legacy-peer-deps

# Copier le reste des fichiers du frontend
COPY Reservation-UI/ ./

# Créer les fichiers CSS manquants et corriger les composants
RUN for component in accueil app centres confirmation footer header; do \
    echo "" > src/app/$component/$component.component.css; \
    done && \
    for file in $(find src/app -name "*.component.ts"); do \
    # Supprimer la ligne template si elle existe
    sed -i '/template:/d' $file; \
    # Corriger styleUrl en styleUrls avec le bon format
    sed -i 's/styleUrl: /styleUrls: [/g' $file; \
    sed -i 's/\.css/\.css]/g' $file; \
    done && \
    # Créer le fichier de types pour les locales
    echo 'declare module "@angular/common/locales/fr";' > src/types.d.ts && \
    # Supprimer les fichiers SSR
    rm -f server.ts src/server.ts server.ngtypecheck.ts && \
    # Nettoyer angular.json
    sed -i '/"server": "src\/main.server.ts"/d' angular.json && \
    sed -i '/"prerender": true/d' angular.json && \
    sed -i '/"ssr": {/,/}/d' angular.json

# S'assurer que tous les composants ont leur fichier HTML
RUN for component in accueil app centres confirmation footer header; do \
    if [ ! -f src/app/$component/$component.component.html ]; then \
    echo "<div></div>" > src/app/$component/$component.component.html; \
    fi; \
    done

# Construire l'application
RUN npm run build

# Étape 3: Image finale
FROM eclipse-temurin:21-jre

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    curl \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY --from=backend-build /app/build/libs/*.jar app.jar
COPY --from=frontend-build /frontend/dist/reservation-ui/browser /app/public

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

EXPOSE 8080

CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]