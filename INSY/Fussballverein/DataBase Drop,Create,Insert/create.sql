CREATE TABLE Mannschaft (
 bezeichnung VARCHAR(355) NOT NULL,
 klasse VARCHAR(355) NOT NULL,
 neachstesspiel DATE NOT NULL
);

ALTER TABLE Mannschaft ADD CONSTRAINT PK_Mannschaft PRIMARY KEY (bezeichnung);


CREATE TABLE Person (
 persnr INT NOT NULL,
 vname VARCHAR(30) NOT NULL,
 nname VARCHAR(30) NOT NULL,
 geschlecht VARCHAR(1) NOT NULL,
 gebdat DATE NOT NULL
);

ALTER TABLE Person ADD CONSTRAINT PK_Person PRIMARY KEY (persnr);


CREATE TABLE Spiel (
 datum DATE NOT NULL,
 bezeichnung VARCHAR(355) NOT NULL,
 gegner VARCHAR(355) NOT NULL,
 ergebnis VARCHAR(20) NOT NULL
);

ALTER TABLE Spiel ADD CONSTRAINT PK_Spiel PRIMARY KEY (datum);


CREATE TABLE Spieler (
 persnr INT NOT NULL,
 position VARCHAR(355) NOT NULL,
 gehalt DECIMAL(30) NOT NULL,
 vertragvon DATE NOT NULL,
 vertragbis DATE NOT NULL
);

ALTER TABLE Spieler ADD CONSTRAINT PK_Spieler PRIMARY KEY (persnr);


CREATE TABLE Spielt (
 persnr INT NOT NULL,
 datum DATE NOT NULL,
 dauer FLOAT(30)
);

ALTER TABLE Spielt ADD CONSTRAINT PK_Spielt PRIMARY KEY (persnr,datum);


CREATE TABLE SpielerEigenschaft (
 persnr INT NOT NULL,
 bezeichnung VARCHAR(355) NOT NULL,
 istkapitean VARCHAR(10) NOT NULL,
 nummer CHAR(10)
);

ALTER TABLE SpielerEigenschaft ADD CONSTRAINT PK_SpielerEigenschaft PRIMARY KEY (persnr,bezeichnung);


CREATE TABLE Standort (
 sid INT NOT NULL,
 land VARCHAR(355),
 ort VARCHAR(355) NOT NULL,
 plz VARCHAR(355) NOT NULL
);

ALTER TABLE Standort ADD CONSTRAINT PK_Standort PRIMARY KEY (sid);


CREATE TABLE Trainer (
 persnr INT NOT NULL,
 gehalt DECIMAL(30) NOT NULL,
 vertragsbeginn DATE NOT NULL,
 vertragsende DATE NOT NULL
);

ALTER TABLE Trainer ADD CONSTRAINT PK_Trainer PRIMARY KEY (persnr);


CREATE TABLE TrainerEigenschaft (
 persnr INT NOT NULL,
 bezeichnung VARCHAR(355) NOT NULL,
 istcheftrainer VARCHAR(10) NOT NULL,
 istassistent VARCHAR(10) NOT NULL
);

ALTER TABLE TrainerEigenschaft ADD CONSTRAINT PK_TrainerEigenschaft PRIMARY KEY (persnr,bezeichnung);


CREATE TABLE Angestellter (
 persnr INT NOT NULL,
 gehalt DECIMAL(30) NOT NULL,
 ueberstunden INT NOT NULL,
 email VARCHAR(355) NOT NULL
);

ALTER TABLE Angestellter ADD CONSTRAINT PK_Angestellter PRIMARY KEY (persnr);


CREATE TABLE Mitglied (
 persnr INT NOT NULL,
 beitrag VARCHAR(355) NOT NULL
);

ALTER TABLE Mitglied ADD CONSTRAINT PK_Mitglied PRIMARY KEY (persnr);


CREATE TABLE FanClub (
 persnr INT NOT NULL,
 name VARCHAR(355) NOT NULL,
 sid INT NOT NULL,
 gegruendet DATE NOT NULL,
 istobman CHAR(10)
);

ALTER TABLE FanClub ADD CONSTRAINT PK_FanClub PRIMARY KEY (name,persnr);


CREATE TABLE Betreut (
 persnr INT NOT NULL,
 name VARCHAR(355) NOT NULL,
 anfang DATE NOT NULL,
 ende DATE NOT NULL
);

ALTER TABLE Betreut ADD CONSTRAINT PK_Betreut PRIMARY KEY (persnr,name);

ALTER TABLE Spiel ADD CONSTRAINT FK_Spiel_0 FOREIGN KEY (bezeichnung) REFERENCES Mannschaft (bezeichnung);

ALTER TABLE Spieler ADD CONSTRAINT FK_Spieler_0 FOREIGN KEY (persnr) REFERENCES Person (persnr);

ALTER TABLE Spielt ADD CONSTRAINT FK_Spielt_0 FOREIGN KEY (persnr) REFERENCES Spieler (persnr);
ALTER TABLE Spielt ADD CONSTRAINT FK_Spielt_1 FOREIGN KEY (datum) REFERENCES Spiel (datum);

ALTER TABLE SpielerEigenschaft ADD CONSTRAINT FK_SpielerEigenschaft_0 FOREIGN KEY (persnr) REFERENCES Spieler (persnr);
ALTER TABLE SpielerEigenschaft ADD CONSTRAINT FK_SpielerEigenschaft_1 FOREIGN KEY (bezeichnung) REFERENCES Mannschaft (bezeichnung);

ALTER TABLE Trainer ADD CONSTRAINT FK_Trainer_0 FOREIGN KEY (persnr) REFERENCES Person (persnr);

ALTER TABLE TrainerEigenschaft ADD CONSTRAINT FK_TrainerEigenschaft_0 FOREIGN KEY (persnr) REFERENCES Trainer (persnr);
ALTER TABLE TrainerEigenschaft ADD CONSTRAINT FK_TrainerEigenschaft_1 FOREIGN KEY (bezeichnung) REFERENCES Mannschaft (bezeichnung);

ALTER TABLE Angestellter ADD CONSTRAINT FK_Angestellter_0 FOREIGN KEY (persnr) REFERENCES Person (persnr);

ALTER TABLE Mitglied ADD CONSTRAINT FK_Mitglied_0 FOREIGN KEY (persnr) REFERENCES Person (persnr);

ALTER TABLE FanClub ADD CONSTRAINT FK_FanClub_0 FOREIGN KEY (persnr) REFERENCES Mitglied (persnr);
ALTER TABLE FanClub ADD CONSTRAINT FK_FanClub_1 FOREIGN KEY (sid) REFERENCES Standort (sid);

ALTER TABLE Betreut ADD CONSTRAINT FK_Betreut_0 FOREIGN KEY (persnr) REFERENCES Angestellter (persnr);
ALTER TABLE Betreut ADD CONSTRAINT FK_Betreut_1 FOREIGN KEY (name) REFERENCES FanClub (name);


