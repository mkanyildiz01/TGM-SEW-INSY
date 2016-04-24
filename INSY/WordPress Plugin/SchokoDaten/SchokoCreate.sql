DROP DATABASE IF EXISTS Schokoladenfabrik;
CREATE DATABASE Schokoladenfabrik;

DROP TABLE IF EXISTS lagert ; 
DROP TABLE IF EXISTS zeigt ; 
DROP TABLE IF EXISTS erzeugt ; 
DROP TABLE IF EXISTS enthaelt ; 
DROP TABLE IF EXISTS betreut ;
DROP TABLE IF EXISTS bedient ;
DROP TABLE IF EXISTS Maschine CASCADE;
DROP TABLE IF EXISTS Kunstwerk CASCADE;
DROP TABLE IF EXISTS Kunstschau CASCADE;
DROP TABLE IF EXISTS Kuendigung ;
DROP TABLE IF EXISTS Auftrag CASCADE;
DROP TABLE IF EXISTS Person CASCADE;
DROP TABLE IF EXISTS Mitarbeiter CASCADE;
DROP TABLE IF EXISTS Kuenstler CASCADE;
DROP TABLE IF EXISTS Kunde CASCADE;
DROP TABLE IF EXISTS Lager CASCADE;
DROP TABLE IF EXISTS Produkt CASCADE;
DROP TABLE IF EXISTS Standardsortiment CASCADE;

CREATE TABLE Person (
	nummer		INTEGER PRIMARY KEY, 
	vorname		VARCHAR(30), 
	nachname	VARCHAR(30));
	
CREATE TABLE Mitarbeiter(
	nummer		INTEGER PRIMARY KEY REFERENCES Person,
	einstellungsdatum	DATE);
	
CREATE TABLE Kuendigung (
	nummer		INTEGER PRIMARY KEY REFERENCES Mitarbeiter, 
	kuendigungsdatum	DATE);
	
CREATE TABLE Kuenstler (
	nummer		INTEGER PRIMARY KEY REFERENCES Person, 
	bekanntheit	INTEGER,
	CHECK (bekanntheit between 0 and 10)); 
	
CREATE TABLE Produkt (
	nummer		INTEGER PRIMARY KEY, 
	bezeichnung	VARCHAR(100), 
	gewicht		INTEGER);
	
CREATE TABLE Standardsortiment (
	nummer		INTEGER PRIMARY KEY REFERENCES Produkt,
	preis		NUMERIC(7,2), 
	verpackung	VARCHAR(100));
	
CREATE TABLE Kunstwerk (
	nummer		INTEGER PRIMARY KEY REFERENCES Produkt,
	schaetzwert	NUMERIC(10,2));
	
CREATE TABLE Maschine (
	nummer		INTEGER PRIMARY KEY, 
	beschreibung	VARCHAR(30));
	
CREATE TABLE bedient (
	mitnummer	INTEGER REFERENCES Mitarbeiter(nummer), 
	maschnummer	INTEGER REFERENCES Maschine (nummer),
	PRIMARY KEY (mitnummer, maschnummer));
	
CREATE TABLE erzeugt (
	maschnummer	INTEGER REFERENCES Maschine (nummer), 
	pnummer		INTEGER REFERENCES Produkt (nummer),
	PRIMARY KEY (maschnummer, pnummer));
	
CREATE TABLE Lager (
	bezeichnung	VARCHAR(30) PRIMARY KEY, 
	flaeche		NUMERIC(10,2));
	
CREATE TABLE betreut(
	mitnummer	INTEGER PRIMARY KEY REFERENCES Mitarbeiter (nummer), 
	bezeichnung 	VARCHAR(30) REFERENCES Lager);
	
CREATE TABLE lagert (
	bezeichnung	VARCHAR(30) REFERENCES Lager, 
	pnummer		INTEGER REFERENCES Produkt (nummer), 
	menge		INTEGER,
	PRIMARY KEY (bezeichnung, pnummer));
	
CREATE TABLE Kunde (
	firmenname	VARCHAR(30) PRIMARY KEY, 
	adresse		VARCHAR(60), 
	telefonnummer	VARCHAR(20));
	
CREATE TABLE Auftrag (
	firmenname	VARCHAR(50), 
	nummer		INTEGER, 
	datum		DATE, 
	status		VARCHAR(50),
	PRIMARY KEY (firmenname, nummer)); 
	
CREATE TABLE enthaelt (
	firmenname	VARCHAR(30), 
	nummer		INTEGER, 
	pnummer		INTEGER REFERENCES Produkt (nummer), 
	menge		INTEGER,
	FOREIGN KEY (firmenname, nummer) REFERENCES Auftrag (firmenname, nummer),
	PRIMARY KEY (firmenname, nummer, pnummer)); 
	
CREATE TABLE Kunstschau (
	datum		DATE, 
	name		VARCHAR(100), 
	ort		VARCHAR(40), 
	land		VARCHAR(40),
	PRIMARY KEY (datum, name)); 

CREATE TABLE zeigt (
	knummer 	INTEGER REFERENCES Kuenstler (nummer), 
	kunstwerknummer	INTEGER REFERENCES Kunstwerk (nummer), 
	datum		DATE, 
	name		VARCHAR(100),
	platz		INTEGER,
	FOREIGN KEY (datum, name) REFERENCES Kunstschau (datum, name),
	PRIMARY KEY (knummer, kunstwerknummer, datum, name)); 








