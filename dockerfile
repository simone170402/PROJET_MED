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

# Utiliser une image de base pour la construction du frontend
FROM node:18-slim AS frontend-build

# Définir le répertoire de travail pour le frontend
WORKDIR /frontend

# Copier package.json et package-lock.json d'abord
COPY Reservation-UI/package*.json ./

#d'abord, nettoyons le cache 
RUN npm cache clean --force
#RUN npm install --legacy-peer-deps @angular-devkit/build-angular

# Installer les dépendances avec --legacy-peer-deps
RUN npm install --legacy-peer-deps
# RUN npm install rimraf@latest --legacy-peer-deps
# RUN npm install glob@latest --legacy-peer-deps
# RUN npm uninstall critters --legacy-peer-deps
# RUN npm install beasties --legacy-peer-deps
RUN npm install --save typescript@5.4.2 @fortawesome/fontawesome-free --legacy-peer-deps

# Copier le reste des fichiers du frontend
COPY Reservation-UI .

# Construire l'application frontend
RUN npm run build --configuration=development

# Utiliser une image de base plus légère pour l'exécution
FROM eclipse-temurin:21-jre

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR de l'étape de construction du backend
COPY --from=backend-build /app/build/libs/*.jar app.jar

# Copier les fichiers construits du frontend
COPY --from=frontend-build /frontend/dist/reservation-ui /app/public

# Exposer les ports pour le backend et le frontend
EXPOSE 8080
EXPOSE 4200

# Définir la commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]