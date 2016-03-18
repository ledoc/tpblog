INSERT INTO Categorie (name, url) VALUES ('Test','http//Test'),('Test2','http//Test2'),('Test3','http//Test3'),('Test4','http//Test4') ;

INSERT INTO Auteur (nom , prenom ,url , email)
VALUES ('NomTest','PrenomTest','http//NomTest','NomTest@NomTest.test'),('NomTest2','PrenomTest2','http//NomTest2','NomTest2@NomTest.test');

INSERT INTO Article (titre , chapeau, contenu, creationDate, auteur_id ,enligne)
VALUES ('Test','Test du blog','cet article parme du test','2013-07-22',1,true),('Test2','Test2 du blog','cet article parme du test2','2013-07-23',1,true),
('Test3','Test3 du blog','cet article parme du test3','2013-07-22',2,true),('Test4','Test4 du blog','cet article parme du test4','2013-07-24',1,false),
('Test5','Test5 du blog','cet article parme du test5','2013-07-20',2,true),('Test6','Test6 du blog','cet article parme du test6','2013-06-04',1,true);

INSERT INTO Commentaire (nom , email , texte, creationDate , validation)
VALUES ('TOTO11','TOTO11@TOTO.TO','cet article parle du test a toto11','2013-07-22',true),('TOTO12','TOTO12@TOTO.TO','cet article parle du test a toto12','2013-07-22',true),
('TOTO13','TOTO13@TOTO.TO','cet article parle du test a toto13','2013-07-22',true),('TOTO21','TOTO21@TOTO.TO','cet article parle du test a toto21','2013-07-23',false),
('TOTO22','TOTO@TOTO.TO','cet article parle du test a toto22','2013-07-23',true),('TOTO14','TOTO14@TOTO.TO','cet article parle du test a toto14','2013-07-23',true);

