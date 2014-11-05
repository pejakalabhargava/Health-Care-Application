--CREATE TABLE Script for PATIENT Table
CREATE TABLE PATIENT (
name VARCHAR(30) NOT NULL,
age INT,
patientid VARCHAR(30) NOT NULL,
pwd VARCHAR(30) NOT NULL,
address VARCHAR(50),
sex VARCHAR(7) NOT NULL,
public_status CHAR(1) NOT NULL,
PRIMARY KEY (patientid)
);

INSERT INTO PATIENT VALUES ('Gary George',25,'ggeorge','geo123','2806 Conifer Drive, Raleigh, NC 27606','Male','Y');
INSERT INTO PATIENT VALUES ('Adnan Kazi',31,'akazi','kazi123','1234 Capability Drive, Raleigh, NC 27655','Female','Y');
INSERT INTO PATIENT VALUES ('Neha Shetty',40,'nshetty','shetty123','440 Sullivan Drive, Chapel Hill, NC 27517','Female','Y');
INSERT INTO PATIENT VALUES ('Sheldon Cooper',33,'scooper','cooper123','2808 Avent Ferry Road, Raleigh, NC 27616','Female','Y');
INSERT INTO PATIENT VALUES ('Michael Watson',47,'mwatson','watson123','2222 Gorman Street, Raleigh, NC 27678','Male','Y');
INSERT INTO PATIENT VALUES ('Tom Kerr',40,'tkerr','kerr123','1430 Collegeview Ave, Durham, NC 27701','Male','Y');
INSERT INTO PATIENT VALUES ('Maya Tran',37,'mtran','tran123','100 Brown Circle, Chapel Hill, NC 27516','Female','Y');

--CREATE TABLE Script for PYSICIAN Table
CREATE TABLE PHYSICIAN (
name VARCHAR(30) NOT NULL,
doctorid VARCHAR(30) NOT NULL,
pwd VARCHAR(30) NOT NULL,
clinic VARCHAR(30),
PRIMARY KEY (doctorid)
);

INSERT INTO PHYSICIAN VALUES ('Altaf Hussain','ahussain','hussain123','Dayview');
INSERT INTO PHYSICIAN VALUES ('Manu Joseph','mjoseph','joseph123','Dayview');
INSERT INTO PHYSICIAN VALUES ('Shane Lee','slee','lee123','Huntigton');
INSERT INTO PHYSICIAN VALUES ('Shyam Prasad','sprasad','prasad123','Huntington');

--CREATE TABLE Script for PATIENT_CLASS Table
CREATE TABLE PATIENT_CLASS (
classid int NOT NULL,
classname VARCHAR(30) NOT NULL,
PRIMARY KEY(classid)
);

INSERT INTO PATIENT_CLASS VALUES (1,'GENERAL');
INSERT INTO PATIENT_CLASS VALUES (2,'HIV');
INSERT INTO PATIENT_CLASS VALUES (3,'Obesity');
INSERT INTO PATIENT_CLASS VALUES (4,'High Risk Pregnancy');
INSERT INTO PATIENT_CLASS VALUES (5,'COPD');



--CREATE TABLE Script for PATIENT_HAS_CLASS Table
CREATE TABLE PATIENT_HAS_CLASS (
patientid VARCHAR(30) NOT NULL,
classid int,
PRIMARY KEY(patientid,classid),
FOREIGN KEY (patientid) REFERENCES PATIENT (patientid) ON DELETE CASCADE,
FOREIGN KEY (classid) REFERENCES PATIENT_CLASS (classid) ON DELETE CASCADE
);

