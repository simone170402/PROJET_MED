-- Insertion de centres de santé fictifs en France
INSERT INTO centre (name, city, address, phone_number) VALUES 
('Centre Hospitalier Universitaire de Bordeaux', 'Bordeaux', 'Place Amélie Raba Léon', '0556248394'),
('Centre Hospitalier Sud Francilien', 'Évry', '116 Boulevard Jean Jaurès', '0160699177'),
('Hôpital Nord', 'Marseille', 'Chemin des Bourrely', '0412911275'),
('Centre Hospitalier Régional d_Orléans', 'Orléans', '1 Rue Porte Madeleine', '0238553333'),
('Hôpital de la Timone', 'Marseille', '264 Rue Saint-Pierre', '0491384000'),
('Centre Hospitalier Universitaire de Strasbourg', 'Strasbourg', '1 Place de l_Hôpital', '0388116767'),
('Hôpital de la Pitié-Salpêtrière', 'Paris', '47 Boulevard de l_Hôpital', '0142178000'),
('Centre Hospitalier d_Angers', 'Angers', '4 Rue Larrey', '0241222323'),
('Centre Hospitalier Universitaire de Nantes', 'Nantes', 'Boulevard Jean Monnet', '0253671100'),
('Hôpital Européen', 'Marseille', '6 Rue Désirée Clary', '0488136666'),
('Centre Hospitalier de Lyon', 'Lyon', '3 Quai des Célestins', '0478721212'),
('Hôpital Saint-Louis', 'Paris', '1 Avenue Claude Vellefaux', '0144958000'),
('Centre Hospitalier de Toulouse', 'Toulouse', '2 Rue Viguerie', '0561772222'),
('Hôpital Européen Georges-Pompidou', 'Paris', '20 Rue Leblanc', '0156092000'),
('Centre Hospitalier de Lille', 'Lille', '2 Avenue Oscar Lambret', '0320445959'),
('Hôpital de la Croix-Rousse', 'Lyon', '103 Grande Rue de la Croix-Rousse', '0472728383'),
('Centre Hospitalier de Grenoble', 'Grenoble', 'Avenue Maquis du Grésivaudan', '0476765555'),
('Hôpital Saint-Joseph', 'Marseille', '26 Boulevard de Louvain', '0491380000'),
('Centre Hospitalier de Nice', 'Nice', '4 Avenue Reine Victoria', '0492030303'),
('Hôpital Cochin', 'Paris', '27 Rue du Faubourg Saint-Jacques', '0145841919'),
('Centre Hospitalier de Rennes', 'Rennes', '2 Rue Henri Le Guilloux', '0299263232'),
('Hôpital Saint-Antoine', 'Paris', '184 Rue du Faubourg Saint-Antoine', '0149232323'),
('Centre Hospitalier de Montpellier', 'Montpellier', '191 Avenue du Doyen Gaston Giraud', '0467336767'),
('Hôpital Bichat-Claude Bernard', 'Paris', '46 Rue Henri Huchard', '0140232323'),
('Centre Hospitalier de Rouen', 'Rouen', '1 Rue de Germont', '0232888888'),
('Hôpital Tenon', 'Paris', '4 Rue de la Chine', '0144232323'),
('Centre Hospitalier de Nancy', 'Nancy', '29 Avenue du Maréchal de Lattre de Tassigny', '0383838383'),
('Hôpital Européen de Paris', 'Paris', '6 Rue Guy Patin', '0145232323'),
('Centre Hospitalier de Brest', 'Brest', '2 Avenue Foch', '0298444444'),
('Hôpital Saint-Jacques', 'Nantes', '85 Rue Saint-Jacques', '0253444444'),
('Centre Hospitalier de Clermont-Ferrand', 'Clermont-Ferrand', '58 Rue Montalembert', '0473737373'),
('Hôpital Saint-Jean', 'Angers', '4 Rue Larrey', '0241222323'),
('Centre Hospitalier de Dijon', 'Dijon', '14 Rue Paul Gaffarel', '0380666666'),
('Centre Hospitalier de Saint-Étienne', 'Saint-Étienne', '44 Rue Pointe Cadet', '0477910000'),
('Centre Hospitalier de Reims', 'Reims', '45 Rue Cognacq-Jay', '0326780000'),
('Centre Hospitalier de Metz', 'Metz', '1 Rue des Frères Lacretelle', '0387750000'),
('Centre Hospitalier de Tours', 'Tours', '2 Boulevard Tonnellé', '0247380000'),
('Centre Hospitalier de Limoges', 'Limoges', '12 Boulevard de Fleurus', '0555380000'),
('Centre Hospitalier de Amiens', 'Amiens', 'Place Victor Pauchet', '0322780000'),
('Centre Hospitalier de Caen', 'Caen', 'Avenue de la Côte de Nacre', '0231060000'),
('Centre Hospitalier de Poitiers', 'Poitiers', '2 Rue de la Milétrie', '0549490000'),
('Centre Hospitalier de Besançon', 'Besançon', '3 Boulevard Fleming', '0381660000'),
('Centre Hospitalier de Saint-Denis', 'Saint-Denis', '14 Rue du Général Leclerc', '0148320000');
-- 10 entrées ajoutées, continuez avec plus de centres fictifs similaires jusqu'à 25


