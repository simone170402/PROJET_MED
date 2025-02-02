# Utiliser une image de base avec JDK 21 pour la construction
FROM openjdk:21-jdk-slim AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier les fichiers de configuration Gradle et le fichier build.gradle
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# Télécharger les dépendances sans construire le projet
RUN ./gradlew dependencies

# Copier le reste du code source
COPY src /app/src

# Construire l'application
RUN ./gradlew clean build

# Utiliser une image de base plus légère pour l'exécution
FROM openjdk:21-jre-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR de l'étape de construction
COPY --from=build /app/build/libs/*.jar app.jar

# Exposer le port sur lequel l'application écoute
EXPOSE 8084

# Définir la commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]