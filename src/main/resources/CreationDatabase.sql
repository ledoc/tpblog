DROP DATABASE IF EXISTS blogspring;
CREATE DATABASE blogspring;
USE blogspring;

CREATE TABLE Articles (art_id INT NOT NULL AUTO_INCREMENT,cat_id INT NOT NULL, art_titre VARCHAR(250) NOT NULL, art_chapeau TEXT NOT NULL,
art_contenu TEXT NOT NULL, art_date TIMESTAMP NOT NULL, aut_id INT NOT NULL, art_en_ligne BOOLEAN , PRIMARY KEY (art_id));
CREATE TABLE Commentaires (com_id INT NOT NULL AUTO_INCREMENT, art_id INT NOT NULL, com_nom VARCHAR(250) NOT NULL, com_email VARCHAR(250) NOT NULL,
com_texte TEXT NOT NULL, com_date TIMESTAMP NOT NULL, com_validation BOOLEAN NOT NULL, PRIMARY KEY (com_id));
CREATE TABLE Categories (cat_id INT NOT NULL AUTO_INCREMENT, cat_name VARCHAR(250) NOT NULL, cat_url VARCHAR(250) NOT NULL, PRIMARY KEY (cat_id));
CREATE TABLE Auteurs (aut_id INT NOT NULL AUTO_INCREMENT , aut_nom VARCHAR(250) NOT NULL, aut_prenom VARCHAR(250) NOT NULL,
aut_url VARCHAR(250) NOT NULL, aut_email VARCHAR(250) NOT NULL, PRIMARY KEY (aut_id));

ALTER TABLE Articles ADD CONSTRAINT fk_Articles_Categories_cat_id FOREIGN KEY (cat_id) REFERENCES Categories (cat_id);
ALTER TABLE Articles ADD CONSTRAINT fk_Articles_Auteurs_aut_id FOREIGN KEY (aut_id) REFERENCES Auteurs (aut_id);
ALTER TABLE Commentaires ADD CONSTRAINT fk_Commentaires_Articles_art_id FOREIGN KEY (art_id) REFERENCES Articles (art_id);

INSERT INTO Categories (cat_name, cat_url) VALUES ('Test','http//Test'),('Test2','http//Test2'),('Test3','http//Test3'),('Test4','http//Test4') ;

INSERT INTO Auteurs (aut_nom , aut_prenom ,aut_url , aut_email)
VALUES ('NomTest','PrenomTest','http//NomTest','NomTest@NomTest.test'),('NomTest2','PrenomTest2','http//NomTest2','NomTest2@NomTest.test');

INSERT INTO Articles (cat_id, art_titre , art_chapeau,art_contenu, art_date, aut_id ,art_en_ligne)
VALUES (1,'Test','Test du blog','cet article parme du test','2013-07-22',1,true),(2,'Test2','Test2 du blog','cet article parme du test2','2013-07-23',1,true),
(3,'Test3','Test3 du blog','cet article parme du test3','2013-07-22',2,true),(2,'Test4','Test4 du blog','cet article parme du test4','2013-07-24',1,false);

INSERT INTO Commentaires (art_id , com_nom , com_email ,com_texte, com_date , com_validation)
VALUES (1,'TOTO11','TOTO11@TOTO.TO','cet article parle du test a toto11','2013-07-22',true),(1,'TOTO12','TOTO12@TOTO.TO','cet article parle du test a toto12','2013-07-22',true),
(1,'TOTO13','TOTO13@TOTO.TO','cet article parle du test a toto13','2013-07-22',true),(2,'TOTO21','TOTO21@TOTO.TO','cet article parle du test a toto21','2013-07-23',false),
(2,'TOTO22','TOTO@TOTO.TO','cet article parle du test a toto22','2013-07-23',true),(1,'TOTO14','TOTO14@TOTO.TO','cet article parle du test a toto14','2013-07-23',true);

