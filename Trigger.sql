
--TRIGGER 1.	After inserting a new PATIENT, assign him a default PATIENT_CLASS called ‘GENERAL’

CREATE OR REPLACE TRIGGER Add_Patient_to_General
  AFTER INSERT ON PATIENT
  FOR EACH ROW
BEGIN
	INSERT INTO PATIENT_HAS_CLASS VALUES (:new.patientid,1);
END;
/
--TRIGGER 2.	After a PATIENT adds a new OBSERVATION_TYPE, add an entry to the OBS_TYPE_ENTRY_LOG to log this event.
CREATE OR REPLACE TRIGGER Add_OBS_Type_Log
  AFTER INSERT ON OBSERVATION_TYPE
  FOR EACH ROW
BEGIN
	INSERT INTO OBS_TYPE_ENTRY_LOG VALUES (:new.obs_typeid,:new.obs_typename, SYSDATE);
END;
/
--TRIGGER 3.	To add entries to ALERT_INFO table and update them as time passes and if the PATIENT views/clears them
--a.	TRIGGER to add a new entry to ALERT_INFO table for the PATIENT and all of his HEALTH_FRIENDs when a new OBSERVATION_LIST entry is added.
create or replace 
trigger Add_Alert_on_New_Obs
  AFTER INSERT ON OBS_LIST
  FOR EACH ROW
DECLARE
	TYPE TempTable IS TABLE OF HEALTH_FRIENDS.patientid1%type;
	names TempTable;
BEGIN
    INSERT INTO ALERT_INFO VALUES (alert_id_seq.nextval,1,:new.patientid,:new.patientid,:new.observed_date,0,0,:new.obs_typeid,NULL);
 
	 select * BULK COLLECT INTO NAMES FROM	 
		( Select DISTINCT(patientid2) FROM health_friends H WHERE H.patientid1 = :new.patientid )
		UNION
		( Select DISTINCT(patientid1) FROM health_friends H WHERE H.patientid2 = :new.patientid );
IF NAMES.count > 0 then
	 FOR i IN names.FIRST .. names.LAST
	   LOOP
			INSERT INTO ALERT_INFO VALUES (alert_id_seq.nextval,1,names(i),:new.patientid,:new.observed_date,0,0,:new.obs_typeid,NULL);
	   END LOOP;
END IF;
END;
/
/*b.	Stored Procedure to update the entry in ALERT_INFO for a PATIENT and all of his HEALTH_FRIENDs if the OBSERVED_DATE for the PATIENT is more than 7 days past from current date or if the generated alert for a HEALTH_FRIEND is not seen for 7 days after it was generated*/

CREATE OR REPLACE PROCEDURE Update_Alert_Proc (
PT_ID IN HEALTH_FRIENDS.patientid1%TYPE )
IS
	TYPE TempTable IS TABLE OF HEALTH_FRIENDS.patientid1%type;
	names TempTable;
BEGIN
	UPDATE ALERT_INFO
	set isGenerated = 1
	where alert_for = PT_ID AND isGenerated = 0 
AND ( (alert_OnBehalf = PT_ID AND (SYSDATE - observed_date) > 7) OR (alert_OnBehalf <> PT_ID AND (SYSDATE - observed_date) > 14) );
	
	SELECT * BULK COLLECT INTO NAMES FROM	
( Select DISTINCT(patientid2) FROM health_friends H WHERE H.patientid1 = PT_ID )
UNION
( Select DISTINCT(patientid1) FROM health_friends H WHERE H.patientid2 = PT_ID );

	IF NAMES.count > 0 then
	FOR i IN names.FIRST .. names.LAST
	LOOP
UPDATE ALERT_INFO
set isGenerated = 1
where alert_for = names(i) AND isGenerated = 0 
AND ( (alert_OnBehalf = names(i) AND (SYSDATE - observed_date) > 7) OR (alert_OnBehalf <> names(i) AND (SYSDATE - observed_date) > 14) );
	END LOOP;
	END IF;