-- Auftrag
INSERT INTO Auftrag VALUES ('Sweet and Sons', 1, DATE '2007-11-25', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Sweet and Sons', 2, DATE '2008-01-23', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Sweet and Sons', 3, DATE '2008-03-14', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Sweet and Sons', 4, DATE '2008-09-16', 'bereit zur Auslieferung');
INSERT INTO Auftrag VALUES ('Sweet and Sons', 5, DATE '2008-11-15', 'in Produktion');

INSERT INTO Auftrag VALUES ('Schokoladenparadies Erdl', 1, DATE '2007-03-20', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Schokoladenparadies Erdl', 2, DATE '2007-07-12', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Schokoladenparadies Erdl', 3, DATE '2007-12-24', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Schokoladenparadies Erdl', 4, DATE '2008-01-08', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Schokoladenparadies Erdl', 5, DATE '2008-05-19', 'bereit zur Auslieferung');
INSERT INTO Auftrag VALUES ('Schokoladenparadies Erdl', 6, DATE '2008-08-16', 'in Produktion');

INSERT INTO Auftrag VALUES ('Gesuender Leben', 1, DATE '2007-11-13', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Gesuender Leben', 2, DATE '2007-12-13', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Gesuender Leben', 3, DATE '2008-01-18', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Gesuender Leben', 4, DATE '2008-02-27', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Gesuender Leben', 5, DATE '2008-08-28', 'in Produktion');
INSERT INTO Auftrag VALUES ('Gesuender Leben', 6, DATE '2008-09-29', 'in Produktion');

INSERT INTO Auftrag VALUES ('Chocolatier Holler', 1, DATE '2008-07-13', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Chocolatier Holler', 2, DATE '2008-08-15', 'bereit zur Auslieferung');
INSERT INTO Auftrag VALUES ('Chocolatier Holler', 3, DATE '2008-09-19', 'in Produktion');

INSERT INTO Auftrag VALUES ('Ederle', 1, DATE '2007-09-26', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Ederle', 2, DATE '2008-11-10', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Ederle', 3, DATE '2008-05-17', 'bereit zur Auslieferung');
INSERT INTO Auftrag VALUES ('Ederle', 4, DATE '2008-09-19', 'in Produktion');

INSERT INTO Auftrag VALUES ('Megamarkt', 1, DATE '2007-10-20', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Megamarkt', 2, DATE '2007-10-19', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Megamarkt', 3, DATE '2007-11-17', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Megamarkt', 4, DATE '2007-11-19', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Megamarkt', 5, DATE '2007-12-10', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Megamarkt', 6, DATE '2008-03-20', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Megamarkt', 7, DATE '2008-04-27', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Megamarkt', 8, DATE '2008-05-16', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Megamarkt', 9, DATE '2008-07-15', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Megamarkt', 10, DATE '2008-08-29', 'bereit zur Auslieferung');
INSERT INTO Auftrag VALUES ('Megamarkt', 11, DATE '2008-10-01', 'in Produktion');
INSERT INTO Auftrag VALUES ('Megamarkt', 12, DATE '2008-10-08', 'in Vorbereitung');

INSERT INTO Auftrag VALUES ('Naschen, Ja!', 1, DATE '2007-12-18', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Naschen, Ja!', 2, DATE '2007-12-25', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Naschen, Ja!', 3, DATE '2008-10-20', 'in Produktion');
INSERT INTO Auftrag VALUES ('Naschen, Ja!', 4, DATE '2008-11-03', 'in Produktion');
INSERT INTO Auftrag VALUES ('Naschen, Ja!', 5, DATE '2008-11-16', 'in Vorbereitung');

INSERT INTO Auftrag VALUES ('Bitter und Scharf', 1, DATE '2007-01-25', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Bitter und Scharf', 2, DATE '2007-03-10', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Bitter und Scharf', 3, DATE '2007-03-16', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Bitter und Scharf', 4, DATE '2007-03-17', 'abgeschlossen');
INSERT INTO Auftrag VALUES ('Bitter und Scharf', 5, DATE '2008-03-01', 'bereit zur Auslieferung');
INSERT INTO Auftrag VALUES ('Bitter und Scharf', 6, DATE '2008-03-05', 'bereit zur Auslieferung');
INSERT INTO bedient VALUES (02, 0101);
INSERT INTO bedient VALUES (03, 0101);
INSERT INTO bedient VALUES (02, 0102);
INSERT INTO bedient VALUES (03, 0102);
INSERT INTO bedient VALUES (02, 0103);
INSERT INTO bedient VALUES (03, 0103);
INSERT INTO bedient VALUES (02, 0104);
INSERT INTO bedient VALUES (03, 0104);
INSERT INTO bedient VALUES (02, 0105);
INSERT INTO bedient VALUES (03, 0105);
INSERT INTO bedient VALUES (10, 0102);

INSERT INTO bedient VALUES (04, 0201);
INSERT INTO bedient VALUES (04, 0202);
INSERT INTO bedient VALUES (04, 0203);
INSERT INTO bedient VALUES (04, 0204);
INSERT INTO bedient VALUES (04, 0205);
INSERT INTO bedient VALUES (06, 0201);
INSERT INTO bedient VALUES (06, 0202);
INSERT INTO bedient VALUES (07, 0203);
INSERT INTO bedient VALUES (07, 0204);
INSERT INTO bedient VALUES (09, 0205);

INSERT INTO bedient VALUES (10, 0301);
INSERT INTO bedient VALUES (11, 0302);
INSERT INTO bedient VALUES (10, 0303);
INSERT INTO bedient VALUES (10, 0304);
INSERT INTO bedient VALUES (11, 0305);
INSERT INTO bedient VALUES (10, 0306);
INSERT INTO bedient VALUES (11, 0307);
INSERT INTO bedient VALUES (10, 0308);
INSERT INTO bedient VALUES (15, 0303);
INSERT INTO bedient VALUES (15, 0304);
INSERT INTO bedient VALUES (15, 0305);
INSERT INTO bedient VALUES (16, 0306);
INSERT INTO bedient VALUES (16, 0307);
INSERT INTO bedient VALUES (16, 0308);
INSERT INTO bedient VALUES (19, 0308);

INSERT INTO bedient VALUES (22, 0401);
INSERT INTO bedient VALUES (23, 0401);
INSERT INTO bedient VALUES (24, 0401);
INSERT INTO bedient VALUES (22, 0402);
INSERT INTO bedient VALUES (23, 0402);
INSERT INTO bedient VALUES (25, 0402);

INSERT INTO bedient VALUES (30, 0501);
INSERT INTO bedient VALUES (30, 0502);
INSERT INTO bedient VALUES (30, 0503);
INSERT INTO bedient VALUES (30, 0504);
INSERT INTO bedient VALUES (30, 0505);
INSERT INTO bedient VALUES (31, 0503);
INSERT INTO bedient VALUES (31, 0504);
INSERT INTO bedient VALUES (31, 0505);

INSERT INTO bedient VALUES (32, 0601);

INSERT INTO bedient VALUES (37, 0101);
INSERT INTO bedient VALUES (37, 0102);
INSERT INTO bedient VALUES (37, 0103);
INSERT INTO bedient VALUES (37, 0104);
INSERT INTO bedient VALUES (37, 0105);
INSERT INTO bedient VALUES (37, 0201);
INSERT INTO bedient VALUES (37, 0202);
INSERT INTO bedient VALUES (37, 0203);
INSERT INTO bedient VALUES (37, 0204);
INSERT INTO bedient VALUES (37, 0205);
INSERT INTO bedient VALUES (37, 0301);
INSERT INTO bedient VALUES (37, 0302);
INSERT INTO bedient VALUES (37, 0303);
INSERT INTO bedient VALUES (37, 0304);
INSERT INTO bedient VALUES (37, 0305);
INSERT INTO bedient VALUES (37, 0306);
INSERT INTO bedient VALUES (37, 0307);
INSERT INTO bedient VALUES (37, 0308);
INSERT INTO bedient VALUES (37, 0401);
INSERT INTO bedient VALUES (37, 0402);
INSERT INTO bedient VALUES (37, 0501);
INSERT INTO bedient VALUES (37, 0502);
INSERT INTO bedient VALUES (37, 0503);
INSERT INTO bedient VALUES (37, 0504);
INSERT INTO bedient VALUES (37, 0505);
INSERT INTO bedient VALUES (37, 0601);
INSERT INTO bedient VALUES (37, 0602);

INSERT INTO bedient VALUES (35, 0101);
INSERT INTO bedient VALUES (35, 0102);
INSERT INTO bedient VALUES (35, 0103);
INSERT INTO bedient VALUES (35, 0104);
INSERT INTO bedient VALUES (35, 0105);
INSERT INTO bedient VALUES (35, 0201);
INSERT INTO bedient VALUES (35, 0202);
INSERT INTO bedient VALUES (35, 0203);
INSERT INTO bedient VALUES (35, 0204);
INSERT INTO bedient VALUES (35, 0205);
INSERT INTO bedient VALUES (35, 0301);
INSERT INTO bedient VALUES (35, 0302);
INSERT INTO bedient VALUES (35, 0303);
INSERT INTO bedient VALUES (35, 0304);
INSERT INTO bedient VALUES (35, 0305);
INSERT INTO bedient VALUES (35, 0306);
INSERT INTO bedient VALUES (35, 0307);
INSERT INTO bedient VALUES (35, 0308);
INSERT INTO bedient VALUES (35, 0401);
INSERT INTO bedient VALUES (35, 0402);
INSERT INTO bedient VALUES (35, 0501);
INSERT INTO bedient VALUES (35, 0502);
INSERT INTO bedient VALUES (35, 0503);
INSERT INTO bedient VALUES (35, 0504);
INSERT INTO bedient VALUES (35, 0505);
INSERT INTO bedient VALUES (35, 0601);
INSERT INTO bedient VALUES (35, 0602);
-- betreut
INSERT INTO betreut VALUES (37, 'Abstellraum');
INSERT INTO betreut VALUES (25, 'Zwischenlager Klein');
INSERT INTO betreut VALUES (15, 'Zwischenlager Gross');
INSERT INTO betreut VALUES (21, 'Zwischenlager Gross');
INSERT INTO betreut VALUES (30, 'Zwischenlager Gross');
INSERT INTO betreut VALUES (10, 'Reservelager');
INSERT INTO betreut VALUES (07, 'Zwischenlager Mittel');
INSERT INTO betreut VALUES (22, 'Zwischenlager Mittel');
INSERT INTO betreut VALUES (02, 'Area 51');
INSERT INTO betreut VALUES (06, 'Area 51');
INSERT INTO betreut VALUES (16, 'Area 51');


-- 3, 4, 9, 19, 23, 29, 31, 32, 35 arbeiten noch an nichts

-- enthaelt
INSERT INTO enthaelt VALUES ('Sweet and Sons', 1, 001, 1000);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 1, 003, 500);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 1, 005, 500);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 1, 023, 200);

INSERT INTO enthaelt VALUES ('Sweet and Sons', 2, 001, 800);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 2, 003, 800);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 2, 005, 300);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 2, 007, 300);

INSERT INTO enthaelt VALUES ('Sweet and Sons', 3, 001, 200);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 3, 002, 1000);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 3, 003, 200);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 3, 004, 1000);

INSERT INTO enthaelt VALUES ('Sweet and Sons', 4, 001, 400);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 4, 002, 400);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 4, 003, 400);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 4, 004, 400);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 4, 005, 100);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 4, 006, 100);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 4, 007, 100);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 4, 008, 100);

INSERT INTO enthaelt VALUES ('Sweet and Sons', 5, 023, 1000);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 5, 024, 500);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 5, 026, 500);
INSERT INTO enthaelt VALUES ('Sweet and Sons', 5, 027, 300);


INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 1, 019, 300);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 1, 020, 300);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 1, 021, 300);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 1, 022, 300);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 1, 023, 300);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 1, 001, 500);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 1, 002, 300);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 1, 003, 500);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 1, 004, 300);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 1, 005, 500);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 1, 006, 300);

INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 2, 007, 300);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 2, 008, 300);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 2, 009, 300);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 2, 010, 300);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 2, 001, 150);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 2, 003, 150);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 2, 005, 150);


INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 3, 023, 150);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 3, 024, 150);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 3, 025, 150);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 3, 026, 150);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 3, 027, 150);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 3, 104, 1);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 3, 105, 1);

INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 4, 011, 300);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 4, 012, 120);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 4, 013, 150);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 4, 014, 100);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 4, 015, 120);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 4, 016, 170);

INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 5, 001, 400);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 5, 002, 150);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 5, 003, 400);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 5, 004, 150);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 5, 005, 400);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 5, 006, 150);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 5, 007, 400);

INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 6, 016, 400);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 6, 017, 400);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 6, 018, 400);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 6, 028, 200);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 6, 029, 200);
INSERT INTO enthaelt VALUES ('Schokoladenparadies Erdl', 6, 030, 350);


INSERT INTO enthaelt VALUES ('Gesuender Leben', 1, 023, 700);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 1, 024, 500);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 1, 025, 200);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 1, 026, 600);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 1, 027, 280);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 1, 017, 10000);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 1, 029, 300);

INSERT INTO enthaelt VALUES ('Gesuender Leben', 2, 011, 700);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 2, 012, 500);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 2, 013, 200);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 2, 014, 600);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 2, 015, 280);

INSERT INTO enthaelt VALUES ('Gesuender Leben', 3, 017, 12000);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 3, 114, 1);

INSERT INTO enthaelt VALUES ('Gesuender Leben', 4, 002, 400);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 4, 004, 500);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 4, 006, 200);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 4, 008, 600);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 4, 010, 290);