-- Insertion de UTILISATEURS fictifs
INSERT INTO utilisateur (name, surname, phone_number, email, password) VALUES 
('Jean', 'Dupont', '0612345678', 'jean.dupont@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Marie', 'Curie', '0623456789', 'marie.curie@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Albert', 'Einstein', '0634567890', 'albert.einstein@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Isaac', 'Newton', '0645678901', 'isaac.newton@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Galileo', 'Galilei', '0656789012', 'galileo.galilei@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Charles', 'Darwin', '0667890123', 'charles.darwin@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Nikola', 'Tesla', '0678901234', 'nikola.tesla@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Leonardo', 'da Vinci', '0689012345', 'leonardo.davinci@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Thomas', 'Edison', '0690123456', 'thomas.edison@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Alexander', 'Graham Bell', '0612345679', 'alexander.bell@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Marie', 'Lavoisier', '0623456790', 'marie.lavoisier@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Louis', 'Pasteur', '0634567901', 'louis.pasteur@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Gregor', 'Mendel', '0645679012', 'gregor.mendel@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('James', 'Watson', '0656789123', 'james.watson@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Francis', 'Crick', '0667890234', 'francis.crick@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Rosalind', 'Franklin', '0678901345', 'rosalind.franklin@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Ada', 'Lovelace', '0689012456', 'ada.lovelace@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Alan', 'Turing', '0690123567', 'alan.turing@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Grace', 'Hopper', '0612345680', 'grace.hopper@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Katherine', 'Johnson', '0623456801', 'katherine.johnson@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Dorothy', 'Vaughan', '0634568012', 'dorothy.vaughan@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Mary', 'Jackson', '0645679123', 'mary.jackson@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Carl', 'Sagan', '0656780234', 'carl.sagan@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Neil', 'Armstrong', '0667891345', 'neil.armstrong@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Buzz', 'Aldrin', '0678902456', 'buzz.aldrin@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Yuri', 'Gagarin', '0689013567', 'yuri.gagarin@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Sally', 'Ride', '0690124678', 'sally.ride@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Mae', 'Jemison', '0612345790', 'mae.jemison@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Valentina', 'Tereshkova', '0623457901', 'valentina.tereshkova@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Chris', 'Hadfield', '0634569012', 'chris.hadfield@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Isaac', 'Asimov', '0645678902', 'isaac.asimov@example.com', 'password456'),
('Arthur', 'Clarke', '0656789013', 'arthur.clarke@example.com', 'password789'),
('H.G.', 'Wells', '0667890124', 'hg.wells@example.com', 'password101'),
('Jules', 'Verne', '0678901235', 'jules.verne@example.com', 'password102'),
('Philip', 'Dick', '0689012346', 'philip.dick@example.com', 'password103'),
('Ray', 'Bradbury', '0690123457', 'ray.bradbury@example.com', 'password104'),
('Robert', 'Heinlein', '0612345670', 'robert.heinlein@example.com', 'password105'),
('Frank', 'Herbert', '0623456781', 'frank.herbert@example.com', 'password106'),
('Ursula', 'Le Guin', '0634567892', 'ursula.leguin@example.com', 'password107'),
('William', 'Gibson', '0645678903', 'william.gibson@example.com', 'password108'),
('Neal', 'Stephenson', '0656789014', 'neal.stephenson@example.com', 'password109'),
('Margaret', 'Atwood', '0667890125', 'margaret.atwood@example.com', 'password110'),
('Kurt', 'Vonnegut', '0678901236', 'kurt.vonnegut@example.com', 'password111'),
('Aldous', 'Huxley', '0689012347', 'aldous.huxley@example.com', 'password112'),
('George', 'Orwell', '0690123458', 'george.orwell@example.com', 'password113'),
('Douglas', 'Adams', '0612345671', 'douglas.adams@example.com', 'password114'),
('Terry', 'Pratchett', '0623456782', 'terry.pratchett@example.com', 'password115'),
('Isaac', 'Asimov', '0634567893', 'isaac.asimov2@example.com', 'password116'),
('Arthur', 'Clarke', '0645678904', 'arthur.clarke2@example.com', 'password117'),
('H.G.', 'Wells', '0656789015', 'hg.wells2@example.com', 'password118'),
('Jules', 'Verne', '0667890126', 'jules.verne2@example.com', 'password119'),
('Philip', 'Dick', '0678901237', 'philip.dick2@example.com', 'password120'),
('Ray', 'Bradbury', '0689012348', 'ray.bradbury2@example.com', 'password121'),
('Robert', 'Heinlein', '0690123459', 'robert.heinlein2@example.com', 'password122'),
('Frank', 'Herbert', '0612345672', 'frank.herbert2@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Ursula', 'Le Guin', '0623456783', 'ursula.leguin2@example.com', 'password124'),
('William', 'Gibson', '0634567894', 'william.gibson2@example.com', 'password125'),
('Neal', 'Stephenson', '0645678905', 'neal.stephenson2@example.com', 'password126'),
('Margaret', 'Atwood', '0656789016', 'margaret.atwood2@example.com', 'password127'),
('Kurt', 'Vonnegut', '0667890127', 'kurt.vonnegut2@example.com', 'password128'),
('Medecin', 'One', '0612345673', 'medecin.one@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Two', '0623456784', 'medecin.two@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Three', '0634567895', 'medecin.three@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Four', '0645678906', 'medecin.four@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Five', '0656789017', 'medecin.five@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Six', '0667890128', 'medecin.six@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Seven', '0678901239', 'medecin.seven@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Eight', '0689012340', 'medecin.eight@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Nine', '0690123451', 'medecin.nine@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Ten', '0612345674', 'medecin.ten@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Eleven', '0623456785', 'medecin.eleven@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Twelve', '0634567896', 'medecin.twelve@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Thirteen', '0645678907', 'medecin.thirteen@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Fourteen', '0656789018', 'medecin.fourteen@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Fifteen', '0667890129', 'medecin.fifteen@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Sixteen', '0678901240', 'medecin.sixteen@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Seventeen', '0689012351', 'medecin.seventeen@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Eighteen', '0690123462', 'medecin.eighteen@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Nineteen', '0612345685', 'medecin.nineteen@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Twenty', '0623456796', 'medecin.twenty@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwentyOne', '0634567907', 'medecin.twentyone@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwentyTwo', '0645678918', 'medecin.twentytwo@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwentyThree', '0656789029', 'medecin.twentythree@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwentyFour', '0667890130', 'medecin.twentyfour@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwentyFive', '0678901251', 'medecin.twentyfive@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwentySix', '0689012362', 'medecin.twentysix@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwentySeven', '0690123473', 'medecin.twentyseven@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwentyEight', '0612345696', 'medecin.twentyeight@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwentyNine', '0623456807', 'medecin.twentynine@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Thirty', '0634567918', 'medecin.thirty@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'ThirtyOne', '0645678929', 'medecin.thirtyone@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'ThirtyTwo', '0656789030', 'medecin.thirtytwo@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'ThirtyThree', '0667890141', 'medecin.thirtythree@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'ThirtyFour', '0678901262', 'medecin.thirtyfour@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'ThirtyFive', '0689012373', 'medecin.thirtyfive@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'ThirtySix', '0690123484', 'medecin.thirtysix@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'ThirtySeven', '0612345707', 'medecin.thirtyseven@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'ThirtyEight', '0623456818', 'medecin.thirtyeight@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'ThirtyNine', '0634567929', 'medecin.thirtynine@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Forty', '0645678930', 'medecin.forty@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FortyOne', '0656789041', 'medecin.fortyone@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FortyTwo', '0667890152', 'medecin.fortytwo@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FortyThree', '0678901273', 'medecin.fortythree@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FortyFour', '0689012384', 'medecin.fortyfour@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FortyFive', '0690123495', 'medecin.fortyfive@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FortySix', '0612345718', 'medecin.fortysix@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FortySeven', '0623456829', 'medecin.fortyseven@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FortyEight', '0634567930', 'medecin.fortyeight@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FortyNine', '0645678941', 'medecin.fortynine@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Fifty', '0656789052', 'medecin.fifty@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FiftyOne', '0667890163', 'medecin.fiftyone@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FiftyTwo', '0678901284', 'medecin.fiftytwo@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FiftyThree', '0689012395', 'medecin.fiftythree@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FiftyFour', '0690123506', 'medecin.fiftyfour@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FiftyFive', '0612345729', 'medecin.fiftyfive@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FiftySix', '0623456830', 'medecin.fiftysix@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FiftySeven', '0634567941', 'medecin.fiftyseven@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FiftyEight', '0645678952', 'medecin.fiftyeight@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'FiftyNine', '0656789063', 'medecin.fiftynine@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Sixty', '0667890174', 'medecin.sixty@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SixtyOne', '0678901295', 'medecin.sixtyone@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SixtyTwo', '0689012406', 'medecin.sixtytwo@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SixtyThree', '0690123517', 'medecin.sixtythree@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SixtyFour', '0612345730', 'medecin.sixtyfour@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SixtyFive', '0623456841', 'medecin.sixtyfive@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SixtySix', '0634567952', 'medecin.sixtysix@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SixtySeven', '0645678963', 'medecin.sixtyseven@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SixtyEight', '0656789074', 'medecin.sixtyeight@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SixtyNine', '0667890185', 'medecin.sixtynine@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Seventy', '0678901306', 'medecin.seventy@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SeventyOne', '0689012417', 'medecin.seventyone@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SeventyTwo', '0690123528', 'medecin.seventytwo@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SeventyThree', '0612345741', 'medecin.seventythree@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SeventyFour', '0623456852', 'medecin.seventyfour@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SeventyFive', '0634567963', 'medecin.seventyfive@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SeventySix', '0645678974', 'medecin.seventysix@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SeventySeven', '0656789085', 'medecin.seventyseven@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SeventyEight', '0667890196', 'medecin.seventyeight@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'SeventyNine', '0678901317', 'medecin.seventynine@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Eighty', '0689012428', 'medecin.eighty@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'EightyOne', '0690123539', 'medecin.eightyone@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'EightyTwo', '0612345752', 'medecin.eightytwo@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'EightyThree', '0623456863', 'medecin.eightythree@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'EightyFour', '0634567974', 'medecin.eightyfour@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'EightyFive', '0645678985', 'medecin.eightyfive@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'EightySix', '0656789096', 'medecin.eightysix@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'EightySeven', '0667890207', 'medecin.eightyseven@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'EightyEight', '0678901328', 'medecin.eightyeight@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'EightyNine', '0689012439', 'medecin.eightynine@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'Ninety', '0690123540', 'medecin.ninety@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'NinetyOne', '0612345763', 'medecin.ninetyone@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'NinetyTwo', '0623456874', 'medecin.ninetytwo@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'NinetyThree', '0634567985', 'medecin.ninetythree@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'NinetyFour', '0645678996', 'medecin.ninetyfour@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'NinetyFive', '0656789107', 'medecin.ninetyfive@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'NinetySix', '0667890218', 'medecin.ninetysix@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'NinetySeven', '0678901339', 'medecin.ninetyseven@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'NinetyEight', '0689012440', 'medecin.ninetyeight@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'NinetyNine', '0690123551', 'medecin.ninetynine@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundred', '0612345774', 'medecin.onehundred@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredOne', '0623456885', 'medecin.onehundredone@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredTwo', '0634567996', 'medecin.onehundredtwo@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredThree', '0645679007', 'medecin.onehundredthree@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredFour', '0656789118', 'medecin.onehundredfour@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredFive', '0667890229', 'medecin.onehundredfive@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredSix', '0678901340', 'medecin.onehundredsix@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'), 
('Medecin', 'OneHundredSixtySeven', '0612345785', 'medecin.onehundredsixtyseven@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredSixtyEight', '0623456896', 'medecin.onehundredsixtyeight@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredSixtyNine', '0634568007', 'medecin.onehundredsixtynine@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredSeventy', '0645679118', 'medecin.onehundredseventy@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredSeventyOne', '0656789229', 'medecin.onehundredseventyone@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredSeventyTwo', '0667890330', 'medecin.onehundredseventytwo@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredSeventyThree', '0678901441', 'medecin.onehundredseventythree@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredSeventyFour', '0689012552', 'medecin.onehundredseventyfour@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredSeventyFive', '0690123663', 'medecin.onehundredseventyfive@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredSeventySix', '0612345776', 'medecin.onehundredseventysix@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredSeventySeven', '0623456887', 'medecin.onehundredseventyseven@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredSeventyEight', '0634567998', 'medecin.onehundredseventyeight@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredSeventyNine', '0645679009', 'medecin.onehundredseventynine@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredEighty', '0656789110', 'medecin.onehundredeighty@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredEightyOne', '0667890221', 'medecin.onehundredeightyone@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredEightyTwo', '0678901332', 'medecin.onehundredeightytwo@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredEightyThree', '0689012443', 'medecin.onehundredeightythree@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredEightyFour', '0690123554', 'medecin.onehundredeightyfour@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredEightyFive', '0612345665', 'medecin.onehundredeightyfive@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredEightySix', '0623456776', 'medecin.onehundredeightysix@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredEightySeven', '0634567887', 'medecin.onehundredeightyseven@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredEightyEight', '0645678998', 'medecin.onehundredeightyeight@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredEightyNine', '0656789009', 'medecin.onehundredeightynine@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredNinety', '0667890110', 'medecin.onehundredninety@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredNinetyOne', '0678901221', 'medecin.onehundredninetyone@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredNinetyTwo', '0689012332', 'medecin.onehundredninetytwo@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredNinetyThree', '0690123443', 'medecin.onehundredninetythree@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredNinetyFour', '0612345554', 'medecin.onehundredninetyfour@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredNinetyFive', '0623456665', 'medecin.onehundredninetyfive@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredNinetySix', '0634567776', 'medecin.onehundredninetysix@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredNinetySeven', '0645678887', 'medecin.onehundredninetyseven@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredNinetyEight', '0656789998', 'medecin.onehundredninetyeight@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'OneHundredNinetyNine', '0667890009', 'medecin.onehundredninetynine@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundred', '0678901110', 'medecin.twohundred@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredOne', '0689012221', 'medecin.twohundredone@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredTwo', '0690123332', 'medecin.twohundredtwo@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredThree', '0612344443', 'medecin.twohundredthree@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredFour', '0623455554', 'medecin.twohundredfour@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredFive', '0634566665', 'medecin.twohundredfive@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredSix', '0645677776', 'medecin.twohundredsix@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredSeven', '0656788887', 'medecin.twohundredseven@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredEight', '0667899998', 'medecin.twohundredeight@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredNine', '0678900009', 'medecin.twohundrednine@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredTen', '0689011110', 'medecin.twohundredten@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredEleven', '0690122221', 'medecin.twohundredeleven@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredTwelve', '0612343332', 'medecin.twohundredtwelve@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredThirteen', '0623454443', 'medecin.twohundredthirteen@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredFourteen', '0634565554', 'medecin.twohundredfourteen@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredFifteen', '0645676665', 'medecin.twohundredfifteen@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredSixteen', '0656787776', 'medecin.twohundredsixteen@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredSeventeen', '0667898887', 'medecin.twohundredseventeen@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredEighteen', '0678909998', 'medecin.twohundredeighteen@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredNineteen', '0689010009', 'medecin.twohundrednineteen@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredTwenty', '0690121110', 'medecin.twohundredtwenty@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredTwenty', '0690121110', 'medecin.twohundredtwenty@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredTwentyOne', '0612342221', 'medecin.twohundredtwentyone@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredTwentyTwo', '0623453332', 'medecin.twohundredtwentytwo@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredTwentyThree', '0634564443', 'medecin.twohundredtwentythree@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredTwentyFour', '0645675554', 'medecin.twohundredtwentyfour@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS'),
('Medecin', 'TwoHundredTwentyFive', '0656786665', 'medecin.twohundredtwentyfive@example.com', '$2a$12$F1fuVAoMKLBpkAQY1qwhVurbyXjRdB/WPDK8NOZAr4XTpPCPAChNS');


-- Insertion de rôles pour les utilisateurs
INSERT INTO role (id, name) VALUES 
(1, 'ADMIN_CENTRE'),
(2, 'MEDECIN'),
(3, 'PATIENT'),
(4, 'SUPER_ADMIN');




-- Association des utilisateurs avec leurs rôles
INSERT INTO utilisateur_role (utilisateur_id, role_id) VALUES 
(1, 4), -- Jean Dupont est un médecin
(2, 2), -- Marie Curie est un médecin
(3, 2), -- Albert Einstein est un médecin
(4, 2), -- Isaac Newton est un médecin
(5, 2), -- Galileo Galilei est un médecin
(6, 2), -- Charles Darwin est un médecin
(7, 2), -- Nikola Tesla est un médecin
(8, 2), -- Leonardo da Vinci est un médecin
(9, 2), -- Thomas Edison est un médecin
(10, 2), -- Alexander Graham Bell est un médecin
(11, 2), -- Marie Lavoisier est un administrateur
(12, 2), -- Louis Pasteur est un administrateur
(13, 1), -- Gregor Mendel est un administrateur
(14, 1), -- James Watson est un administrateur
(15, 1), -- Francis Crick est un administrateur
(16, 1), -- Rosalind Franklin est un administrateur
(17, 1), -- Ada Lovelace est un administrateur
(18, 1), -- Alan Turing est un administrateur
(19, 1), -- Grace Hopper est un administrateur
(20, 1), -- Katherine Johnson est un administrateur
(21, 1), -- Dorothy Vaughan est un administrateur
(22, 1), -- Mary Jackson est un administrateur
(23, 1), -- Carl Sagan est un administrateur
(24, 1), -- Neil Armstrong est un administrateur
(25, 1), -- Buzz Aldrin est un administrateur
(26, 1), -- Yuri Gagarin est un administrateur
(27, 1), -- Sally Ride est un administrateur
(28, 1), -- Mae Jemison est un administrateur
(29, 1), -- Valentina Tereshkova est un administrateur
(30, 1), -- Chris Hadfield est un administrateur
(31, 3), -- Isaac Asimov est un patient
(32, 3), -- Arthur Clarke est un patient
(33, 3), -- H.G. Wells est un patient
(34, 3), -- Jules Verne est un patient
(35, 3), -- Philip Dick est un patient
(36, 3), -- Ray Bradbury est un patient
(37, 3), -- Robert Heinlein est un patient
(38, 3), -- Frank Herbert est un patient
(39, 3), -- Ursula Le Guin est un patient
(40, 3), -- William Gibson est un patient
(41, 3), -- Neal Stephenson est un patient
(42, 3), -- Margaret Atwood est un patient
(43, 3), -- Kurt Vonnegut est un patient
(44, 3), -- Aldous Huxley est un patient
(45, 3), -- George Orwell est un patient
(46, 3), -- Douglas Adams est un patient
(47, 3), -- Terry Pratchett est un patient
(48, 3), -- Isaac Asimov est un patient
(49, 3), -- Arthur Clarke est un patient
(50, 3), -- H.G. Wells est un patient
(51, 3), -- Jules Verne est un patient
(52, 3), -- Philip Dick est un patient
(53, 3), -- Ray Bradbury est un patient
(54, 3), -- Robert Heinlein est un patient
(55, 3), -- Frank Herbert est un patient
(56, 3), -- Ursula Le Guin est un patient
(57, 3), -- William Gibson est un patient
(58, 3), -- Neal Stephenson est un patient
(59, 3), -- Margaret Atwood est un patient
(60, 3), -- Kurt Vonnegut est un patient;
(61, 2), -- Medecin One est un médecin
(62, 2), -- Medecin Two est un médecin
(63, 2), -- Medecin Three est un médecin
(64, 2), -- Medecin Four est un médecin
(65, 2), -- Medecin Five est un médecin
(66, 2), -- Medecin Six est un médecin
(67, 2), -- Medecin Seven est un médecin
(68, 2), -- Medecin Eight est un médecin
(69, 2), -- Medecin Nine est un médecin
(70, 2), -- Medecin Ten est un médecin
(71, 2), -- Medecin Eleven est un médecin
(72, 2), -- Medecin Twelve est un médecin
(73, 2), -- Medecin Thirteen est un médecin
(74, 2), -- Medecin Fourteen est un médecin
(75, 2), -- Medecin Fifteen est un médecin
(76, 2), -- Medecin Sixteen est un médecin
(77, 2), -- Medecin Seventeen est un médecin
(78, 2), -- Medecin Eighteen est un médecin
(79, 2), -- Medecin Nineteen est un médecin
(80, 2), -- Medecin Twenty est un médecin
(81, 2), -- Medecin TwentyOne est un médecin
(82, 2), -- Medecin TwentyTwo est un médecin
(83, 2), -- Medecin TwentyThree est un médecin
(84, 2), -- Medecin TwentyFour est un médecin
(85, 2), -- Medecin TwentyFive est un médecin
(86, 2), -- Medecin TwentySix est un médecin
(87, 2), -- Medecin TwentySeven est un médecin
(88, 2), -- Medecin TwentyEight est un médecin
(89, 2), -- Medecin TwentyNine est un médecin
(90, 2), -- Medecin Thirty est un médecin
(91, 2), -- Medecin ThirtyOne est un médecin
(92, 2), -- Medecin ThirtyTwo est un médecin
(93, 2), -- Medecin ThirtyThree est un médecin
(94, 2), -- Medecin ThirtyFour est un médecin
(95, 2),
(96, 2),
(97, 2),
(98, 2),
(99, 2),
(100, 2),
(101, 2),
(102, 2),
(103, 2),
(104, 2),
(105, 2),
(106, 2),
(107, 2),
(108, 2),
(109, 2),
(110, 2),
(111, 2),
(112, 2),
(113, 2),
(114, 2),
(115, 2),
(116, 2),
(117, 2),
(118, 2),
(119, 2),
(120, 2),
(121, 2),
(122, 2),
(123, 2),
(124, 2),
(125, 2),
(126, 2),
(127, 2),
(128, 2),
(129, 2),
(130, 2),
(131, 2),
(132, 2),
(133, 2),
(134, 2),
(135, 2),
(136, 2),
(137, 2),
(138, 2),
(139, 2),
(140, 2),
(141, 2),
(142, 2),
(143, 2),
(144, 2),
(145, 2),
(146, 2),
(147, 2),
(148, 2),
(149, 2),
(150, 2),
(151, 2),
(152, 2),
(153, 2),
(154, 2),
(155, 2),
(156, 2),
(157, 2),
(158, 2),
(159, 2),
(160, 2),
(161, 2),
(162, 2),
(163, 2),
(164, 2),
(165, 2),
(166, 2),
(167, 2),
(168, 2),
(169, 2),
(170, 2),
(171, 2),
(172, 2),
(173, 2),
(174, 2),
(175, 2),
(176, 2),
(177, 2),
(178, 2),
(179, 2),
(180, 2),
(181, 2),
(182, 2),
(183, 2),
(184, 2),
(185, 2),
(186, 2),
(187, 2),
(188, 2),
(189, 2),
(190, 2),
(191, 2),
(192, 2),
(193, 2),
(194, 2),
(195, 2),
(196, 2),
(197, 2),
(198, 2),
(199, 2),
(200, 2),
(201, 2),
(202, 2),
(203, 2),
(204, 2),
(205, 2),
(206, 2),
(207, 2),
(208, 2),
(209, 2),
(210, 2),
(211, 2),
(212, 2),
(213, 2),
(214, 2),
(215, 2),
(216, 2),
(217, 2),
(218, 2),
(219, 2),
(220, 2),
(221, 2),
(222, 2),
(223, 2),
(224, 2),
(225, 2);
-- Update the roles for the first 165 users to be doctors
Update utilisateur_role SET role_id = 4 WHERE utilisateur_id = 1;
UPDATE utilisateur_role SET role_id = 2 WHERE utilisateur_id > 1 AND utilisateur_id <= 165;


-- Update the roles for the next 27 users to be patients
UPDATE utilisateur_role SET role_id = 3 WHERE utilisateur_id > 165 AND utilisateur_id <= 181;

-- Update the roles for the remaining 33 users to be administrators
UPDATE utilisateur_role SET role_id = 1 WHERE utilisateur_id > 181;

INSERT INTO administrateur(id,centre_id) VALUES
(182,1),
(183,2),
(184,3),
(185,4),
(186,5),
(187,6),
(188,7),
(189,8),
(190,9),
(191,10),
(192,11),
(193,12),
(194,13),
(195,14),
(196,15),
(197,16),
(198,17),
(199,18),
(200,19),
(201,20),
(202,21),
(203,22),
(204,23),
(205,24),
(206,25),
(207,26),
(208,27),
(209,28),
(210,29),
(211,30),
(212,31),
(213,32),
(214,33),
(215,34),
(216,35),
(217,36),
(218,37),
(219,38),
(220,39),
(221,40),
(222,41),
(223,42),
(224,43);
-- Insertion des médecins en utilisant les ID des utilisateurs précédemment insérés
INSERT INTO medecin (id, centre_id)
VALUES 
(1, 1), (2, 1), (3, 1), (4, 1), (5, 1), 
(6, 2), (7, 2), (8, 2), (9, 2), (10, 2), 
(11, 3), (12, 3), (13, 3), (14, 3), (15, 3),
(16, 4), (17, 4), (18, 4), (19, 4), (20, 4),
(21, 5), (22, 5), (23, 5), (24, 5), (25, 5),
(26, 6), (27, 6), (28, 6), (29, 6), (30, 6),
(31, 7), (32, 7), (33, 7), (34, 7), (35, 7),
(36, 8), (37, 8), (38, 8), (39, 8), (40, 8),
(41, 9), (42, 9), (43, 9), (44, 9), (45, 9),
(46, 10), (47, 10), (48, 10), (49, 10), (50, 10),
(51, 11), (52, 11), (53, 11), (54, 11), (55, 11),
(56, 12), (57, 12), (58, 12), (59, 12), (60, 12),
(61, 13), (62, 13), (63, 13), (64, 13), (65, 13),
(66, 14), (67, 14), (68, 14), (69, 14), (70, 14),
(71, 15), (72, 15), (73, 15), (74, 15), (75, 15),
(76, 16), (77, 16), (78, 16), (79, 16), (80, 16),
(81, 17), (82, 17), (83, 17), (84, 17), (85, 17),
(86, 18), (87, 18), (88, 18), (89, 18), (90, 18),
(91, 19), (92, 19), (93, 19), (94, 19), (95, 19),
(96, 20), (97, 20), (98, 20), (99, 20), (100, 20),
(101, 21), (102, 21), (103, 21), (104, 21), (105, 21),
(106, 22), (107, 22), (108, 22), (109, 22), (110, 22),
(111, 23), (112, 23), (113, 23), (114, 23), (115, 23),
(116, 24), (117, 24), (118, 24), (119, 24), (120, 24),
(121, 25), (122, 25), (123, 25), (124, 25), (125, 25),
(126, 26), (127, 26), (128, 26), (129, 26), (130, 26),
(131, 27), (132, 27), (133, 27), (134, 27), (135, 27),
(136, 28), (137, 28), (138, 28), (139, 28), (140, 28),
(141, 29), (142, 29), (143, 29), (144, 29), (145, 29),
(146, 30), (147, 30), (148, 30), (149, 30), (150, 30),
(151, 31), (152, 31), (153, 31), (154, 31), (155, 31),
(156, 32), (157, 32), (158, 32), (159, 32), (160, 32),
(161, 33), (162, 33), (163, 33), (164, 33), (165, 33);


-- Insertion de patients fictifs
INSERT INTO patient (name, surname, phone_number, email, date_of_birth, centre_id,medecin_id, vaccination_status) VALUES 
('Marie', 'Dubois', '0623481293', 'marie.dubois@example.com', '1988-04-05', 1,1, 'VACCINATED'),
('Lucas', 'Morel', '0658473956', 'lucas.morel@example.com', '1992-11-17', 2, 2,'NOT_VACCINATED'),
('Emma', 'Lefevre', '0639128475', 'emma.lefevre@example.com', '1985-07-25', 3,3, 'VACCINATED'),
('Thomas', 'Garcia', '0645192837', 'thomas.garcia@example.com', '1990-02-11', 4, 4,'NOT_VACCINATED'),
('Julie', 'Martin', '0672198347', 'julie.martin@example.com', '1983-12-14', 5, 5, 'VACCINATED'),
('Antoine', 'Bernard', '0681928476', 'antoine.bernard@example.com', '1994-08-19', 6, 6, 'NOT_VACCINATED'),
('Elodie', 'Dupont', '0627189453', 'elodie.dupont@example.com', '1991-06-21', 7, 7, 'VACCINATED'),
('Maxime', 'Roux', '0653927846', 'maxime.roux@example.com', '1986-09-09', 8, 8,'NOT_VACCINATED'),
('Alice', 'Moreau', '0628947152', 'alice.moreau@example.com', '1989-01-22', 9, 9,'VACCINATED'),
('Nicolas', 'Petit', '0671284739', 'nicolas.petit@example.com', '1987-03-08', 10,10, 'NOT_VACCINATED'),
('Sophie', 'Lemoine', '0628394751', 'sophie.lemoine@example.com', '1993-05-14', 11,11 ,'VACCINATED'),
('Hugo', 'Martinez', '0672938475', 'hugo.martinez@example.com', '1984-10-30', 12,12, 'NOT_VACCINATED'),
('Chloe', 'Thomas', '0639184752', 'chloe.thomas@example.com', '1995-03-12', 13, 13, 'VACCINATED'),
('Louis', 'Robert', '0657283946', 'louis.robert@example.com', '1982-07-19', 14,14, 'NOT_VACCINATED'),
('Camille', 'Richard', '0628394753', 'camille.richard@example.com', '1996-11-23', 15, 15, 'VACCINATED'),
('Leo', 'Durand', '0672938476', 'leo.durand@example.com', '1983-04-17', 16, 16,'NOT_VACCINATED'),
('Manon', 'Dubois', '0639184753', 'manon.dubois@example.com', '1991-09-05', 17, 17, 'VACCINATED'),
('Lucas', 'Morel', '0657283947', 'lucas.morel@example.com', '1987-02-28', 18, 18, 'NOT_VACCINATED'),
('Sarah', 'Lefevre', '0628394754', 'sarah.lefevre@example.com', '1994-06-15', 19, 19,'VACCINATED'),
('Nathan', 'Garcia', '0672938477', 'nathan.garcia@example.com', '1989-12-01', 20, 20,'NOT_VACCINATED'),
('Laura', 'Martin', '0639184754', 'laura.martin@example.com', '1992-08-08', 21, 21, 'VACCINATED'),
('Tom', 'Bernard', '0657283948', 'tom.bernard@example.com', '1985-01-19', 22, 22, 'NOT_VACCINATED'),
('Clara', 'Dupont', '0628394755', 'clara.dupont@example.com', '1990-05-27', 23, 23,'VACCINATED'),
('Paul', 'Roux', '0672938478', 'paul.roux@example.com', '1986-11-14', 24, 24,'NOT_VACCINATED'),
('Emma', 'Moreau', '0639184755', 'emma.moreau@example.com', '1993-03-03', 25, 25, 'VACCINATED'),
('Hugo', 'Petit', '0657283949', 'hugo.petit@example.com', '1988-07-07', 26, 26, 'NOT_VACCINATED'),
('Pauline', 'Girard', '0628394756', 'pauline.girard@example.com', '1990-04-12', 27, 27, 'VACCINATED'),
('Julien', 'Blanc', '0657283950', 'julien.blanc@example.com', '1988-05-23', 28, 28, 'NOT_VACCINATED'),
('Claire', 'Fontaine', '0639184756', 'claire.fontaine@example.com', '1992-06-14', 29, 29, 'VACCINATED'),
('Benoit', 'Dupuis', '0672938480', 'benoit.dupuis@example.com', '1985-07-25', 30, 30, 'NOT_VACCINATED'),
('Amandine', 'Lemoine', '0628394757', 'amandine.lemoine@example.com', '1993-08-16', 31, 31, 'VACCINATED'),
('Guillaume', 'Renaud', '0657283951', 'guillaume.renaud@example.com', '1987-09-27', 32, 32, 'NOT_VACCINATED'),
('Isabelle', 'Gauthier', '0639184757', 'isabelle.gauthier@example.com', '1991-10-18', 33, 33, 'VACCINATED'),
('Damien', 'Perrin', '0672938481', 'damien.perrin@example.com', '1986-11-29', 1, 34, 'NOT_VACCINATED'),
('Sabrina', 'Leroy', '0628394758', 'sabrina.leroy@example.com', '1994-12-10', 2, 35, 'VACCINATED'),
('Vincent', 'Moreau', '0657283952', 'vincent.moreau@example.com', '1989-01-21', 3, 36, 'NOT_VACCINATED'),
('Nathalie', 'Rousseau', '0639184758', 'nathalie.rousseau@example.com', '1990-02-01', 4, 37, 'VACCINATED'),
('Laurent', 'Bertrand', '0672938482', 'laurent.bertrand@example.com', '1988-03-12', 5,38, 'NOT_VACCINATED'),
('Sandrine', 'Fournier', '0628394759', 'sandrine.fournier@example.com', '1992-04-23', 6, 39, 'VACCINATED'),
('Olivier', 'Girard', '0657283953', 'olivier.girard@example.com', '1985-05-04', 7, 40,'NOT_VACCINATED'),
('Celine', 'Dupont', '0639184759', 'celine.dupont@example.com', '1993-06-15', 8, 41,'VACCINATED'),
('Sebastien', 'Lemoine', '0672938483', 'sebastien.lemoine@example.com', '1987-07-26', 9, 42,'NOT_VACCINATED'),
('Stephanie', 'Renaud', '0628394760', 'stephanie.renaud@example.com', '1991-08-06', 10, 43, 'VACCINATED');

-- Insertion de réservations fictives pour les patients 
INSERT INTO reservation (patient_id, centre_id, date_reservation, reservation_status, medecin_id, datestart, dateend, title) VALUES 
(1, 1, '2024-10-30', 'Disponible', 1, '2024-10-30 09:00:00', '2024-10-30 09:30:00', 'Consultation'),
(2, 2, '2024-11-15', 'Disponible', 2, '2024-11-15 10:00:00', '2024-11-15 10:30:00', 'Vaccination'),
(3, 3, '2024-10-25', 'Disponible', 3, '2024-10-25 11:00:00', '2024-10-25 11:30:00', 'Check-up'),
(4, 4, '2024-11-02', 'Disponible', 4, '2024-11-02 12:00:00', '2024-11-02 12:30:00', 'Consultation'),
(5, 5, '2024-12-05', 'CONFIRMED', 5, '2024-12-05 13:00:00', '2024-12-05 13:30:00', 'Vaccination'),
(6, 6, '2024-11-18', 'PENDING', 6, '2024-11-18 14:00:00', '2024-11-18 14:30:00', 'Check-up'),
(7, 7, '2024-12-22', 'CONFIRMED', 7, '2024-12-22 15:00:00', '2024-12-22 15:30:00', 'Consultation'),
(8, 8, '2024-10-28', 'Disponible', 8, '2024-10-28 16:00:00', '2024-10-28 16:30:00', 'Vaccination'),
(9, 9, '2024-11-30', 'CONFIRMED', 9, '2024-11-30 17:00:00', '2024-11-30 17:30:00', 'Check-up'),
(10, 10, '2024-11-20', 'CANCELED', 10, '2024-11-20 18:00:00', '2024-11-20 18:30:00', 'Consultation'),
(11, 11, '2024-12-10', 'PENDING', 11, '2024-12-10 09:00:00', '2024-12-10 09:30:00', 'Vaccination'),
(12, 12, '2024-11-05', 'CONFIRMED', 12, '2024-11-05 10:00:00', '2024-11-05 10:30:00', 'Check-up'),
(13, 13, '2024-12-15', 'PENDING', 13, '2024-12-15 11:00:00', '2024-12-15 11:30:00', 'Consultation'),
(14, 14, '2024-10-31', 'CONFIRMED', 14, '2024-10-31 12:00:00', '2024-10-31 12:30:00', 'Vaccination'),
(15, 15, '2024-11-25', 'PENDING', 15, '2024-11-25 13:00:00', '2024-11-25 13:30:00', 'Check-up'),
(16, 16, '2024-12-01', 'CONFIRMED', 16, '2024-12-01 14:00:00', '2024-12-01 14:30:00', 'Consultation'),
(17, 17, '2024-11-12', 'PENDING', 17, '2024-11-12 15:00:00', '2024-11-12 15:30:00', 'Vaccination'),
(18, 18, '2024-12-20', 'CONFIRMED', 18, '2024-12-20 16:00:00', '2024-12-20 16:30:00', 'Check-up'),
(19, 19, '2024-11-28', 'PENDING', 19, '2024-11-28 17:00:00', '2024-11-28 17:30:00', 'Consultation'),
(20, 20, '2024-12-08', 'CONFIRMED', 20, '2024-12-08 18:00:00', '2024-12-08 18:30:00', 'Vaccination'),
(21, 21, '2024-11-17', 'PENDING', 21, '2024-11-17 09:00:00', '2024-11-17 09:30:00', 'Check-up'),
(22, 22, '2024-12-03', 'CONFIRMED', 22, '2024-12-03 10:00:00', '2024-12-03 10:30:00', 'Consultation'),
(23, 23, '2024-11-08', 'PENDING', 23, '2024-11-08 11:00:00', '2024-11-08 11:30:00', 'Vaccination'),
(24, 24, '2024-12-18', 'CONFIRMED', 24, '2024-12-18 12:00:00', '2024-12-18 12:30:00', 'Check-up'),
(25, 25, '2024-11-23', 'PENDING', 25, '2024-11-23 13:00:00', '2024-11-23 13:30:00', 'Consultation'),
(26, 26, '2024-12-13', 'CONFIRMED', 26, '2024-12-13 14:00:00', '2024-12-13 14:30:00', 'Vaccination'),
(27, 27, '2024-11-03', 'PENDING', 27, '2024-11-03 15:00:00', '2024-11-03 15:30:00', 'Check-up'),
(28, 28, '2024-12-23', 'CONFIRMED', 28, '2024-12-23 16:00:00', '2024-12-23 16:30:00', 'Consultation'),
(29, 29, '2024-11-13', 'PENDING', 29, '2024-11-13 17:00:00', '2024-11-13 17:30:00', 'Vaccination'),
(30, 30, '2024-12-21', 'CONFIRMED', 30, '2024-12-21 18:00:00', '2024-12-21 18:30:00', 'Check-up'),
(31, 31, '2024-11-29', 'PENDING', 31, '2024-11-29 09:00:00', '2024-11-29 09:30:00', 'Consultation'),
(32, 32, '2024-12-09', 'CONFIRMED', 32, '2024-12-09 10:00:00', '2024-12-09 10:30:00', 'Vaccination'),
(33, 33, '2024-11-14', 'PENDING', 33, '2024-11-14 11:00:00', '2024-11-14 11:30:00', 'Check-up'),
(34, 34, '2024-12-19', 'CONFIRMED', 34, '2024-12-19 12:00:00', '2024-12-19 12:30:00', 'Consultation'),
(35, 35, '2024-11-24', 'PENDING', 35, '2024-11-24 13:00:00', '2024-11-24 13:30:00', 'Vaccination'),
(36, 36, '2024-12-14', 'CONFIRMED', 36, '2024-12-14 14:00:00', '2024-12-14 14:30:00', 'Check-up'),
(37, 37, '2024-11-04', 'PENDING', 37, '2024-11-04 15:00:00', '2024-11-04 15:30:00', 'Consultation'),
(38, 38, '2024-12-24', 'CONFIRMED', 38, '2024-12-24 16:00:00', '2024-12-24 16:30:00', 'Vaccination'),
(39, 39, '2024-11-16', 'PENDING', 39, '2024-11-16 17:00:00', '2024-11-16 17:30:00', 'Check-up'),
(40, 40, '2024-12-06', 'CONFIRMED', 40, '2024-12-06 18:00:00', '2024-12-06 18:30:00', 'Consultation'),
(41, 41, '2024-11-26', 'PENDING', 41, '2024-11-26 09:00:00', '2024-11-26 09:30:00', 'Vaccination'),
(42, 42, '2024-12-16', 'CONFIRMED', 42, '2024-12-16 10:00:00', '2024-12-16 10:30:00', 'Check-up'),
(43, 43, '2024-11-01', 'PENDING', 43, '2024-11-01 11:00:00', '2024-11-01 11:30:00', 'Consultation');