END;
/
--TRIGGER 4.	TRIGGER to add a new entry to ALERT_INFO table for the PATIENT and all of his HEALTH_FRIENDs when an abnormally High Temperature is observed.
CREATE OR REPLACE TRIGGER Add_Alert_High_Temperature
AFTER INSERT ON OBS_ATTRIBUTE_LIST
FOR EACH ROW
WHEN (new.attributeid = 13 AND TO_NUMBER(new.attribute_value) > 102)
DECLARE
	OBS_DATE date;
  	PT_ID varchar(30);
	TYPE TempTable IS TABLE OF HEALTH_FRIENDS.patientid1%type;
	names TempTable;
BEGIN
SELECT observed_date, patientid INTO OBS_DATE,PT_ID FROM OBS_LIST where obs_listid = :new.obs_listid;

INSERT INTO ALERT_INFO VALUES (alert_id_seq.nextval,2,PT_ID,PT_ID,OBS_DATE,1,0,NULL,:new.attributeid);

	select * BULK COLLECT INTO NAMES FROM	
	( Select DISTINCT(patientid2) FROM health_friends H WHERE H.patientid1 = PT_ID )
	UNION
	( Select DISTINCT(patientid1) FROM health_friends H WHERE H.patientid2 = PT_ID );

	IF NAMES.count > 0 then
FOR I IN names.FIRST .. names.LAST
	LOOP
INSERT INTO ALERT_INFO VALUES (alert_id_seq.nextval,2,names(i),PT_ID,OBS_DATE,1,0,NULL,:new.attributeid);
	END LOOP;
	END IF;
END;
/
--TRIGGER 5.	TRIGGER to add a new entry to ALERT_INFO table for the PATIENT and all of his HEALTH_FRIENDs when an abnormally Low Oxygen Saturation is observed.
CREATE OR REPLACE TRIGGER Add_Alert_Low_Oxygen_Sat
AFTER INSERT ON OBS_ATTRIBUTE_LIST
FOR EACH ROW
WHEN (new.attributeid = 10 AND TO_NUMBER(new.attribute_value) < 88)
DECLARE
	OBS_DATE date;
  	PT_ID varchar(30);
	TYPE TempTable IS TABLE OF HEALTH_FRIENDS.patientid1%type;
	names TempTable;
BEGIN
SELECT observed_date, patientid INTO OBS_DATE,PT_ID FROM OBS_LIST where obs_listid = :new.obs_listid;

INSERT INTO ALERT_INFO VALUES (alert_id_seq.nextval,2,PT_ID,PT_ID,OBS_DATE,1,0,NULL,:new.attributeid);

	select * BULK COLLECT INTO NAMES FROM	
	( Select DISTINCT(patientid2) FROM health_friends H WHERE H.patientid1 = PT_ID )
	UNION
	( Select DISTINCT(patientid1) FROM health_friends H WHERE H.patientid2 = PT_ID );

	IF NAMES.count > 0 then
FOR i IN names.FIRST .. names.LAST
	LOOP
INSERT INTO ALERT_INFO VALUES (alert_id_seq.nextval,2,names(i),PT_ID,OBS_DATE,1,0,NULL,:new.attributeid);
	END LOOP;
	END IF;
END;
/
--TRIGGER 6.	TRIGGER to add a new entry to ALERT_INFO table for the PATIENT and all of his HEALTH_FRIENDs when an abnormally High Pain is observed.
CREATE OR REPLACE TRIGGER Add_Alert_High_Pain
AFTER INSERT ON OBS_ATTRIBUTE_LIST
FOR EACH ROW
WHEN (new.attributeid = 11 AND TO_NUMBER(new.attribute_value) > 7)
DECLARE
	OBS_DATE date;
  	PT_ID varchar(30);
	TYPE TempTable IS TABLE OF HEALTH_FRIENDS.patientid1%type;
	names TempTable;
BEGIN
SELECT observed_date, patientid INTO OBS_DATE,PT_ID FROM OBS_LIST where obs_listid = :new.obs_listid;

