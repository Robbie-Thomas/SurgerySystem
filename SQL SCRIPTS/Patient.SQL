CREATE TABLE Patient (
Patient_Id INT NOT NULL AUTO_INCREMENT,
First_Name VARCHAR(55)  NOT NULL,
Middle_Name VARCHAR(55) NOT NULL,
Last_Name VARCHAR(55) NOT NULL,
Is_Male TINYINT NOT NULL,
Medical_Information VARCHAR(5000),
Date_Of_Birth DATE,
Row_Create DATETIME NOT NULL,
Row_LastUpdate DATETIME,
PRIMARY KEY (  Patient_id),
INDEX name(First_Name, Last_Name)
) ENGINE=INNODB;

CREATE TABLE PatientPhoneNumber (
PatientPhoneNumber_Id INT NOT NULL AUTO_INCREMENT,
Patient_Id INT NOT NULL,
Phone_Number VARCHAR (15),
Row_Create DATETIME NOT NULL,
Row_LastUpdate DATETIME,
PRIMARY KEY (  PatientPhoneNumber_Id),
FOREIGN KEY (Patient_Id)
REFERENCES Patient(Patient_Id)
ON UPDATE CASCADE ON DELETE RESTRICT,
INDEX PatientID_PhoneNum (Patient_Id,Phone_Number)
) ENGINE=INNODB;

CREATE TABLE Address (
PatientAddress_Id INT NOT NULL AUTO_INCREMENT,
Patient_Id INT NOT NULL,
House_Name VARCHAR(100),
House_Number INT,
Street VARCHAR(70) NOT NULL,
City VARCHAR(70) NOT NULL,
Postcode VARCHAR(8) NOT NULL,
County VARCHAR(50) NOT NULL,
Country VARCHAR(60) NOT NULL,
Row_Create DATETIME NOT NULL,
Row_LastUpdate DATETIME,
PRIMARY KEY (  PatientAddress_id),
FOREIGN KEY (Patient_Id)
REFERENCES Patient(Patient_Id)
ON UPDATE CASCADE ON DELETE RESTRICT,
INDEX HouseNum_Street(House_Number, Street),
INDEX PatientID_postcode(Patient_Id, Postcode)
)ENGINE=INNODB;

CREATE TABLE PatientEmailAddress (
PatientEmailAddress_Id INT NOT NULL AUTO_INCREMENT,
Patient_Id INT NOT NULL,
Email_Address VARCHAR (255) UNIQUE,
Row_Create DATETIME NOT NULL,
Row_LastUpdate DATETIME,
PRIMARY KEY (  PatientEmailAddress_Id),
FOREIGN KEY (Patient_Id)
REFERENCES Patient(Patient_Id)
ON UPDATE CASCADE ON DELETE RESTRICT,
INDEX PatientID_Email (Patient_Id,Email_Address)
) ENGINE=INNODB;
