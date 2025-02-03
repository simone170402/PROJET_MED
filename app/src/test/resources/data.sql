-- Création de la table Centre
CREATE TABLE IF NOT EXISTS centre (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Création de la table Medecin
CREATE TABLE IF NOT EXISTS medecin (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    centre_id BIGINT,
    FOREIGN KEY (centre_id) REFERENCES centre(id)
);

-- Création de la table Patient
CREATE TABLE IF NOT EXISTS patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- Insertion des données
INSERT INTO centre (id, name) VALUES (1, 'Centre Test');
INSERT INTO medecin (id, name, surname, centre_id) VALUES (1, 'Dr. House', 'Gregory', 1);
INSERT INTO patient (id, name, surname, email) VALUES (1, 'John', 'Doe', 'john.doe@example.com');