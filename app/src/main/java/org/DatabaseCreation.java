package org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseCreation {

    // Méthode pour se connecter à la base de données PostgreSQL
    public static Connection connect() {
        Connection conn = null;
        try {

            // Charger le driver JDBC pour PostgreSQL
            System.out.println("Bonjour");
            // URL de connexion à la base de données PostgreSQL
            String url = "jdbc:postgresql://localhost:5432/reservation_vaccination";
            String user = "postgres";
            String password = "simone";

            // Établir la connexion à PostgreSQL
            conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("Connexion à la base de données réussie !");
            } else {
                System.out.println("Échec de la connexion !");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Méthode pour créer les tables
    public static void createTables(Connection conn) {
        Statement stmt = null;

        String[] sqlQueries = {
            // Création de la table Utilisateur
            """
            CREATE TABLE IF NOT EXISTS Utilisateur (
                id SERIAL PRIMARY KEY,
                email VARCHAR(255) UNIQUE NOT NULL,
                password VARCHAR(255) NOT NULL,
                nom VARCHAR(255) NOT NULL,
                prenom VARCHAR(255) NOT NULL,
                telephone VARCHAR(20)
            );
            """,

            // Création de la table Centre
            """
            CREATE TABLE IF NOT EXISTS Centre (
                id SERIAL PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                city VARCHAR(255) NOT NULL,
                address VARCHAR(255) NOT NULL,
                phoneNumber VARCHAR(20) NOT NULL
            );
            """,

            // Création de la table Patient
            """
            CREATE TABLE IF NOT EXISTS Patient (
                id SERIAL PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                surname VARCHAR(255) NOT NULL,
                phoneNumber VARCHAR(20),
                email VARCHAR(255),
                dateOfBirth DATE,
                centre INT REFERENCES Centre(id),
                vaccinationStatus BOOLEAN DEFAULT FALSE
            );
            """,

            // Création de la table Medecin
            """
            CREATE TABLE IF NOT EXISTS Medecin (
                id SERIAL PRIMARY KEY,
                nom VARCHAR(255) NOT NULL,
                prenom VARCHAR(255) NOT NULL,
                centre INT REFERENCES Centre(id)
            );
            """,

            // Création de la table Reservation
            """
            CREATE TABLE IF NOT EXISTS Reservation (
                id SERIAL PRIMARY KEY,
                patient INT REFERENCES Patient(id),
                centre INT REFERENCES Centre(id),
                date_reservation DATE NOT NULL,
                statut VARCHAR(50)
            );
            """,

            // Création de la table Administrateur_centre
            """
            CREATE TABLE IF NOT EXISTS Administrateur_centre (
                id SERIAL PRIMARY KEY,
                nom VARCHAR(255) NOT NULL,
                prenom VARCHAR(255) NOT NULL,
                email VARCHAR(255) NOT NULL,
                role VARCHAR(50) CHECK (role IN ('SUPER_ADMIN', 'ADMIN_CENTRE')),
                Centres INT[] -- Liste des centres gérés par cet admin
            );
            """
        };

        try {
            stmt = conn.createStatement();

            for (String sql : sqlQueries) {
                stmt.execute(sql);
                System.out.println("Table créée ou existante.");
            }

            stmt.close();
            conn.commit();  // Valide les changements
        } catch (Exception e) {
            System.out.println("Erreur lors de la création des tables : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Connexion à la base de données
        Connection conn = connect();
        if (conn != null) {
            // Création des tables
            createTables(conn);

            try {
                conn.close();  // Fermer la connexion après la création des tables
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
