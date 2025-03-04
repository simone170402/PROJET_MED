# Utiliser une image de base avec JDK pour la construction du backend
FROM eclipse-temurin:21-jdk AS backend-build

# Définir le répertoire de travail pour le backend
WORKDIR /app

# Copier les fichiers de configuration Gradle et le fichier build.gradle
COPY app/build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# Télécharger les dépendances sans construire le projet
RUN ./gradlew dependencies

# Copier le reste du code source du backend
COPY app/src /app/src

# Construire l'application backend
RUN ./gradlew clean build

# Utiliser une image de base pour la construction du frontend avec la version spécifiée de Node.js
FROM node:18.19-slim AS frontend-build

# Définir le répertoire de travail pour le frontend
WORKDIR /frontend

# Copier package.json et package-lock.json d'abord
COPY Reservation-UI/package*.json ./

# Nettoyer le cache npm
RUN npm cache clean --force

# Installer les dépendances Angular spécifiées dans le README
RUN npm install --legacy-peer-deps \
    @angular/core@^18.2.0 \
    @angular/common@^18.2.0 \
    @angular/forms@^18.2.0 \
    @angular/router@^18.2.0 \
    @angular/material@^18.2.0 \
    @fullcalendar/angular@^6.1.16 \
    @fullcalendar/core@^6.1.15 \
    @fullcalendar/daygrid@^6.1.15 \
    @fullcalendar/timegrid@^6.1.15 \
    @fortawesome/fontawesome-free \
    typescript@5.4.2



# Copier le reste des fichiers du frontend
COPY Reservation-UI .

# Construire l'application frontend
RUN npm run build --configuration=production

# Utiliser une image de base plus légère pour l'exécution
FROM eclipse-temurin:21-jre

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR de l'étape de construction du backend
COPY --from=backend-build /app/build/libs/*.jar app.jar

# Copier les fichiers construits du frontend
COPY --from=frontend-build /frontend/dist/reservation-ui /app/public

# Exposer le port pour l'API backend et le frontend
EXPOSE 8080
EXPOSE 4200
# Définir la commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]