INSERT INTO enthaelt VALUES ('Gesuender Leben', 5, 001, 800);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 5, 003, 800);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 5, 005, 800);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 5, 007, 800);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 5, 009, 800);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 5, 028, 1300);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 5, 030, 1300);

INSERT INTO enthaelt VALUES ('Gesuender Leben', 6, 002, 500);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 6, 004, 300);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 6, 006, 120);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 6, 008, 300);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 6, 010, 300);
INSERT INTO enthaelt VALUES ('Gesuender Leben', 6, 029, 700);


INSERT INTO enthaelt VALUES ('Chocolatier Holler', 1, 101, 1);
INSERT INTO enthaelt VALUES ('Chocolatier Holler', 1, 102, 1);
INSERT INTO enthaelt VALUES ('Chocolatier Holler', 1, 103, 1);
INSERT INTO enthaelt VALUES ('Chocolatier Holler', 1, 015, 40);

INSERT INTO enthaelt VALUES ('Chocolatier Holler', 2, 107, 1);
INSERT INTO enthaelt VALUES ('Chocolatier Holler', 2, 108, 1);
INSERT INTO enthaelt VALUES ('Chocolatier Holler', 2, 005, 100);

INSERT INTO enthaelt VALUES ('Chocolatier Holler', 3, 006, 20);
INSERT INTO enthaelt VALUES ('Chocolatier Holler', 3, 003, 60);


INSERT INTO enthaelt VALUES ('Ederle', 1, 001, 400);
INSERT INTO enthaelt VALUES ('Ederle', 1, 002, 400);
INSERT INTO enthaelt VALUES ('Ederle', 1, 003, 400);
INSERT INTO enthaelt VALUES ('Ederle', 1, 004, 400);
INSERT INTO enthaelt VALUES ('Ederle', 1, 005, 400);
INSERT INTO enthaelt VALUES ('Ederle', 1, 006, 400);
INSERT INTO enthaelt VALUES ('Ederle', 1, 007, 400);
INSERT INTO enthaelt VALUES ('Ederle', 1, 008, 400);
INSERT INTO enthaelt VALUES ('Ederle', 1, 009, 400);
INSERT INTO enthaelt VALUES ('Ederle', 1, 010, 400);
INSERT INTO enthaelt VALUES ('Ederle', 1, 016, 700);
INSERT INTO enthaelt VALUES ('Ederle', 1, 017, 700);
INSERT INTO enthaelt VALUES ('Ederle', 1, 018, 700);

INSERT INTO enthaelt VALUES ('Ederle', 2, 023, 300);
INSERT INTO enthaelt VALUES ('Ederle', 2, 026, 100);
INSERT INTO enthaelt VALUES ('Ederle', 2, 027, 600);
INSERT INTO enthaelt VALUES ('Ederle', 2, 030, 700);

INSERT INTO enthaelt VALUES ('Ederle', 3, 001, 400);
INSERT INTO enthaelt VALUES ('Ederle', 3, 002, 400);
INSERT INTO enthaelt VALUES ('Ederle', 3, 003, 400);
INSERT INTO enthaelt VALUES ('Ederle', 3, 004, 400);
INSERT INTO enthaelt VALUES ('Ederle', 3, 005, 400);
INSERT INTO enthaelt VALUES ('Ederle', 3, 006, 400);
INSERT INTO enthaelt VALUES ('Ederle', 3, 007, 400);
INSERT INTO enthaelt VALUES ('Ederle', 3, 008, 400);
INSERT INTO enthaelt VALUES ('Ederle', 3, 009, 400);
INSERT INTO enthaelt VALUES ('Ederle', 3, 010, 400);
INSERT INTO enthaelt VALUES ('Ederle', 3, 016, 700);
INSERT INTO enthaelt VALUES ('Ederle', 3, 017, 700);
INSERT INTO enthaelt VALUES ('Ederle', 3, 018, 700);

INSERT INTO enthaelt VALUES ('Ederle', 4, 001, 400);
INSERT INTO enthaelt VALUES ('Ederle', 4, 002, 400);
INSERT INTO enthaelt VALUES ('Ederle', 4, 003, 400);
INSERT INTO enthaelt VALUES ('Ederle', 4, 004, 400);
INSERT INTO enthaelt VALUES ('Ederle', 4, 005, 400);
INSERT INTO enthaelt VALUES ('Ederle', 4, 006, 400);
INSERT INTO enthaelt VALUES ('Ederle', 4, 007, 400);
INSERT INTO enthaelt VALUES ('Ederle', 4, 008, 400);
INSERT INTO enthaelt VALUES ('Ederle', 4, 009, 400);
INSERT INTO enthaelt VALUES ('Ederle', 4, 010, 400);
INSERT INTO enthaelt VALUES ('Ederle', 4, 011, 300);
INSERT INTO enthaelt VALUES ('Ederle', 4, 012, 300);
INSERT INTO enthaelt VALUES ('Ederle', 4, 013, 300);


INSERT INTO enthaelt VALUES ('Megamarkt', 1, 001, 5000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 002, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 003, 5000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 004, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 005, 5000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 006, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 007, 5000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 008, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 009, 5000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 010, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 011, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 012, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 013, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 014, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 015, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 016, 8000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 017, 8000);
INSERT INTO enthaelt VALUES ('Megamarkt', 1, 018, 8000);

INSERT INTO enthaelt VALUES ('Megamarkt', 2, 001, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 2, 002, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 2, 003, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 2, 004, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 2, 005, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 2, 006, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 2, 007, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 2, 008, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 2, 009, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 2, 010, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 2, 011, 800);
INSERT INTO enthaelt VALUES ('Megamarkt', 2, 012, 800);
INSERT INTO enthaelt VALUES ('Megamarkt', 2, 013, 800);
INSERT INTO enthaelt VALUES ('Megamarkt', 2, 014, 800);
INSERT INTO enthaelt VALUES ('Megamarkt', 2, 015, 800);

INSERT INTO enthaelt VALUES ('Megamarkt', 3, 001, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 3, 002, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 3, 003, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 3, 004, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 3, 005, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 3, 006, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 3, 007, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 3, 008, 2000);

INSERT INTO enthaelt VALUES ('Megamarkt', 4, 023, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 4, 024, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 4, 025, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 4, 026, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 4, 027, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 4, 105, 5);

INSERT INTO enthaelt VALUES ('Megamarkt', 5, 001, 3500);
INSERT INTO enthaelt VALUES ('Megamarkt', 5, 003, 3500);
INSERT INTO enthaelt VALUES ('Megamarkt', 5, 005, 3500);
INSERT INTO enthaelt VALUES ('Megamarkt', 5, 007, 3500);
INSERT INTO enthaelt VALUES ('Megamarkt', 5, 009, 3500);
INSERT INTO enthaelt VALUES ('Megamarkt', 5, 023, 3000);
INSERT INTO enthaelt VALUES ('Megamarkt', 5, 024, 3000);
INSERT INTO enthaelt VALUES ('Megamarkt', 5, 025, 3000);
INSERT INTO enthaelt VALUES ('Megamarkt', 5, 026, 3000);
INSERT INTO enthaelt VALUES ('Megamarkt', 5, 027, 3000);

INSERT INTO enthaelt VALUES ('Megamarkt', 6, 001, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 6, 002, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 6, 003, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 6, 004, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 6, 005, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 6, 006, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 6, 007, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 6, 008, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 6, 009, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 6, 010, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 6, 019, 3500);
INSERT INTO enthaelt VALUES ('Megamarkt', 6, 020, 3500);
INSERT INTO enthaelt VALUES ('Megamarkt', 6, 021, 3500);
INSERT INTO enthaelt VALUES ('Megamarkt', 6, 022, 3500);

INSERT INTO enthaelt VALUES ('Megamarkt', 7, 001, 5000);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 002, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 003, 5000);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 004, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 005, 5000);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 006, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 007, 5000);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 008, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 009, 5000);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 010, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 011, 1500);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 012, 1500);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 013, 1500);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 014, 1500);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 015, 1500);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 028, 1000);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 029, 1000);
INSERT INTO enthaelt VALUES ('Megamarkt', 7, 030, 1000);

INSERT INTO enthaelt VALUES ('Megamarkt', 8, 001, 3500);
INSERT INTO enthaelt VALUES ('Megamarkt', 8, 003, 3500);
INSERT INTO enthaelt VALUES ('Megamarkt', 8, 005, 3500);
INSERT INTO enthaelt VALUES ('Megamarkt', 8, 007, 3500);
INSERT INTO enthaelt VALUES ('Megamarkt', 8, 009, 3500);

INSERT INTO enthaelt VALUES ('Megamarkt', 9, 016, 1700);
INSERT INTO enthaelt VALUES ('Megamarkt', 9, 017, 1700);
INSERT INTO enthaelt VALUES ('Megamarkt', 9, 018, 1400);
INSERT INTO enthaelt VALUES ('Megamarkt', 9, 001, 1600);
INSERT INTO enthaelt VALUES ('Megamarkt', 9, 003, 1600);
INSERT INTO enthaelt VALUES ('Megamarkt', 9, 005, 1600);
INSERT INTO enthaelt VALUES ('Megamarkt', 9, 007, 1600);
INSERT INTO enthaelt VALUES ('Megamarkt', 9, 009, 1600);