INSERT INTO ALERT_INFO VALUES (alert_id_seq.nextval,2,PT_ID,PT_ID,OBS_DATE,1,0,NULL,:new.attributeid);

	select * BULK COLLECT INTO NAMES FROM	
	( Select DISTINCT(patientid2) FROM health_friends H WHERE H.patientid1 = PT_ID )
	UNION
	( Select DISTINCT(patientid1) FROM health_friends H WHERE H.patientid2 = PT_ID );

	IF NAMES.count > 0 then
FOR i IN names.FIRST .. names.LAST
	LOOP
INSERT INTO ALERT_INFO VALUES (alert_id_seq.nextval,2,names(i),PT_ID,OBS_DATE,1,0,NULL,:new.attributeid);
	END LOOP;
	END IF;
END;
/
--TRIGGER 7.	TRIGGER to add a new entry to ALERT_INFO table for the PATIENT and all of his HEALTH_FRIENDs when an abnormally High Contraction Frequency is observed.
CREATE OR REPLACE TRIGGER Add_Alert_High_Contraction
AFTER INSERT ON OBS_ATTRIBUTE_LIST
FOR EACH ROW
WHEN (new.attributeid = 12 AND TO_NUMBER(new.attribute_value) > 4)
DECLARE
	OBS_DATE date;
PT_ID varchar(30);
	TYPE TempTable IS TABLE OF HEALTH_FRIENDS.patientid1%type;
	names TempTable;
BEGIN
SELECT observed_date, patientid INTO OBS_DATE,PT_ID FROM OBS_LIST where obs_listid = :new.obs_listid;

INSERT INTO ALERT_INFO VALUES (alert_id_seq.nextval,2,PT_ID,PT_ID,OBS_DATE,1,0,NULL,:new.attributeid);

	select * BULK COLLECT INTO NAMES FROM	
	( Select DISTINCT(patientid2) FROM health_friends H WHERE H.patientid1 = PT_ID )
	UNION
	( Select DISTINCT(patientid1) FROM health_friends H WHERE H.patientid2 = PT_ID );

	IF NAMES.count > 0 then
FOR i IN names.FIRST .. names.LAST
	LOOP
INSERT INTO ALERT_INFO VALUES (alert_id_seq.nextval,2,names(i),PT_ID,OBS_DATE,1,0,NULL,:new.attributeid);
	END LOOP;
	END IF;
END;
/
--TRIGGER 8.	TRIGGER to add a new entry to ALERT_INFO table for the PATIENT and all of his HEALTH_FRIENDs when an abnormally High Blood Pressure is observed.
CREATE OR REPLACE TRIGGER Add_Alert_High_BP
AFTER INSERT ON OBS_ATTRIBUTE_LIST
FOR EACH ROW
WHEN ( (new.attributeid = 7 AND TO_NUMBER(new.attribute_value) >= 140) OR (new.attributeid = 8 AND TO_NUMBER(new.attribute_value) >= 90) )
DECLARE
	OBS_DATE date;
  	PT_ID varchar(30);
	TYPE TempTable IS TABLE OF HEALTH_FRIENDS.patientid1%type;
	names TempTable;
BEGIN
SELECT observed_date, patientid INTO OBS_DATE,PT_ID FROM OBS_LIST where obs_listid = :new.obs_listid;

INSERT INTO ALERT_INFO VALUES (alert_id_seq.nextval,2,PT_ID,PT_ID,OBS_DATE,1,0,NULL,:new.attributeid);

	select * BULK COLLECT INTO NAMES FROM	
	( Select DISTINCT(patientid2) FROM health_friends H WHERE H.patientid1 = PT_ID )
	UNION
	( Select DISTINCT(patientid1) FROM health_friends H WHERE H.patientid2 = PT_ID );

	IF NAMES.count > 0 then
FOR i IN names.FIRST .. names.LAST
	LOOP
INSERT INTO ALERT_INFO VALUES (alert_id_seq.nextval,2,names(i),PT_ID,OBS_DATE,1,0,NULL,:new.attributeid);
	END LOOP;
	END IF;
END;
/