/*
+---------------------------------------------------------------------------------------------------+
|S1.) Wählen Sie "per Hand" die Personalnummer eines Angestellten aus Ihren Testdaten aus. 			|
|	 Schreiben Sie eine SQL-Anfrage, die jene Fan-Clubs ermittelt, die dieser Angestellte			|
|	 im Moment nicht betreut. Geben Sie zu jedem derartigen Fan-Club die Standort-ID und 			|
|	 den Namen des Fan-Clubs aus. Bemerkung: Ein Fan-Club wird von einem Angestellten im 			|
|	 Moment nicht betreut, wenn entweder der Angestellte diesen Fan-Club überhaupt nie 				|
|	 betreut hat oder wenn das heutige Datum(= sysdate) außerhalb des Betreuungszeitraums 			|
|	 liegt. Vergessen Sie nicht, jene Fan-Clubs zu berücksichtigen, die von überhaupt 				|
|	 keinem Angestellten betreut werden (dieser Fall sollte zwar laut Datenmodell nicht vorkommen. 	|
|	Die Einhaltung dieser Bedingung wird aber vermutlich vom Datenbanksystem nicht überprüft)!		|
+---------------------------------------------------------------------------------------------------+
*/

Select DISTINCT b.name as FanClubName,b.persnr,fc.sid 
from Betreut b 
Join FanClub fc On b.name = fc.name 
where fc.name != (Select name from Betreut where persnr = 1345463) and now() not between b.anfang and b.ende or b.persnr != null 
order by sid asc;


Select DISTINCT b.name as FanClubName,b.persnr,fc.sid 
from Betreut b 
Join FanClub fc On b.name = fc.name 
where fc.name != (Select name from Betreut where persnr = 1345463) and now() not between 
(SELECT anfang FROM Betreut WHERE persnr = 1345463) and (SELECT ende FROM Betreut WHERE persnr = 1345463) or b.persnr != null 
order by sid asc;

/*
+---------------------------------------------------------------------------------------------------+
|S2.) (Die eifrigsten Angestellten) Schreiben Sie eine SQL-Anfrage, die den Nachnamen und die 		|
|	 Personalnummer jener Angestellten ausgibt, die im Moment sämtliche Fan-Clubs betreuen. 		|
|	 Ordnen Sie die Nachnamen alphabetisch. Bemerkung: Passen Sie die Testdaten so an, dass 		|
|	 diese Anfrage zumindest zwei Angestellte liefert.												|
+---------------------------------------------------------------------------------------------------+
*/

SELECT p.nname,p.persnr
fROM Person p
JOIN Betreut b ON b.persnr = p.persnr
WHERE now() between b.anfang and b.ende
ORDER BY p.nname ASC;

/*
+-----------------------------------------------------------------------------------------------------------+
|S3.) (Spielereinsätze) Geben Sie für alle Spiele des Jahres 2015 jeweils alle Spieler und die Dauer 		|
|	 ihres Einsatzes aus, d.h.: Gesucht sind alle Tupel (mannschaft, datum, vorname, nachname, dauer),		|
|	 mit folgender Eigenschaft:      																		|
|		"mannschaft" ist die Bezeichnung der Mannschaft, die gespielt hat.									|
|       "datum" ist das Datum, an dem das Spiel stattfand.													|
|        "vorname" und "nachname" beziehen sich auf einen Spieler, der bei diesem Spiel zum Einsatz kam.	|
|        "dauer" gibt die Dauer des Einsatzes (in Minuten) dieses Spielers bei diesem Spiel an.				|
+-----------------------------------------------------------------------------------------------------------+
*/

SELECT  bezeichnung as mannschaft, s1.datum, vname as vorname, nname as nachname, dauer
FROM Spiel s1
JOIN Spielt s2 ON s2.datum = s1.datum
JOIN Person p1 ON p1.persnr = s2.persnr
WHERE s1.datum between '2015-01-01' and '2015-12-31';


