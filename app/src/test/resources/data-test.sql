-- Insertion des données
INSERT INTO centre (id, name) VALUES (1, 'Centre Test');
INSERT INTO medecin (id, name, surname, centre_id) VALUES (1, 'Dr. House', 'Gregory', 1);
INSERT INTO patient (id, name, surname, email) VALUES (1, 'John', 'Doe', 'john.doe@example.com');