USE homework11; 

ALTER TABLE developers 
	ADD salary DOUBLE DEFAULT NULL
	AFTER jobTitle;

UPDATE developers SET salary=4000 WHERE firstName ='Igor'AND lastName LIKE 'G';
UPDATE developers SET salary=1500 WHERE firstName = 'Elena' AND lastName LIKE 'G';
UPDATE developers SET salary=2000 WHERE firstName = 'Guillaume' AND lastName LIKE 'G';