INSERT INTO PATIENT_HAS_CLASS VALUES ('ggeorge',2);
INSERT INTO PATIENT_HAS_CLASS VALUES ('akazi',3);
INSERT INTO PATIENT_HAS_CLASS VALUES ('akazi',4);
INSERT INTO PATIENT_HAS_CLASS VALUES ('nshetty',3);
INSERT INTO PATIENT_HAS_CLASS VALUES ('ggeorge',1);
INSERT INTO PATIENT_HAS_CLASS VALUES ('nshetty',4);
INSERT INTO PATIENT_HAS_CLASS VALUES ('ggeorge',4);
INSERT INTO PATIENT_HAS_CLASS VALUES ('scooper',2);
INSERT INTO PATIENT_HAS_CLASS VALUES ('scooper',5);
INSERT INTO PATIENT_HAS_CLASS VALUES ('mwatson',5);
INSERT INTO PATIENT_HAS_CLASS VALUES ('tkerr',3);
INSERT INTO PATIENT_HAS_CLASS VALUES ('tkerr',5);
INSERT INTO PATIENT_HAS_CLASS VALUES ('mtran',4);
INSERT INTO PATIENT_HAS_CLASS VALUES ('ggeorge',5);
INSERT INTO PATIENT_HAS_CLASS VALUES ('ggeorge',3);



--CREATE TABLE Script for OBSERVATION_CATEGORY Table
CREATE TABLE OBSERVATION_CATEGORY (
categoryid int NOT NULL,
categoryname VARCHAR (20),
PRIMARY KEY (categoryid)
);

INSERT INTO OBSERVATION_CATEGORY VALUES (1,'Behavioral');
INSERT INTO OBSERVATION_CATEGORY VALUES (2,'Psychological');
INSERT INTO OBSERVATION_CATEGORY VALUES (3,'Physiological');
INSERT INTO OBSERVATION_CATEGORY VALUES (4,'General');


--CREATE SEQUENCE & TABLE Script for OBSERVATION_TYPE Table
CREATE SEQUENCE observation_typeid
  START WITH 0
  INCREMENT BY 1
  MINVALUE -1
  CACHE 100;

CREATE TABLE OBSERVATION_TYPE (
obs_typeid int NOT NULL,
obs_typename VARCHAR (20),
categoryid int DEFAULT 4 NOT NULL,
PRIMARY KEY (obs_typeid),
FOREIGN KEY (categoryid) REFERENCES OBSERVATION_CATEGORY(categoryid) ON DELETE CASCADE
);

INSERT INTO OBSERVATION_TYPE VALUES(observation_typeid.nextVal,'Diet',1);
INSERT INTO OBSERVATION_TYPE VALUES(observation_typeid.nextVal,'Weight',1);
INSERT INTO OBSERVATION_TYPE VALUES(observation_typeid.nextVal,'Exercise',1);
INSERT INTO OBSERVATION_TYPE VALUES(observation_typeid.nextVal,'Mood',2);
INSERT INTO OBSERVATION_TYPE VALUES(observation_typeid.nextVal,'Blood Pressure',3);
INSERT INTO OBSERVATION_TYPE VALUES(observation_typeid.nextVal,'Exercise Tolerance',3);
INSERT INTO OBSERVATION_TYPE VALUES(observation_typeid.nextVal,'Oxygen Saturation',3);
INSERT INTO OBSERVATION_TYPE VALUES(observation_typeid.nextVal,'Pain',3);
INSERT INTO OBSERVATION_TYPE VALUES(observation_typeid.nextVal,'Contraction',3);
INSERT INTO OBSERVATION_TYPE VALUES(observation_typeid.nextVal,'Temperature',3);

--CREATE TABLE Script for CLASS_HAS_TYPES Table
CREATE TABLE CLASS_HAS_TYPES(
classid int NOT NULL,
obs_typeid int NOT NULL,
PRIMARY KEY (classid, obs_typeid),
FOREIGN KEY (classid) REFERENCES PATIENT_CLASS (classid) ON DELETE CASCADE,
FOREIGN KEY (obs_typeid) REFERENCES OBSERVATION_TYPE (obs_typeid) ON DELETE CASCADE
);