INSERT INTO enthaelt VALUES ('Megamarkt', 10, 001, 1700);
INSERT INTO enthaelt VALUES ('Megamarkt', 10, 003, 1700);
INSERT INTO enthaelt VALUES ('Megamarkt', 10, 005, 1700);
INSERT INTO enthaelt VALUES ('Megamarkt', 10, 007, 1700);
INSERT INTO enthaelt VALUES ('Megamarkt', 10, 009, 1700);
INSERT INTO enthaelt VALUES ('Megamarkt', 10, 016, 3800);
INSERT INTO enthaelt VALUES ('Megamarkt', 10, 017, 3800);
INSERT INTO enthaelt VALUES ('Megamarkt', 10, 018, 3800);

INSERT INTO enthaelt VALUES ('Megamarkt', 11, 001, 5000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 002, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 003, 5000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 004, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 005, 5000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 006, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 007, 5000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 008, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 009, 5000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 010, 4000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 011, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 012, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 013, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 014, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 015, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 016, 8000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 017, 8000);
INSERT INTO enthaelt VALUES ('Megamarkt', 11, 018, 8000);

INSERT INTO enthaelt VALUES ('Megamarkt', 12, 001, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 12, 002, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 12, 003, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 12, 004, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 12, 005, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 12, 006, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 12, 007, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 12, 008, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 12, 009, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 12, 010, 2000);
INSERT INTO enthaelt VALUES ('Megamarkt', 12, 011, 800);
INSERT INTO enthaelt VALUES ('Megamarkt', 12, 012, 800);
INSERT INTO enthaelt VALUES ('Megamarkt', 12, 013, 800);
INSERT INTO enthaelt VALUES ('Megamarkt', 12, 014, 800);
INSERT INTO enthaelt VALUES ('Megamarkt', 12, 015, 800);


INSERT INTO enthaelt VALUES ('Naschen, Ja!', 1, 001, 300);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 1, 016, 500);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 1, 017, 500);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 1, 018, 600);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 1, 003, 300);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 1, 005, 300);

INSERT INTO enthaelt VALUES ('Naschen, Ja!', 2, 011, 250);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 2, 012, 250);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 2, 013, 250);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 2, 014, 250);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 2, 015, 250);

INSERT INTO enthaelt VALUES ('Naschen, Ja!', 3, 001, 400);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 3, 002, 400);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 3, 003, 400);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 3, 007, 400);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 3, 005, 400);

INSERT INTO enthaelt VALUES ('Naschen, Ja!', 4, 004, 120);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 4, 006, 120);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 4, 008, 120);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 4, 009, 300);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 4, 010, 120);

INSERT INTO enthaelt VALUES ('Naschen, Ja!', 5, 023, 500);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 5, 024, 300);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 5, 025, 80);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 5, 026, 230);
INSERT INTO enthaelt VALUES ('Naschen, Ja!', 5, 027, 120);

INSERT INTO enthaelt VALUES ('Bitter und Scharf', 1, 112, 1);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 1, 002, 350);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 1, 004, 350);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 1, 006, 350);

INSERT INTO enthaelt VALUES ('Bitter und Scharf', 2, 019, 200);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 2, 020, 180);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 2, 021, 120);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 2, 022, 150);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 2, 001, 300);

INSERT INTO enthaelt VALUES ('Bitter und Scharf', 3, 113, 1);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 3, 019, 30);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 3, 020, 40);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 3, 021, 80);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 3, 022, 100);

INSERT INTO enthaelt VALUES ('Bitter und Scharf', 4, 022, 40);

INSERT INTO enthaelt VALUES ('Bitter und Scharf', 5, 114, 1);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 5, 019, 230);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 5, 020, 200);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 5, 021, 180);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 5, 022, 200);

INSERT INTO enthaelt VALUES ('Bitter und Scharf', 6, 019, 30);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 6, 020, 40);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 6, 021, 80);
INSERT INTO enthaelt VALUES ('Bitter und Scharf', 6, 022, 30);
-- erzeugt
INSERT INTO erzeugt VALUES (0101, 001);
INSERT INTO erzeugt VALUES (0102, 001);
INSERT INTO erzeugt VALUES (0205, 001);
INSERT INTO erzeugt VALUES (0305, 001);
INSERT INTO erzeugt VALUES (0501, 001);

INSERT INTO erzeugt VALUES (0101, 002);
INSERT INTO erzeugt VALUES (0102, 002);
INSERT INTO erzeugt VALUES (0203, 002);
INSERT INTO erzeugt VALUES (0204, 002);
INSERT INTO erzeugt VALUES (0306, 002);
INSERT INTO erzeugt VALUES (0503, 002);

INSERT INTO erzeugt VALUES (0103, 003);
INSERT INTO erzeugt VALUES (0104, 003);
INSERT INTO erzeugt VALUES (0202, 003);
INSERT INTO erzeugt VALUES (0302, 003);
INSERT INTO erzeugt VALUES (0502, 003);

INSERT INTO erzeugt VALUES (0103, 004);
INSERT INTO erzeugt VALUES (0104, 004);
INSERT INTO erzeugt VALUES (0201, 004);
INSERT INTO erzeugt VALUES (0202, 004);
INSERT INTO erzeugt VALUES (0308, 004);
INSERT INTO erzeugt VALUES (0504, 004);

INSERT INTO erzeugt VALUES (0105, 005);
INSERT INTO erzeugt VALUES (0104, 005);
INSERT INTO erzeugt VALUES (0204, 005);
INSERT INTO erzeugt VALUES (0305, 005);
INSERT INTO erzeugt VALUES (0501, 005);

INSERT INTO erzeugt VALUES (0105, 006);
INSERT INTO erzeugt VALUES (0104, 006);
INSERT INTO erzeugt VALUES (0202, 006);
INSERT INTO erzeugt VALUES (0203, 006);
INSERT INTO erzeugt VALUES (0306, 006);
INSERT INTO erzeugt VALUES (0503, 006);

INSERT INTO erzeugt VALUES (0101, 007);
INSERT INTO erzeugt VALUES (0102, 007);
INSERT INTO erzeugt VALUES (0202, 007);
INSERT INTO erzeugt VALUES (0304, 007);
INSERT INTO erzeugt VALUES (0502, 007);

INSERT INTO erzeugt VALUES (0101, 008);
INSERT INTO erzeugt VALUES (0102, 008);
INSERT INTO erzeugt VALUES (0202, 008);
INSERT INTO erzeugt VALUES (0304, 008);
INSERT INTO erzeugt VALUES (0504, 008);

INSERT INTO erzeugt VALUES (0103, 009);
INSERT INTO erzeugt VALUES (0104, 009);
INSERT INTO erzeugt VALUES (0203, 009);
INSERT INTO erzeugt VALUES (0305, 009);
INSERT INTO erzeugt VALUES (0501, 009);

INSERT INTO erzeugt VALUES (0103, 010);
INSERT INTO erzeugt VALUES (0104, 010);
INSERT INTO erzeugt VALUES (0203, 010);
INSERT INTO erzeugt VALUES (0305, 010);
INSERT INTO erzeugt VALUES (0503, 010);

INSERT INTO erzeugt VALUES (0101, 011);
INSERT INTO erzeugt VALUES (0201, 011);
INSERT INTO erzeugt VALUES (0305, 011);
INSERT INTO erzeugt VALUES (0401, 011);
INSERT INTO erzeugt VALUES (0505, 011);

INSERT INTO erzeugt VALUES (0101, 012);
INSERT INTO erzeugt VALUES (0201, 012);
INSERT INTO erzeugt VALUES (0304, 012);
INSERT INTO erzeugt VALUES (0401, 012);
INSERT INTO erzeugt VALUES (0505, 012);

INSERT INTO erzeugt VALUES (0102, 013);
INSERT INTO erzeugt VALUES (0201, 013);
INSERT INTO erzeugt VALUES (0305, 013);
INSERT INTO erzeugt VALUES (0402, 013);
INSERT INTO erzeugt VALUES (0505, 013);

INSERT INTO erzeugt VALUES (0102, 014);
INSERT INTO erzeugt VALUES (0201, 014);
INSERT INTO erzeugt VALUES (0305, 014);
INSERT INTO erzeugt VALUES (0402, 014);
INSERT INTO erzeugt VALUES (0505, 014);

INSERT INTO erzeugt VALUES (0101, 015);
INSERT INTO erzeugt VALUES (0102, 015);
INSERT INTO erzeugt VALUES (0201, 015);
INSERT INTO erzeugt VALUES (0305, 015);
INSERT INTO erzeugt VALUES (0401, 015);
INSERT INTO erzeugt VALUES (0402, 015);
INSERT INTO erzeugt VALUES (0505, 015);

INSERT INTO erzeugt VALUES (0103, 016);
INSERT INTO erzeugt VALUES (0202, 016);
INSERT INTO erzeugt VALUES (0203, 016);
INSERT INTO erzeugt VALUES (0301, 016);
INSERT INTO erzeugt VALUES (0305, 016);
INSERT INTO erzeugt VALUES (0401, 016);
INSERT INTO erzeugt VALUES (0501, 016);

INSERT INTO erzeugt VALUES (0104, 017);
INSERT INTO erzeugt VALUES (0202, 017);
INSERT INTO erzeugt VALUES (0204, 017);
INSERT INTO erzeugt VALUES (0302, 017);
INSERT INTO erzeugt VALUES (0305, 017);
INSERT INTO erzeugt VALUES (0402, 017);
INSERT INTO erzeugt VALUES (0502, 017);

INSERT INTO erzeugt VALUES (0104, 018);
INSERT INTO erzeugt VALUES (0202, 018);
INSERT INTO erzeugt VALUES (0204, 018);
INSERT INTO erzeugt VALUES (0301, 018);
INSERT INTO erzeugt VALUES (0304, 018);
INSERT INTO erzeugt VALUES (0402, 018);
INSERT INTO erzeugt VALUES (0502, 018);

