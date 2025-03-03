# PROJET_MED
Application pour inscription à des créneaux de vaccination. 

#  Projet Backend - Authentification avec Basic Auth

##  Description
Ce projet met en place un système d'authentification basé sur **Spring Security** avec **Basic Auth**.  
Les utilisateurs peuvent se connecter et accéder à différentes ressources en fonction de leur rôle (**SUPER_ADMIN, ADMIN_CENTRE, MEDECIN, PATIENT**).

---

##  **Utilisateurs et Rôles**
Voici les comptes test pour l'authentification :

| Rôle            | Nom        | Prénom   | Téléphone  | Email                                | Mot de passe |
|---------------|-----------|----------|-----------|--------------------------------------|--------------|
| **SUPER_ADMIN** | Jean      | Dupont   | 0612345678 | `jean.dupont@example.com`            | `password123` |
| **ADMIN_CENTRE** | Medecin   | 182      | 0678901332 | `medecin.onehundredeightytwo@example.com` | `password123` |
| **MEDECIN**      | Marie     | Curie    | 0623456789 | `marie.curie@example.com`             | `password123` |
| **PATIENT**      | Marie     | Dubois   | 0623481293 | `marie.dubois@example.com`           | `password123` |

** Remarque :** Les mots de passe sont **hachés** et **salés** s en base de données.  
Dans les tests, utilisez `password123`, qui sera automatiquement comparé avec le mot de passe haché.

---

## **Tester l'Authentification Basic Auth**

###  **Avec Postman**
1. Ouvrir **Postman**.
2. Sélectionner **GET** et entrer l'URL :http://localhost:8080/api/auth/login

3. Aller dans l'onglet **Authorization**.
4. Choisir **Basic Auth**.
5. Entrer les identifiants d'un utilisateur :
- **Email:** `jean.dupont@example.com`
- **Password:** `password123`
6. Cliquer sur **Send**.

#### **Réponse attendue (JSON)**
Si les identifiants sont corrects, vous recevrez :
```json
{
 {
    "roles": [
        "SUPER_ADMIN"
    ],
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqZWFuLmR1cG9udEBleGFtcGxlLmNvbSIsInJvbGVzIjpbXSwiaWF0IjoxNzQxMDExODkyLCJleHAiOjE3NDEwOTgyOTJ9.-5M6Mt65vGLMjm2n3y7WMtYGBpZn1x4-acyPC4ET3cbmzKLW86dPuiD_ZRrBJO82E0lrwzfFH-TIyGHIW4adsQ",
    "user": {
        "id": 1,
        "name": "Jean",
        "surname": "Dupont",
        "phoneNumber": "0612345678",
        "email": "jean.dupont@example.com",
        "password": "$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS",
        "address": null,
        "city": null,
        "utilisateurRoles": [
            {
                "id": 1,
                "role": {
                    "id": 4,
                    "name": "SUPER_ADMIN",
                    "utilisateurs": []
                }
            }
        ],
        "centre": {
            "id": 1,
            "name": "Centre Hospitalier Universitaire de Bordeaux",
            "city": "Bordeaux",
            "address": "Place Amélie Raba Léon",
            "phoneNumber": "0556248394"
        },
        "patients": [
            {
                "id": 1,
                "name": "Marie",
                "surname": "Dubois",
                "phoneNumber": "0623481293",
                "email": "marie.dubois@example.com",
                "dateOfBirth": "1988-04-05",
                "adresse": null,
                "centre": {
                    "id": 1,
                    "name": "Centre Hospitalier Universitaire de Bordeaux",
                    "city": "Bordeaux",
                    "address": "Place Amélie Raba Léon",
                    "phoneNumber": "0556248394"
                }
            }
        ],
        "role": []
    }
}
}
```

#### **Dépendances essentielles**

### Angular

- **@angular/core**: ^18.2.0  
  ```bash
  npm install @angular/core@^18.2.0
  ```

- **@angular/common**: ^18.2.0  
  ```bash
  npm install @angular/common@^18.2.0
  ```

- **@angular/forms**: ^18.2.0  
  ```bash
  npm install @angular/forms@^18.2.0
  ```

- **@angular/router**: ^18.2.0  
  ```bash
  npm install @angular/router@^18.2.0
  ```

- **@angular/material**: ^18.2.0  
  ```bash
  npm install @angular/material@^18.2.0
  ```

### Node.js

- **Node.js**: >=18.19.1  
  (Assurez-vous d'installer Node.js depuis le [site officiel](https://nodejs.org))

### FullCalendar

- **@fullcalendar/angular**: ^6.1.16  
  ```bash
  npm install @fullcalendar/angular@^6.1.16
  ```

- **@fullcalendar/core**: ^6.1.15  
  ```bash
  npm install @fullcalendar/core@^6.1.15
  ```

- **@fullcalendar/daygrid**: ^6.1.15  
  ```bash
  npm install @fullcalendar/daygrid@^6.1.15
  ```

- **@fullcalendar/timegrid**: ^6.1.15  
  ```bash
  npm install @fullcalendar/timegrid@^6.1.15
  ```
# ReservationUI - Frontend

## Description
ReservationUI est une application web permettant de gérer les réservations. Elle inclut des interfaces de connexion et un backend fonctionnel pour traiter les données.

## Fonctionnalités
- Interface utilisateur intuitive pour les réservations.
- Authentification des utilisateurs.
- Gestion des réservations via un backend fonctionnel.

#### Installation
Clonez le projet et installez les dépendances nécessaires :
```bash
git clone <URL_DU_REPO>
cd Reservation-UI
npm install

```
## Démarrage de l'application
Pour démarrer l'application, exécutez :
```bash
ng serve
```

## Technologies utilisées
- **Frontend**: Angular, Angular Material, FullCalendar
- **Backend**: java , springboot

## Problèmes connus

### 1. Problèmes d'affichage avec Angular Material
Il existe des problèmes d'affichage dans l'interface utilisateur lors de l'utilisation des composants Angular Material. Cela peut inclure des éléments qui ne s'affichent pas correctement, des styles qui ne s'appliquent pas ou des erreurs de mise en page. 


### 2. Déconnexion de l'utilisateur lors des modifications côté front
Lorsqu'une modification est effectuée dans l'interface utilisateur, il arrive que l'utilisateur soit déconnecté de l'application. Cela nécessite un redémarrage du frontend pour rétablir l'accès à l'interface utilisateur. 



### 3. Problèmes avec Jackson côté back
Des problèmes ont été rencontrés en utilisant Jackson pour la sérialisation et la désérialisation des objets côté backend. Cela peut inclure des erreurs lors de la conversion des objets Java en JSON, ou vice versa. 