INSERT INTO CLASS_HAS_TYPES VALUES(1,1);
INSERT INTO CLASS_HAS_TYPES VALUES(1,2);
INSERT INTO CLASS_HAS_TYPES VALUES(1,3);
INSERT INTO CLASS_HAS_TYPES VALUES(1,4);
INSERT INTO CLASS_HAS_TYPES VALUES(2,10);
INSERT INTO CLASS_HAS_TYPES VALUES(3,5);
INSERT INTO CLASS_HAS_TYPES VALUES(4,5);
INSERT INTO CLASS_HAS_TYPES VALUES(4,8);
INSERT INTO CLASS_HAS_TYPES VALUES(4,9);
INSERT INTO CLASS_HAS_TYPES VALUES(5,6);
INSERT INTO CLASS_HAS_TYPES VALUES(5,7);

--CREATE SEQUENCE & TABLE Script for OBSERVATION_ATTRIBUTE Table
CREATE SEQUENCE observation_attributeid
  START WITH 0
  INCREMENT BY 1
  MINVALUE -1
  CACHE 100;

CREATE TABLE OBSERVATION_ATTRIBUTE(
attributeid int NOT NULL,
attribute_name varchar(70) NOT NULL,
obs_typeid int NOT NULL,
PRIMARY KEY (attributeid),
FOREIGN KEY (obs_typeid) REFERENCES OBSERVATION_TYPE (obs_typeid) ON DELETE CASCADE
);

INSERT INTO OBSERVATION_ATTRIBUTE VALUES (observation_attributeid.nextval,'What was consumed',1);
INSERT INTO OBSERVATION_ATTRIBUTE VALUES (observation_attributeid.nextval,'Amount in servings',1);
INSERT INTO OBSERVATION_ATTRIBUTE VALUES (observation_attributeid.nextval,'Amount in pounds',2);
INSERT INTO OBSERVATION_ATTRIBUTE VALUES (observation_attributeid.nextval,'What Kind',3);
INSERT INTO OBSERVATION_ATTRIBUTE VALUES (observation_attributeid.nextval,'Duration',3);
INSERT INTO OBSERVATION_ATTRIBUTE VALUES (observation_attributeid.nextval,'Mood Type',4);
INSERT INTO OBSERVATION_ATTRIBUTE VALUES (observation_attributeid.nextval,'Systolic',5);
INSERT INTO OBSERVATION_ATTRIBUTE VALUES (observation_attributeid.nextval,'Diastolic',5);
INSERT INTO OBSERVATION_ATTRIBUTE VALUES (observation_attributeid.nextval,'Number of steps before exhaustion',6);
INSERT INTO OBSERVATION_ATTRIBUTE VALUES (observation_attributeid.nextval,'Fraction of haemoglobin saturated by oxygen',7);
INSERT INTO OBSERVATION_ATTRIBUTE VALUES (observation_attributeid.nextval,'Scale',8);
INSERT INTO OBSERVATION_ATTRIBUTE VALUES (observation_attributeid.nextval,'Frequency - # per hour',9);
INSERT INTO OBSERVATION_ATTRIBUTE VALUES (observation_attributeid.nextval,'Amount in Fahrenheit',10);

--CREATE SEQUENCE & TABLE Script for OBS_LIST Table
CREATE SEQUENCE obs_list_id
  START WITH 0
  INCREMENT BY 1
  MINVALUE -1
  CACHE 100;

CREATE TABLE OBS_LIST (
obs_listid int NOT NULL,
patientid VARCHAR(30) NOT NULL,
obs_typeid int NOT NULL,
record_date DATE DEFAULT (SYSDATE) NOT NULL,
observed_date DATE NOT NULL,
PRIMARY KEY (obs_listid),
FOREIGN KEY (patientid) REFERENCES PATIENT (patientid) ON DELETE CASCADE,
FOREIGN KEY (obs_typeid) REFERENCES OBSERVATION_TYPE (obs_typeid) ON DELETE CASCADE
);