INSERT INTO erzeugt VALUES (0101, 019);
INSERT INTO erzeugt VALUES (0202, 019);
INSERT INTO erzeugt VALUES (0303, 019);
INSERT INTO erzeugt VALUES (0505, 019);
INSERT INTO erzeugt VALUES (0601, 019);

INSERT INTO erzeugt VALUES (0101, 020);
INSERT INTO erzeugt VALUES (0202, 020);
INSERT INTO erzeugt VALUES (0307, 020);
INSERT INTO erzeugt VALUES (0505, 020);
INSERT INTO erzeugt VALUES (0602, 020);

INSERT INTO erzeugt VALUES (0101, 021);
INSERT INTO erzeugt VALUES (0202, 021);
INSERT INTO erzeugt VALUES (0307, 021);
INSERT INTO erzeugt VALUES (0505, 021);
INSERT INTO erzeugt VALUES (0602, 021);

INSERT INTO erzeugt VALUES (0102, 022);
INSERT INTO erzeugt VALUES (0203, 022);
INSERT INTO erzeugt VALUES (0304, 022);
INSERT INTO erzeugt VALUES (0505, 022);
INSERT INTO erzeugt VALUES (0602, 022);

INSERT INTO erzeugt VALUES (0101, 023);
INSERT INTO erzeugt VALUES (0202, 023);
INSERT INTO erzeugt VALUES (0303, 023);
INSERT INTO erzeugt VALUES (0505, 023);
INSERT INTO erzeugt VALUES (0601, 023);

INSERT INTO erzeugt VALUES (0101, 024);
INSERT INTO erzeugt VALUES (0202, 024);
INSERT INTO erzeugt VALUES (0307, 024);
INSERT INTO erzeugt VALUES (0505, 024);
INSERT INTO erzeugt VALUES (0602, 024);

INSERT INTO erzeugt VALUES (0101, 025);
INSERT INTO erzeugt VALUES (0202, 025);
INSERT INTO erzeugt VALUES (0307, 025);
INSERT INTO erzeugt VALUES (0505, 025);
INSERT INTO erzeugt VALUES (0602, 025);

INSERT INTO erzeugt VALUES (0103, 026);
INSERT INTO erzeugt VALUES (0203, 026);
INSERT INTO erzeugt VALUES (0304, 026);
INSERT INTO erzeugt VALUES (0505, 026);
INSERT INTO erzeugt VALUES (0602, 026);

INSERT INTO erzeugt VALUES (0104, 027);
INSERT INTO erzeugt VALUES (0204, 027);
INSERT INTO erzeugt VALUES (0305, 027);
INSERT INTO erzeugt VALUES (0501, 027);

INSERT INTO erzeugt VALUES (0104, 028);
INSERT INTO erzeugt VALUES (0105, 028);
INSERT INTO erzeugt VALUES (0202, 028);
INSERT INTO erzeugt VALUES (0301, 028);
INSERT INTO erzeugt VALUES (0302, 028);
INSERT INTO erzeugt VALUES (0502, 028);
INSERT INTO erzeugt VALUES (0601, 028);

INSERT INTO erzeugt VALUES (0104, 029);
INSERT INTO erzeugt VALUES (0203, 029);
INSERT INTO erzeugt VALUES (0306, 029);
INSERT INTO erzeugt VALUES (0401, 029);
INSERT INTO erzeugt VALUES (0501, 029);
INSERT INTO erzeugt VALUES (0602, 029);

INSERT INTO erzeugt VALUES (0104, 030);
INSERT INTO erzeugt VALUES (0105, 030);
INSERT INTO erzeugt VALUES (0201, 030);
INSERT INTO erzeugt VALUES (0202, 030);
INSERT INTO erzeugt VALUES (0303, 030);
INSERT INTO erzeugt VALUES (0304, 030);
INSERT INTO erzeugt VALUES (0501, 030);

INSERT INTO erzeugt VALUES (0104, 031);
INSERT INTO erzeugt VALUES (0203, 031);
INSERT INTO erzeugt VALUES (0306, 031);
INSERT INTO erzeugt VALUES (0401, 031);
INSERT INTO erzeugt VALUES (0501, 031);
INSERT INTO erzeugt VALUES (0602, 031);

INSERT INTO erzeugt VALUES (0105, 101);
INSERT INTO erzeugt VALUES (0201, 101);
INSERT INTO erzeugt VALUES (0301, 101);

INSERT INTO erzeugt VALUES (0105, 102);
INSERT INTO erzeugt VALUES (0201, 102);
INSERT INTO erzeugt VALUES (0301, 102);

INSERT INTO erzeugt VALUES (0105, 103);
INSERT INTO erzeugt VALUES (0201, 103);
INSERT INTO erzeugt VALUES (0301, 103);

INSERT INTO erzeugt VALUES (0105, 104);
INSERT INTO erzeugt VALUES (0201, 104);
INSERT INTO erzeugt VALUES (0301, 104);

INSERT INTO erzeugt VALUES (0105, 105);
INSERT INTO erzeugt VALUES (0201, 105);
INSERT INTO erzeugt VALUES (0301, 105);

INSERT INTO erzeugt VALUES (0105, 106);
INSERT INTO erzeugt VALUES (0201, 106);
INSERT INTO erzeugt VALUES (0301, 106);
INSERT INTO erzeugt VALUES (0401, 106);
INSERT INTO erzeugt VALUES (0601, 106);

INSERT INTO erzeugt VALUES (0105, 107);
INSERT INTO erzeugt VALUES (0201, 107);
INSERT INTO erzeugt VALUES (0301, 107);

INSERT INTO erzeugt VALUES (0105, 108);
INSERT INTO erzeugt VALUES (0201, 108);
INSERT INTO erzeugt VALUES (0301, 108);

INSERT INTO erzeugt VALUES (0105, 109);
INSERT INTO erzeugt VALUES (0201, 109);
INSERT INTO erzeugt VALUES (0301, 109);

INSERT INTO erzeugt VALUES (0105, 110);
INSERT INTO erzeugt VALUES (0201, 110);
INSERT INTO erzeugt VALUES (0301, 110);

INSERT INTO erzeugt VALUES (0105, 111);
INSERT INTO erzeugt VALUES (0201, 111);
INSERT INTO erzeugt VALUES (0301, 111);
INSERT INTO erzeugt VALUES (0402, 111);

INSERT INTO erzeugt VALUES (0105, 112);
INSERT INTO erzeugt VALUES (0201, 112);
INSERT INTO erzeugt VALUES (0301, 112);

INSERT INTO erzeugt VALUES (0105, 113);
INSERT INTO erzeugt VALUES (0201, 113);
INSERT INTO erzeugt VALUES (0301, 113);

INSERT INTO erzeugt VALUES (0105, 114);
INSERT INTO erzeugt VALUES (0201, 114);
INSERT INTO erzeugt VALUES (0301, 114);
INSERT INTO erzeugt VALUES (0401, 114);

INSERT INTO erzeugt VALUES (0105, 115);
INSERT INTO erzeugt VALUES (0201, 115);
INSERT INTO erzeugt VALUES (0301, 115);
-- Kuendigung

INSERT INTO Kuendigung VALUES (11, DATE '2008-10-30');
INSERT INTO Kuendigung VALUES (24, DATE '2004-08-30');
INSERT INTO Kuendigung VALUES (01, DATE '2004-09-30');
INSERT INTO Kuendigung VALUES (05, DATE '2003-01-31');
INSERT INTO Kuendigung VALUES (08, DATE '2003-01-31');
INSERT INTO Kuendigung VALUES (17, DATE '2007-04-30');
INSERT INTO Kuendigung VALUES (26, DATE '2004-06-30');
INSERT INTO Kuendigung VALUES (27, DATE '2004-06-30');
INSERT INTO Kuendigung VALUES (28, DATE '2005-12-31');

-- 11, 24 (sind Kuenstler und Mitarbeiter), 01, 05, 08, 17, 26, 27, 28 bereits wieder gekuendigt 
-- Kuenstler
INSERT INTO Kuenstler VALUES (10, 10);
INSERT INTO Kuenstler VALUES (11, 3);
INSERT INTO Kuenstler VALUES (12, 3);
INSERT INTO Kuenstler VALUES (14, 0);
INSERT INTO Kuenstler VALUES (24, 1);
INSERT INTO Kuenstler VALUES (30, 10);
INSERT INTO Kuenstler VALUES (33, 8);
INSERT INTO Kuenstler VALUES (34, 7);
INSERT INTO Kuenstler VALUES (36, 7);
INSERT INTO Kuenstler VALUES (38, 4);
INSERT INTO Kuenstler VALUES (39, 9);
INSERT INTO Kuenstler VALUES (40, 1);

