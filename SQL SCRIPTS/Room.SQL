CREATE TABLE Room(
Room_Id INT NOT NULL AUTO_INCREMENT,
Name VARCHAR (255),
Number VARCHAR (11),
PRIMARY KEY (Room_id),
Index Id_Number( Room_Id, Name),
Index Id_Name ( Room_Id, Number)
)ENGINE=INNODB;