INSERT INTO OBS_LIST (obs_listid, patientid, obs_typeid, observed_date) VALUES (obs_list_id.nextval,'scooper',1,TO_DATE('2013-05-04 08:15','YYYY-DD-MM HH24:MI'));
INSERT INTO OBS_LIST (obs_listid, patientid, obs_typeid, observed_date) VALUES (obs_list_id.nextval,'scooper',2,TO_DATE('2013-05-04 08:00','YYYY-DD-MM HH24:MI'));
INSERT INTO OBS_LIST (obs_listid, patientid, obs_typeid, observed_date) VALUES (obs_list_id.nextval,'scooper',3,TO_DATE('2013-05-04 06:30','YYYY-DD-MM HH24:MI'));
INSERT INTO OBS_LIST (obs_listid, patientid, obs_typeid, observed_date) VALUES (obs_list_id.nextval,'scooper',4,TO_DATE('2013-05-04 21:00','YYYY-DD-MM HH24:MI'));
INSERT INTO OBS_LIST (obs_listid, patientid, obs_typeid, observed_date) VALUES (obs_list_id.nextval,'scooper',10,TO_DATE('2013-05-04 06:00','YYYY-DD-MM HH24:MI'));
INSERT INTO OBS_LIST (obs_listid, patientid, obs_typeid, observed_date) VALUES (obs_list_id.nextval,'scooper',6,TO_DATE('2013-05-04 10:00','YYYY-DD-MM HH24:MI'));
INSERT INTO OBS_LIST (obs_listid, patientid, obs_typeid, observed_date) VALUES (obs_list_id.nextval,'scooper',7,TO_DATE('2013-05-04 11:30','YYYY-DD-MM HH24:MI'));
INSERT INTO OBS_LIST (obs_listid, patientid, obs_typeid, observed_date) VALUES (obs_list_id.nextval,'scooper',2,TO_DATE('2013-06-04 08:00','YYYY-DD-MM HH24:MI'));
INSERT INTO OBS_LIST (obs_listid, patientid, obs_typeid, observed_date) VALUES (obs_list_id.nextval,'ggeorge',2,TO_DATE('2013-05-04 07:50','YYYY-DD-MM HH24:MI'));
INSERT INTO OBS_LIST (obs_listid, patientid, obs_typeid, observed_date) VALUES (obs_list_id.nextval,'ggeorge',2,TO_DATE('2013-06-04 08:00','YYYY-DD-MM HH24:MI'));
INSERT INTO OBS_LIST (obs_listid, patientid, obs_typeid, observed_date) VALUES (obs_list_id.nextval,'akazi',5,TO_DATE('2013-06-04 07:50','YYYY-DD-MM HH24:MI'));
INSERT INTO OBS_LIST (obs_listid, patientid, obs_typeid, observed_date) VALUES (obs_list_id.nextval,'akazi',5,TO_DATE('2013-08-04 08:00','YYYY-DD-MM HH24:MI'));
INSERT INTO OBS_LIST (obs_listid, patientid, obs_typeid, observed_date) VALUES (obs_list_id.nextval,'nshetty',5,TO_DATE('2013-06-04 07:50','YYYY-DD-MM HH24:MI'));
INSERT INTO OBS_LIST (obs_listid, patientid, obs_typeid, observed_date) VALUES (obs_list_id.nextval,'mtran',8,TO_DATE('2013-06-04 13:00','YYYY-DD-MM HH24:MI'));


--CREATE TABLE Script for OBS_ATTRIBUTE_LIST Table
CREATE TABLE OBS_ATTRIBUTE_LIST (
obs_listid int NOT NULL,
attributeid int NOT NULL,
attribute_value varchar (70) NOT NULL,
PRIMARY KEY (obs_listid,attributeid),
FOREIGN KEY (obs_listid) REFERENCES OBS_LIST (obs_listid) ON DELETE CASCADE,
FOREIGN KEY (attributeid) REFERENCES OBSERVATION_ATTRIBUTE (attributeid) ON DELETE CASCADE
);