-- 10, 11, 24, 30 Kuenstler und Mitarbeiter
-- Kunde
INSERT INTO Kunde VALUES ('Sweet and Sons', 'An der Donau 13, 1919 Unterberg', '00437654321');
INSERT INTO Kunde VALUES ('Schokoladenparadies Erdl', 'Marktweg 17A/5, 1087 Sobendorf', '004309090987');
INSERT INTO Kunde VALUES ('Gesuender Leben', 'Wadenstrasse 144, 3878 Kellersdorf', '00435148326');
INSERT INTO Kunde VALUES ('Chocolatier Holler', 'Bahnstrasse 10, 7658 Kolm', '00432020361');
INSERT INTO Kunde VALUES ('Ederle', 'Hanseweg 15, 9292 Neustadt', '00436868655');
INSERT INTO Kunde VALUES ('Megamarkt', 'An der Donau 14, 1919 Unterberg', '00437650215');
INSERT INTO Kunde VALUES ('Naschen, Ja!', 'Wohlstrasse 198, 2210 Erlstadt', '00433968758');
INSERT INTO Kunde VALUES ('Bitter und Scharf', 'Obere Landstrasse 184, 330 Umland', '00433624111');
-- Kunstschau
INSERT INTO Kunstschau VALUES (DATE '2006-03-05', 'Die Besten Schokolademacher', 'Graz', 'Oesterreich');
INSERT INTO Kunstschau VALUES (DATE '2007-03-04', 'Die Besten Schokolademacher', 'Graz', 'Oesterreich');
INSERT INTO Kunstschau VALUES (DATE '2008-03-02', 'Die Besten Schokolademacher', 'Graz', 'Oesterreich');

INSERT INTO Kunstschau VALUES (DATE '2007-08-24', 'Kunstschau 2007', 'Salzburg', 'Oesterreich');
INSERT INTO Kunstschau VALUES (DATE '2008-08-26', 'Kunstschau 2008', 'Innsbruck', 'Oesterreich');

INSERT INTO Kunstschau VALUES (DATE '2006-05-21', 'Internactional Chocolate Contest', 'Hobart', 'Australien');
INSERT INTO Kunstschau VALUES (DATE '2007-05-20', 'Internactional Chocolate Contest', 'New York', 'USA');
INSERT INTO Kunstschau VALUES (DATE '2008-05-18', 'Internactional Chocolate Contest', 'Athen', 'Griechenland');

INSERT INTO Kunstschau VALUES (DATE '2006-11-16', 'Europaeischer Suessigkeitenkongress', 'Oslo', 'Norwegen');
INSERT INTO Kunstschau VALUES (DATE '2007-11-18', 'Europaeischer Suessigkeitenkongress', 'Wien', 'Oesterreich');
INSERT INTO Kunstschau VALUES (DATE '2008-11-19', 'Europaeischer Suessigkeitenkongress', 'Muenchen', 'Deutschland');

INSERT INTO Kunstschau VALUES (DATE '2007-12-23', 'Die Schoensten Weihnachtsmotive', 'Bern', 'Schweiz');


-- Kunstwerk
INSERT INTO Kunstwerk VALUES (101, 1800);
INSERT INTO Kunstwerk VALUES (102, 2490);
INSERT INTO Kunstwerk VALUES (103, 3650);

INSERT INTO Kunstwerk VALUES (104, 3100);
INSERT INTO Kunstwerk VALUES (105, 2400);
INSERT INTO Kunstwerk VALUES (106, 85.50);

INSERT INTO Kunstwerk VALUES (107, 1800);
INSERT INTO Kunstwerk VALUES (108, 3790);
INSERT INTO Kunstwerk VALUES (109, 7850);

INSERT INTO Kunstwerk VALUES (110, 1366.60);
INSERT INTO Kunstwerk VALUES (111, 6900);

INSERT INTO Kunstwerk VALUES (112, 2600);
INSERT INTO Kunstwerk VALUES (113, 5325);
INSERT INTO Kunstwerk VALUES (114, 8890);

INSERT INTO Kunstwerk VALUES (115, 1459.99); 
INSERT INTO Kunstwerk VALUES (116, 9500.00);

-- Lager
INSERT INTO Lager VALUES ('Abstellraum', 30);
INSERT INTO Lager VALUES ('Zwischenlager Klein', 150);
INSERT INTO Lager VALUES ('Zwischenlager Gross', 1600);
INSERT INTO Lager VALUES ('Reservelager', 600);
INSERT INTO Lager VALUES ('Zwischenlager Mittel', 600);
INSERT INTO Lager VALUES ('Speziallager', 500);
INSERT INTO Lager VALUES ('Area 51', 510);

-- lagert
INSERT INTO lagert VALUES ('Zwischenlager Gross', 001, 10000);
INSERT INTO lagert VALUES ('Zwischenlager Gross', 002, 5000);
INSERT INTO lagert VALUES ('Zwischenlager Gross', 003, 10000);
INSERT INTO lagert VALUES ('Zwischenlager Gross', 004, 5000);
INSERT INTO lagert VALUES ('Zwischenlager Gross', 005, 8000);
INSERT INTO lagert VALUES ('Zwischenlager Gross', 006, 6500);
INSERT INTO lagert VALUES ('Zwischenlager Gross', 007, 3000);
INSERT INTO lagert VALUES ('Zwischenlager Gross', 008, 4000);
INSERT INTO lagert VALUES ('Zwischenlager Gross', 009, 7000);
INSERT INTO lagert VALUES ('Zwischenlager Gross', 010, 1500);
INSERT INTO lagert VALUES ('Zwischenlager Gross', 011, 800);
INSERT INTO lagert VALUES ('Zwischenlager Gross', 012, 780);
INSERT INTO lagert VALUES ('Zwischenlager Gross', 013, 330);
INSERT INTO lagert VALUES ('Zwischenlager Gross', 014, 880);
INSERT INTO lagert VALUES ('Zwischenlager Gross', 015, 1500);

INSERT INTO lagert VALUES ('Zwischenlager Mittel', 023, 1700);
INSERT INTO lagert VALUES ('Zwischenlager Mittel', 024, 1500);
INSERT INTO lagert VALUES ('Zwischenlager Mittel', 025, 1200);
INSERT INTO lagert VALUES ('Zwischenlager Mittel', 026, 2000);
INSERT INTO lagert VALUES ('Zwischenlager Mittel', 027, 300);
INSERT INTO lagert VALUES ('Zwischenlager Mittel', 001, 6000);
INSERT INTO lagert VALUES ('Zwischenlager Mittel', 003, 6000);
INSERT INTO lagert VALUES ('Zwischenlager Mittel', 005, 3500);

INSERT INTO lagert VALUES ('Zwischenlager Klein', 028, 7000);
INSERT INTO lagert VALUES ('Zwischenlager Klein', 029, 6000);
INSERT INTO lagert VALUES ('Zwischenlager Klein', 030, 5500);
INSERT INTO lagert VALUES ('Zwischenlager Klein', 016, 4400);
INSERT INTO lagert VALUES ('Zwischenlager Klein', 017, 3900);
INSERT INTO lagert VALUES ('Zwischenlager Klein', 018, 2200);

INSERT INTO lagert VALUES ('Reservelager', 019, 180);
INSERT INTO lagert VALUES ('Reservelager', 020, 245);
INSERT INTO lagert VALUES ('Reservelager', 021, 10);
INSERT INTO lagert VALUES ('Reservelager', 022, 340);
INSERT INTO lagert VALUES ('Reservelager', 028, 400);
INSERT INTO lagert VALUES ('Reservelager', 016, 500);

INSERT INTO lagert VALUES ('Speziallager', 002, 2500);
INSERT INTO lagert VALUES ('Speziallager', 004, 2500);
INSERT INTO lagert VALUES ('Speziallager', 006, 2500);
INSERT INTO lagert VALUES ('Speziallager', 008, 2500);
INSERT INTO lagert VALUES ('Speziallager', 010, 1);
INSERT INTO lagert VALUES ('Speziallager', 101, 1);
INSERT INTO lagert VALUES ('Speziallager', 102, 1);
INSERT INTO lagert VALUES ('Speziallager', 103, 1);
INSERT INTO lagert VALUES ('Speziallager', 107, 1);
INSERT INTO lagert VALUES ('Speziallager', 009, 2);
INSERT INTO lagert VALUES ('Speziallager', 012, 5);
INSERT INTO lagert VALUES ('Speziallager', 013, 1);
INSERT INTO lagert VALUES ('Speziallager', 014, 1);

INSERT INTO lagert VALUES ('Abstellraum', 104, 1);
INSERT INTO lagert VALUES ('Abstellraum', 106, 1);
INSERT INTO lagert VALUES ('Abstellraum', 110, 1);
INSERT INTO lagert VALUES ('Abstellraum', 111, 1);
INSERT INTO lagert VALUES ('Abstellraum', 022, 34);


INSERT INTO Maschine VALUES (0101, 'Mischer');
INSERT INTO Maschine VALUES (0102, 'Mischer');
INSERT INTO Maschine VALUES (0103, 'Mischer');
INSERT INTO Maschine VALUES (0104, 'Mischer');
INSERT INTO Maschine VALUES (0105, 'Mischer');

INSERT INTO Maschine VALUES (0201, 'Heizwerk klein');
INSERT INTO Maschine VALUES (0202, 'Heizwerk mittel');
INSERT INTO Maschine VALUES (0203, 'Heizwerk mittel');
INSERT INTO Maschine VALUES (0204, 'Heizwerk mittel');
INSERT INTO Maschine VALUES (0205, 'Heizwerk gross');

INSERT INTO Maschine VALUES (0301, 'Tank 30L');
INSERT INTO Maschine VALUES (0302, 'Tank 50L');
INSERT INTO Maschine VALUES (0303, 'Tank 100L');
INSERT INTO Maschine VALUES (0304, 'Tank 100L');
INSERT INTO Maschine VALUES (0305, 'Tank 100L');
INSERT INTO Maschine VALUES (0306, 'Tank 250L');
INSERT INTO Maschine VALUES (0307, 'Tank 250L');
INSERT INTO Maschine VALUES (0308, 'Tank 500L');