/*
+-----------------------------------------------------------------------------------------------------------+
|S4.) (Spieler-Ranking) Geben Sie für jeden Spieler den Vornamen und Nachnamen sowie die Gesamtdauer 		|
|("gesamtdauer") der von ihm bei Spielen im Jahr 2015 geleisteten Einsätze aus. 							|
|Vergessen Sie nicht, jene Spieler des Vereins zu berücksichtigen, die im Jahr 2015 bei keinem 				|
|einzigen Spiel mitgespielt haben (d.h. gesamtdauer = 0). Ordnen Sie die Ausgabe in absteigender 			|
|Gesamtdauer. Bei Gleichheit der Gesamtdauer sollen die Spieler in alphabetischer Reihenfolge				|
|(zuerst des Nachnamen, dann des Vornamen) sortiert werden.													|
+-----------------------------------------------------------------------------------------------------------+
*/

SELECT  sum(dauer) as gesammtdauer , vname as vorname, nname as nachname
FROM Spiel s1
JOIN Spielt s2 ON s2.datum = s1.datum
JOIN Person p1 ON p1.persnr = s2.persnr
WHERE s1.datum between '2015-01-01' and '2015-12-31'
GROUP BY p1.persnr,vname,nname
ORDER BY gesammtdauer DESC, nname ASC,vname ASC;

/*
+-----------------------------------------------------------------------------------------------------------+
|S5.) (Der fleißigste Spieler) Geben Sie den Vornamen und Nachnamen jenes Spielers aus, von dem die unter 	|
|b) berechnete Gesamtdauer am größten ist, d.h.: dieser Spieler ist bei Spielen im Jahr 2015 insgesamt am 	|
|längsten im Einsatz gewesen. Falls sich mehrere Spieler den ersten Platz teilen (d.h. sie kommen auf die 	|
|gleiche Gesamtdauer), dann sollen diese in alphabetischer Reihenfolge 										|
|(zuerst des Nachnamen, dann des Vornamen) geordnet werden. Der Fall, dass im Jahr 2015 überhaupt kein Spiel| 
|stattfand, darf ignoriert werden. Bemerkung: Berücksichtigen Sie bei Ihren Testdaten die Situation, 		|
|dass sich zumindest 2 Spieler den ersten Platz teilen.														|
+-----------------------------------------------------------------------------------------------------------+
*/

SELECT  sum(dauer) as gesammtdauer , vname as vorname, nname as nachname
FROM Spiel s1
JOIN Spielt s2 ON s2.datum = s1.datum
JOIN Person p1 ON p1.persnr = s2.persnr
WHERE s1.datum between '2015-01-01' and '2015-12-31' or 
GROUP BY p1.persnr,vname,nname
ORDER BY gesammtdauer DESC, nname ASC,vname ASC;

/*
+-------------------------------------------------------------------------------------------------------------+
|S6.) Schreiben Sie CREATE und DROP Befehle für eine View, die alle Informationen über Trainer aus der 		  |
|Personen- und Trainer-Tabelle zusammenfügt, d.h.: sowohl die allgemeinen Personendaten 					  |
|(Personalnummer, Vorname, Nachname, Geschlecht und Geburtsdatum) als auch die Trainer-spezifischen 		  |
|Informationen (Gehalt sowie Beginn und Ende der Vertragsdauer). In Summe ist also folgende View erforderlich:|
|Trainer_view (persnr, vname, nname, geschlecht, gebdat, gehalt, von, bis).									  |
+-------------------------------------------------------------------------------------------------------------+
*/
DROP VIEW Trainer_view CASCADE;

CREATE VIEW Trainer_view AS SELECT t.persnr,p.vname,p.nname,geschlecht,gebdat,gehalt,vertragsbeginn,vertragsende 
FROM Trainer t 
JOIN Person p ON  p.persnr = t.persnr;

DROP VIEW Trainer_view CASCADE;