INSERT INTO OBS_ATTRIBUTE_LIST VALUES (1,1,'Eggs');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (1,2,'3');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (2,3,'100');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (3,4,'Walking');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (3,5,'30');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (4,6,'Neutral');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (5,13,'98.2');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (6,9,'20');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (7,10,'78');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (8,3,'102');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (9,3,'150');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (10,3,'156');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (11,7,'150');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (11,8,'96');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (12,7,'170');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (12,8,'90');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (13,7,'162');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (13,8,'110');
INSERT INTO OBS_ATTRIBUTE_LIST VALUES (14,11,'8');

--CREATE SEQUENCE & TABLE Script for HEALTH_FRIENDS Table
CREATE SEQUENCE friendship_seq
  START WITH 0
  INCREMENT BY 1
  MINVALUE -1
  CACHE 100;

CREATE TABLE HEALTH_FRIENDS (
friendShipId INT ,
patientid1 VARCHAR(30) NOT NULL,
patientid2 VARCHAR(30) NOT NULL,
dateOfFriendship date default sysdate,
PRIMARY KEY (friendShipId),
FOREIGN KEY (patientid1) REFERENCES PATIENT(patientid) ON DELETE CASCADE,
FOREIGN KEY (patientid2) REFERENCES PATIENT(patientid) ON DELETE CASCADE
);

insert into HEALTH_FRIENDS(friendShipId,patientid1,patientid2) values(friendship_seq.nextval,'tkerr','akazi');
insert into HEALTH_FRIENDS(friendShipId,patientid1,patientid2) values(friendship_seq.nextval,'tkerr','mwatson');
insert into HEALTH_FRIENDS(friendShipId,patientid1,patientid2) values(friendship_seq.nextval,'scooper','ggeorge');
insert into HEALTH_FRIENDS(friendShipId,patientid1,patientid2) values(friendship_seq.nextval,'scooper','mwatson');
insert into HEALTH_FRIENDS(friendShipId,patientid1,patientid2) values(friendship_seq.nextval,'scooper','tkerr');


--CREATE SEQUENCE & TABLE Script for ALERT_INFO Table
CREATE SEQUENCE alert_id_seq
  START WITH 0
  INCREMENT BY 1
  MINVALUE -1
  CACHE 100;

CREATE TABLE ALERT_INFO(
alertid int,
alert_type int,
alert_for varchar (30),
alert_OnBehalf varchar (30),
observed_date DATE,
isGenerated int default 0  check (isGenerated in (0,1)),
isSeen int default 0  check (isSeen in (0,1)),
obs_typeid int default NULL,
attributeid int default NULL,
PRIMARY KEY (alertid),
FOREIGN KEY (alert_for) REFERENCES PATIENT (patientid) ON DELETE CASCADE,
FOREIGN KEY (alert_OnBehalf) REFERENCES PATIENT (patientid) ON DELETE CASCADE,
FOREIGN KEY (obs_typeid) REFERENCES OBSERVATION_TYPE (obs_typeid) ON DELETE CASCADE,
FOREIGN KEY (attributeid) REFERENCES OBSERVATION_ATTRIBUTE (attributeid) ON DELETE CASCADE
);

--CREATE TABLE Script for OBS_TYPE_ENTRY_LOG Table
CREATE TABLE OBS_TYPE_ENTRY_LOG(
obs_typeid int NOT NULL,
obs_typename VARCHAR (20),
addedOn DATE DEFAULT (SYSDATE) NOT NULL
);

--CREATE TABLE Script for FRIENDMESSAGE Table
CREATE TABLE FRIENDMESSAGE(
messageFrom varchar(30),
messageTo   varchar(30),
messageString varchar(300),
isSeen int default 0  check (isSeen in (0,1))
);