INSERT INTO Maschine VALUES (0401, 'Fuellanlage 1');
INSERT INTO Maschine VALUES (0402, 'Fuellanlage 2');

INSERT INTO Maschine VALUES (0501, 'Verpacker Typ I');
INSERT INTO Maschine VALUES (0502, 'Verpacker Typ I');
INSERT INTO Maschine VALUES (0503, 'Verpacker Typ II');
INSERT INTO Maschine VALUES (0504, 'Verpacker Typ II');
INSERT INTO Maschine VALUES (0505, 'Verpacker Typ III');

INSERT INTO Maschine VALUES (0601, 'Sekundaerverpacker');
INSERT INTO Maschine VALUES (0602, 'Sekundaerverpacker');
-- Mitarbeiter
INSERT INTO Mitarbeiter VALUES (01, DATE '1998-10-01');
INSERT INTO Mitarbeiter VALUES (02, DATE '1998-10-01');
INSERT INTO Mitarbeiter VALUES (03, DATE '1998-10-01');
INSERT INTO Mitarbeiter VALUES (04, DATE '1998-10-01');
INSERT INTO Mitarbeiter VALUES (05, DATE '1998-12-01');
INSERT INTO Mitarbeiter VALUES (06, DATE '1998-12-01');
INSERT INTO Mitarbeiter VALUES (07, DATE '1998-12-01');
INSERT INTO Mitarbeiter VALUES (08, DATE '1999-04-01');
INSERT INTO Mitarbeiter VALUES (09, DATE '1999-04-01');
INSERT INTO Mitarbeiter VALUES (10, DATE '2000-10-01');
INSERT INTO Mitarbeiter VALUES (11, DATE '2000-10-01');
INSERT INTO Mitarbeiter VALUES (15, DATE '2000-10-01');
INSERT INTO Mitarbeiter VALUES (16, DATE '2001-05-01');
INSERT INTO Mitarbeiter VALUES (17, DATE '2001-05-01');
INSERT INTO Mitarbeiter VALUES (19, DATE '2001-06-01');
INSERT INTO Mitarbeiter VALUES (21, DATE '2002-06-01');
INSERT INTO Mitarbeiter VALUES (22, DATE '2003-09-01');
INSERT INTO Mitarbeiter VALUES (23, DATE '2003-09-01');
INSERT INTO Mitarbeiter VALUES (24, DATE '2003-09-01');
INSERT INTO Mitarbeiter VALUES (25, DATE '2003-12-01');
INSERT INTO Mitarbeiter VALUES (26, DATE '2004-01-01');
INSERT INTO Mitarbeiter VALUES (27, DATE '2004-01-01');
INSERT INTO Mitarbeiter VALUES (28, DATE '2005-01-01');
INSERT INTO Mitarbeiter VALUES (29, DATE '2006-05-01');
INSERT INTO Mitarbeiter VALUES (30, DATE '2006-05-01');
INSERT INTO Mitarbeiter VALUES (31, DATE '2007-01-01');
INSERT INTO Mitarbeiter VALUES (32, DATE '2007-08-01');
INSERT INTO Mitarbeiter VALUES (35, DATE '2008-09-01');
INSERT INTO Mitarbeiter VALUES (37, DATE '2008-10-01');

-- 13, 18, 20 weder Kuenstler noch Mitarbeiter
-- 10, 11, 24, 30 Kuenstler und Mitarbeiter
-- 11, 24 (sind Kuenstler und Mitarbeiter), 01, 05, 08, 17, 26, 27, 28 bereits wieder gekuendigt -- Person
INSERT INTO Person VALUES (01, 'Fabian', 'Blake');
INSERT INTO Person VALUES (02, 'Thamia', 'Jakobs');
INSERT INTO Person VALUES (03, 'Stefan', 'Fober'); 
INSERT INTO Person VALUES (04, 'Justus', 'Goller');
INSERT INTO Person VALUES (05, 'Harald', 'Neubauer');
INSERT INTO Person VALUES (06, 'Konstantin', 'Seger');
INSERT INTO Person VALUES (07, 'Paolo', 'Tollmer');
INSERT INTO Person VALUES (08, 'Erich', 'Westerbruck');
INSERT INTO Person VALUES (09, 'Wolfgang', 'Bank');
INSERT INTO Person VALUES (10, 'Jochen', 'Ullner');
INSERT INTO Person VALUES (11, 'Aurelius', 'Richter');
INSERT INTO Person VALUES (12, 'Julia', 'Titzian');
INSERT INTO Person VALUES (13, 'Ina', 'Ertl');
INSERT INTO Person VALUES (14, 'Paul', 'Holler');
INSERT INTO Person VALUES (15, 'Hubert', 'Backmann');
INSERT INTO Person VALUES (16, 'Robert', 'Backmann');
INSERT INTO Person VALUES (17, 'Ines', 'Lachmann');
INSERT INTO Person VALUES (18, 'Andrea', 'Wallner');
INSERT INTO Person VALUES (19, 'Wolfgang', 'Bruck');
INSERT INTO Person VALUES (20, 'Rolf', 'Humeier');
INSERT INTO Person VALUES (21, 'Peter', 'Gollmer');
INSERT INTO Person VALUES (22, 'Olav', 'Lock'); 
INSERT INTO Person VALUES (23, 'Jack', 'Bauer');
INSERT INTO Person VALUES (24, 'Alexandra', 'Brandl');
INSERT INTO Person VALUES (25, 'Sabine', 'Steffen');
INSERT INTO Person VALUES (26, 'Tilli', 'Ottner'); 
INSERT INTO Person VALUES (27, 'Reinhard', 'Koller'); 
INSERT INTO Person VALUES (28, 'Andreas', 'Uller');  
INSERT INTO Person VALUES (29, 'Peter', 'Saga'); 
INSERT INTO Person VALUES (30, 'Jack', 'Sawyer');
INSERT INTO Person VALUES (31, 'Perry', 'Koch');
INSERT INTO Person VALUES (32, 'Ernst', 'Poller'); 
INSERT INTO Person VALUES (33, 'Paul', 'Steffen');
INSERT INTO Person VALUES (34, 'Gerry', 'Fisch');
INSERT INTO Person VALUES (35, 'Reini', 'Mandelson');
INSERT INTO Person VALUES (36, 'Ute', 'Beck');
INSERT INTO Person VALUES (37, 'Karl', 'Umser');
INSERT INTO Person VALUES (38, 'Julia', 'Ertl');
INSERT INTO Person VALUES (39, 'Gerlinde', 'Hochbauer');
INSERT INTO Person VALUES (40, 'Paul', 'Holler');
-- Produkt
INSERT INTO Produkt VALUES (001, 'Tafel Milchschokolade klein', 150);
INSERT INTO Produkt VALUES (002, 'Tafel Milchschokolade gross', 300);
INSERT INTO Produkt VALUES (003, 'Tafel Weisse Schokolade klein', 150);
INSERT INTO Produkt VALUES (004, 'Tafel Weisse Schokolade gross', 300);
INSERT INTO Produkt VALUES (005, 'Tafel Dunkle Schokolade klein', 150);
INSERT INTO Produkt VALUES (006, 'Tafel Dunkle Schokolade gross', 300);
INSERT INTO Produkt VALUES (007, 'Tafel Milchschokolade mit Nuessen klein', 150);
INSERT INTO Produkt VALUES (008, 'Tafel Milchschokolade mit Nuessen gross', 300);
INSERT INTO Produkt VALUES (009, 'Tafel Weisse Schokolade mit Nuessen klein', 150);
INSERT INTO Produkt VALUES (010, 'Tafel Weisse Schokolade mit Nuessen gross', 300);

INSERT INTO Produkt VALUES (011, 'Pralinen mit Kirschfuellung', 250);
INSERT INTO Produkt VALUES (012, 'Pralinen mit Nussfuellung', 250);
INSERT INTO Produkt VALUES (013, 'Pralinen mit Nougatfuellung', 250);
INSERT INTO Produkt VALUES (014, 'Pralinen mit Marzipanfuellung', 250);
INSERT INTO Produkt VALUES (015, 'Pralinen gemischt', 250);

INSERT INTO Produkt VALUES (016, 'Kids Bar', 60);
INSERT INTO Produkt VALUES (017, 'Energy Bar', 80);
INSERT INTO Produkt VALUES (018, 'Fruchtriegel', 80);

INSERT INTO Produkt VALUES (019, 'Osterhase klein', 80);
INSERT INTO Produkt VALUES (020, 'Osterhase mittel', 140);
INSERT INTO Produkt VALUES (021, 'Osterhase gross', 230);
INSERT INTO Produkt VALUES (022, 'Osterlamm', 120);

INSERT INTO Produkt VALUES (023, 'Schokonikolo klein', 80);
INSERT INTO Produkt VALUES (024, 'Schokonikolo mittel', 140);
INSERT INTO Produkt VALUES (025, 'Schokonikolo gross', 230);
INSERT INTO Produkt VALUES (026, 'Weihnachtlicher Baumbehang', 350);
INSERT INTO Produkt VALUES (027, 'Schokoschirme', 210);

INSERT INTO Produkt VALUES (028, 'Mika Fender', 70);
INSERT INTO Produkt VALUES (029, 'Pluto', 90);
INSERT INTO Produkt VALUES (030, 'NandNs', 150);
INSERT INTO Produkt VALUES (031, 'Pluto', 180);



INSERT INTO Produkt VALUES (101, 'Der vitruvianische Mensch', 1370);
INSERT INTO Produkt VALUES (102, 'Das letzte Abendmahl', 2256);
INSERT INTO Produkt VALUES (103, 'Michelangelo''s David', 2547);

