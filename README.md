Voici une version mise à jour du fichier 

README.md

 avec les instructions pour Windows, Mac, et Linux, ainsi que pour Visual Studio Code et IntelliJ IDEA :

```markdown
# PROJET_MED
Application pour inscription à des créneaux de vaccination.

## Étapes pour lancer le projet

### Prérequis
- Node.js (version 18.x ou supérieure)
- Angular CLI
- Java 17
- PostgreSQL
- Visual Studio Code ou IntelliJ IDEA

### Cloner le dépôt
```bash
git clone <URL_DU_DEPOT>
cd <NOM_DU_DEPOT>
```

### Installer Angular CLI globalement
#### Windows, Mac, Linux
```bash
sudo npm install -g @angular/cli
```

### Mettre à jour Node.js à la version requise
#### Windows
Téléchargez et installez Node.js depuis [nodejs.org](https://nodejs.org/).

#### Mac
```bash
brew install node@18
brew link --force --overwrite node@18
```

#### Linux
```bash
sudo apt remove nodejs libnode72 libnode-dev
curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E bash -
sudo apt install -y nodejs
```

### Vérifier l'installation de Node.js
```bash
node --version
```

### Naviguer dans le répertoire du projet
```bash
cd ~/PROJET_MED/Reservation-UI
```

### Installer les dépendances du projet
```bash
npm install --legacy-peer-deps
```

### Installer le module manquant `@fullcalendar/core`
```bash
npm install @fullcalendar/core --legacy-peer-deps
```

### Lancer le projet Angular
```bash
ng serve
```

### (Optionnel) Activer l'autocomplétion pour les commandes Angular CLI
```bash
source <(ng completion script)
```

Suivez ces étapes pour configurer et lancer votre projet Angular.

## Étapes pour lancer le backend Spring Boot

### Installer Java 17
#### Windows
Téléchargez et installez Java 17 depuis [adoptium.net](https://adoptium.net/).

#### Mac
```bash
brew install openjdk@17
sudo ln -sfn /usr/local/opt/openjdk@17/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-17.jdk
```

#### Linux
```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

### Vérifier l'installation de Java 17
```bash
java -version
```

### Configurer Java 17 comme version par défaut
#### Windows
Configurez les variables d'environnement JAVA_HOME et PATH.

#### Mac
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
```

#### Linux
```bash
sudo update-alternatives --config java
```

### Installer PostgreSQL
#### Windows
Téléchargez et installez PostgreSQL depuis [postgresql.org](https://www.postgresql.org/download/).

#### Mac
```bash
brew install postgresql
brew services start postgresql
```

#### Linux
```bash
sudo apt update
sudo apt install postgresql postgresql-contrib
sudo systemctl start postgresql
```

### Configurer PostgreSQL
#### Accéder à l'invite de commande PostgreSQL
```bash
sudo -i -u postgres
psql
```

#### Créer une base de données et un utilisateur
```sql
CREATE DATABASE vaccinationMain;
CREATE USER postgre WITH PASSWORD 'Admin';
GRANT ALL PRIVILEGES ON DATABASE vaccinationMain TO postgre;
\q
exit
```

### Vérifier les informations de connexion dans `application.yaml`
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/vaccinationMain
    username: postgre
    password: Admin
  
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    show-sql: true
    hibernate:
      ddl-auto: create
    defer-datasource-initialization: true

  sql:
    init:
      mode: always

server:
  port: 8081
```

### Lancer l'application Spring Boot

#### Visual Studio Code
1. Ouvrez le projet dans Visual Studio Code.
2. Utilisez l'extension Spring Boot Dashboard pour démarrer l'application.

#### IntelliJ IDEA
1. Ouvrez le projet dans IntelliJ IDEA.
2. Cliquez sur le bouton "Run" pour démarrer l'application.

Assurez-vous que Gradle, Java et PostgreSQL sont installés et configurés correctement sur votre machine.
```