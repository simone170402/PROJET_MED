# Utiliser une image de base avec JDK 21 pour la construction du backend
FROM eclipse-temurin:21-jdk-jammy AS backend-build

# Définir le répertoire de travail pour le backend
WORKDIR /app

# Copier les fichiers de configuration Gradle
COPY app/build.gradle build.gradle
COPY settings.gradle ./
COPY gradlew ./
COPY gradle gradle/

# Donner les permissions d'exécution au gradlew
RUN chmod +x gradlew

# Télécharger les dépendances sans construire le projet
RUN ./gradlew dependencies

# Copier le reste du code source du backend
COPY app/src src/

# Construire l'application backend
RUN ./gradlew clean build

# Utiliser une image de base pour la construction du frontend
FROM node:18-slim AS frontend-build

# Définir le répertoire de travail pour le frontend
WORKDIR /frontend

# Copier package.json et package-lock.json
COPY Reservation-UI/package*.json ./

# Installer les dépendances
RUN npm install --legacy-peer-deps

# Copier le reste des fichiers du frontend
COPY Reservation-UI/ ./

# Supprimer tous les fichiers liés au SSR
RUN rm -rf server.ts src/server.ts server.ngtypecheck.ts angular:ssr-entry

# Corriger les styleUrls dans les composants
RUN sed -i 's/styleUrl: \(.*\)/styleUrls: [\1]/' src/app/accueil/accueil.component.ts && \
    sed -i 's/styleUrl: \(.*\)/styleUrls: [\1]/' src/app/app.component.ts && \
    sed -i 's/styleUrl: \(.*\)/styleUrls: [\1]/' src/app/centres/centres.component.ts && \
    sed -i 's/styleUrl: \(.*\)/styleUrls: [\1]/' src/app/confirmation/confirmation.component.ts && \
    sed -i 's/styleUrl: \(.*\)/styleUrls: [\1]/' src/app/header/header.component.ts

# Désactiver le SSR dans angular.json
RUN sed -i 's/"server": "src\/main.server.ts",//g' angular.json && \
    sed -i 's/"prerender": true,//g' angular.json && \
    sed -i 's/"ssr": {[^}]*},//g' angular.json

# Construire l'application frontend en mode production
RUN npm run build

# Utiliser une image de base plus légère pour l'exécution
FROM eclipse-temurin:21-jre-jammy

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR de l'étape de construction du backend
COPY --from=backend-build /app/build/libs/*.jar app.jar

# Copier les fichiers construits du frontend
COPY --from=frontend-build /frontend/dist/reservation-ui/browser /app/public

# Exposer le port sur lequel l'application écoute
EXPOSE 8080

# Définir la commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]