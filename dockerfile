# Utiliser une image de base avec JDK 21 pour la construction du backend
FROM eclipse-temurin:21-jdk AS backend-build

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
FROM node:18 AS frontend-build

# Définir le répertoire de travail pour le frontend
WORKDIR /frontend

# Copier les fichiers du frontend
COPY Reservation-UI/ ./

# Installer les dépendances et construire l'application frontend
RUN npm install --legacy-peer-deps
RUN npm run build

# Utiliser une image de base plus légère pour l'exécution
FROM eclipse-temurin:21-jre

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR de l'étape de construction du backend
COPY --from=backend-build /app/build/libs/*.jar app.jar

# Copier les fichiers construits du frontend
COPY --from=frontend-build /frontend/build /app/public

# Exposer le port sur lequel l'application écoute
EXPOSE 8080

# Définir la commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]