INSERT INTO Produkt VALUES (104, 'Weihnachtskrippe', 4605);
INSERT INTO Produkt VALUES (105, 'Weihnachtslandschaft', 3590);
INSERT INTO Produkt VALUES (106, 'Gluecksschwein Rosa', 1400);

INSERT INTO Produkt VALUES (107, 'Schokoladenskulptur "Der Schwan"', 2300);
INSERT INTO Produkt VALUES (108, 'Schokoladenskulptur "Das Piano"', 5120);
INSERT INTO Produkt VALUES (109, 'Schokoladenskulptur "Angst"', 1760);

INSERT INTO Produkt VALUES (110, 'Der Schrei', 2279);
INSERT INTO Produkt VALUES (111, '"Die Zauberfloete" - Ein Genuss mit besonderer Note', 3150);

INSERT INTO Produkt VALUES (112, 'Vereintes Europa', 1390);
INSERT INTO Produkt VALUES (113, 'Mona Lisa', 3070);
INSERT INTO Produkt VALUES (114, 'Die Anatomie des Menschen', 5380);

INSERT INTO Produkt VALUES (115, 'Wien bei Nacht', 4380);
INSERT INTO Produkt VALUES (116, 'Suesze Apokalypse', 1760);




INSERT INTO Standardsortiment VALUES (001, 0.99, 'Alufolie + Papier bedruckt');
INSERT INTO Standardsortiment VALUES (002, 1.49, 'Alufolie + Papier bedruckt');
INSERT INTO Standardsortiment VALUES (003, 0.99, 'Alufolie + Papier bedruckt');
INSERT INTO Standardsortiment VALUES (004, 1.49, 'Alufolie + Papier bedruckt');
INSERT INTO Standardsortiment VALUES (005, 0.99, 'Alufolie + Papier bedruckt');
INSERT INTO Standardsortiment VALUES (006, 1.49, 'Alufolie + Papier bedruckt');
INSERT INTO Standardsortiment VALUES (007, 0.99, 'Alufolie + Papier bedruckt');
INSERT INTO Standardsortiment VALUES (008, 1.49, 'Alufolie + Papier bedruckt');
INSERT INTO Standardsortiment VALUES (009, 0.99, 'Alufolie + Papier bedruckt');
INSERT INTO Standardsortiment VALUES (010, 1.49, 'Alufolie + Papier bedruckt');

INSERT INTO Standardsortiment VALUES (011, 2.49, 'Kartonschachtel');
INSERT INTO Standardsortiment VALUES (012, 2.49, 'Kartonschachtel');
INSERT INTO Standardsortiment VALUES (013, 2.49, 'Kartonschachtel');
INSERT INTO Standardsortiment VALUES (014, 2.49, 'Kartonschachtel');
INSERT INTO Standardsortiment VALUES (015, 2.49, 'Kartonschachtel');

INSERT INTO Standardsortiment VALUES (016, 1.29, 'Plastikfolie bedruckt');
INSERT INTO Standardsortiment VALUES (017, 1.59, 'Plastikfolie bedruckt');
INSERT INTO Standardsortiment VALUES (018, 0.99, 'Plastikfolie bedruckt');

INSERT INTO Standardsortiment VALUES (019, 1.29, 'Alufolie bedruckt');
INSERT INTO Standardsortiment VALUES (020, 1.49, 'Alufolie bedruckt');
INSERT INTO Standardsortiment VALUES (021, 1.99, 'Alufolie bedruckt');
INSERT INTO Standardsortiment VALUES (022, 1.39, 'Alufolie bedruckt');

INSERT INTO Standardsortiment VALUES (023, 1.29, 'Alufolie bedruckt');
INSERT INTO Standardsortiment VALUES (024, 1.49, 'Alufolie bedruckt');
INSERT INTO Standardsortiment VALUES (025, 1.99, 'Alufolie bedruckt');
INSERT INTO Standardsortiment VALUES (026, 3.99, 'Kartonschachtel');
INSERT INTO Standardsortiment VALUES (027, 2.49, 'Plastiksackerl');

INSERT INTO Standardsortiment VALUES (028, 0.80, 'Plastikfolie bedruckt');
INSERT INTO Standardsortiment VALUES (029, 0.79, 'Plastikfolie bedruckt');
INSERT INTO Standardsortiment VALUES (030, 1.79, 'Plastiksackerl');
-- zeigt
INSERT INTO zeigt VALUES (10, 101, DATE '2006-03-05', 'Die Besten Schokolademacher', 2);
INSERT INTO zeigt VALUES (12, 107, DATE '2006-03-05', 'Die Besten Schokolademacher', 6);
INSERT INTO zeigt VALUES (34, 111, DATE '2006-03-05', 'Die Besten Schokolademacher', 4);
INSERT INTO zeigt VALUES (40, 116, DATE '2006-03-05', 'Die Besten Schokolademacher', 14);

INSERT INTO zeigt VALUES (10, 101, DATE '2007-03-04', 'Die Besten Schokolademacher', 1);
INSERT INTO zeigt VALUES (12, 108, DATE '2007-03-04', 'Die Besten Schokolademacher', 2);
INSERT INTO zeigt VALUES (39, 114, DATE '2007-03-04', 'Die Besten Schokolademacher', 7);
INSERT INTO zeigt VALUES (36, 113, DATE '2007-03-04', 'Die Besten Schokolademacher', 4);
INSERT INTO zeigt VALUES (40, 116, DATE '2007-03-04', 'Die Besten Schokolademacher', 10);

INSERT INTO zeigt VALUES (10, 103, DATE '2007-03-04', 'Die Besten Schokolademacher', 6);
INSERT INTO zeigt VALUES (12, 107, DATE '2007-03-04', 'Die Besten Schokolademacher', 1);
INSERT INTO zeigt VALUES (40, 116, DATE '2008-03-02', 'Die Besten Schokolademacher', 16);


INSERT INTO zeigt VALUES (14, 110, DATE '2007-08-24', 'Kunstschau 2007', 5);
INSERT INTO zeigt VALUES (39, 114, DATE '2007-08-24', 'Kunstschau 2007', 7);
INSERT INTO zeigt VALUES (36, 113, DATE '2007-08-24', 'Kunstschau 2007', 2);

INSERT INTO zeigt VALUES (14, 110, DATE '2008-08-26', 'Kunstschau 2008', 4);
INSERT INTO zeigt VALUES (39, 110, DATE '2008-08-26', 'Kunstschau 2008', 6);
INSERT INTO zeigt VALUES (36, 110, DATE '2008-08-26', 'Kunstschau 2008', 9);
INSERT INTO zeigt VALUES (12, 109, DATE '2008-08-26', 'Kunstschau 2008', 12);


INSERT INTO zeigt VALUES (10, 101, DATE '2006-05-21', 'Internactional Chocolate Contest', 12);
INSERT INTO zeigt VALUES (34, 111, DATE '2006-05-21', 'Internactional Chocolate Contest', 1);

INSERT INTO zeigt VALUES (12, 109, DATE '2007-05-20', 'Internactional Chocolate Contest', 1);
INSERT INTO zeigt VALUES (39, 114, DATE '2007-05-20', 'Internactional Chocolate Contest', 3);
INSERT INTO zeigt VALUES (34, 111, DATE '2007-05-20', 'Internactional Chocolate Contest', 16);
INSERT INTO zeigt VALUES (12, 107, DATE '2007-05-20', 'Internactional Chocolate Contest', 4);

INSERT INTO zeigt VALUES (14, 110, DATE '2008-05-18', 'Internactional Chocolate Contest', 1);


INSERT INTO zeigt VALUES (38, 115, DATE '2006-11-16', 'Europaeischer Suessigkeitenkongress', 2);
INSERT INTO zeigt VALUES (34, 111, DATE '2006-11-16', 'Europaeischer Suessigkeitenkongress', 8);
INSERT INTO zeigt VALUES (33, 106, DATE '2006-11-16', 'Europaeischer Suessigkeitenkongress', 19);
INSERT INTO zeigt VALUES (14, 110, DATE '2006-11-16', 'Europaeischer Suessigkeitenkongress', 6);

INSERT INTO zeigt VALUES (38, 115, DATE '2007-11-18', 'Europaeischer Suessigkeitenkongress', 2);
INSERT INTO zeigt VALUES (34, 111, DATE '2007-11-18', 'Europaeischer Suessigkeitenkongress', 8);
INSERT INTO zeigt VALUES (33, 106, DATE '2007-11-18', 'Europaeischer Suessigkeitenkongress', 10);

INSERT INTO zeigt VALUES (38, 112, DATE '2008-11-19', 'Europaeischer Suessigkeitenkongress', 1);
INSERT INTO zeigt VALUES (34, 111, DATE '2008-11-19', 'Europaeischer Suessigkeitenkongress', 8);
INSERT INTO zeigt VALUES (33, 106, DATE '2008-11-19', 'Europaeischer Suessigkeitenkongress', 19);


INSERT INTO zeigt VALUES (11, 104, DATE '2007-12-23', 'Die Schoensten Weihnachtsmotive', 2);
INSERT INTO zeigt VALUES (24, 105, DATE '2007-12-23', 'Die Schoensten Weihnachtsmotive', 1);
INSERT INTO zeigt VALUES (33, 106, DATE '2007-12-23', 'Die Schoensten Weihnachtsmotive', 4);
INSERT INTO zeigt VALUES (40, 116, DATE '2007-12-23', 'Die Schoensten Weihnachtsmotive', 9);

