# Utiliser une image de base avec JDK 21 pour la construction du backend
FROM openjdk:21-jdk-slim-buster AS backend-build

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
FROM node:18 AS frontend-build

# Définir le répertoire de travail pour le frontend
WORKDIR /frontend

<<<<<<< HEAD
# Copier package.json et package-lock.json
COPY Reservation-UI/package*.json ./

# Installer les dépendances
RUN npm install --legacy-peer-deps

# Copier le reste des fichiers du frontend
COPY Reservation-UI/ ./

# Supprimer la configuration SSR
RUN rm -rf src/server.ts server.ts

# Créer un script pour corriger les styleUrls
RUN echo '#!/bin/sh\n\
for file in src/app/**/*.component.ts; do\n\
  if [ -f "$file" ]; then\n\
    sed -i "s/styleUrls: \x27\([^[\x27]*\)\x27/styleUrls: [\x27\1\x27]/" "$file"\n\
    sed -i "s/styleUrls: \"\([^[\"]*\)\"/styleUrls: [\"\1\"]/" "$file"\n\
  fi\n\
done' > fix-styles.sh && chmod +x fix-styles.sh

# Exécuter le script
RUN ./fix-styles.sh

# Ajouter la déclaration de type pour @angular/common/locales/fr
RUN echo "declare module '@angular/common/locales/fr';" > src/types.d.ts

# Construire l'application frontend en mode production
=======
# Copier les fichiers du frontend
COPY Reservation-UI /frontend

# Installer les dépendances et construire l'application frontend
RUN npm install
>>>>>>> f7d8e515ed5c5b74b8132e03095b9c19da1f1684
RUN npm run build

# Utiliser une image de base plus légère pour l'exécution
FROM openjdk:21-jre-slim-buster

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