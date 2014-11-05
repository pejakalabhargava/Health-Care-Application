--=======================================================================================================================

Query 1: Find patients with the lowest weight amongst HIV patients.

SELECT DISTINCT(P1.name), A1.attribute_value
FROM patient P1, OBS_LIST L1, OBS_ATTRIBUTE_LIST A1
WHERE P1.patientid = L1.patientid AND L1.obs_listid = A1.obs_listid AND A1.attributeid = 3 
AND A1.attribute_value = (
        SELECT MIN(TO_NUMBER(A.attribute_value))
        FROM patient P, PATIENT_CLASS PC, OBS_LIST L, OBS_ATTRIBUTE_LIST A
        WHERE P.patientid = L.patientid AND L.obs_listid = A.obs_listid AND pc.classid = 2 AND A.attributeid = 3);

--=======================================================================================================================
		
Query 2: Of all Obesity and High Risk Patients, find patients with the highest blood pressure

SELECT DISTINCT(P1.name), A1.attribute_value
FROM patient P1, OBS_LIST L1, OBS_ATTRIBUTE_LIST A1
WHERE P1.patientid = L1.patientid AND L1.obs_listid = A1.obs_listid AND A1.attributeid = 7 
AND A1.attribute_value = (
        SELECT MAX(TO_NUMBER(A.attribute_value))
        FROM patient P, PATIENT_CLASS PC, OBS_LIST L, OBS_ATTRIBUTE_LIST A
        WHERE P.patientid = L.patientid AND L.obs_listid = A.obs_listid AND (PC.classid = 3 OR PC.classid = 4) AND A.attributeid = 7);

--=======================================================================================================================

Query 3: Find patients who have healthfriends with no outstanding alerts.

Select P2.name, COUNT(P1.patientid) AS No_Of_Safe_Friends
FROM health_friends H, patient P1, Patient P2
WHERE P1.patientid NOT IN ( select DISTINCT(alert_for) from alert_info where isgenerated = 1 AND isseen = 0 group by alert_for )
AND ( (H.patientid1 = P2.patientid AND H.patientid2 = p1.patientid) OR (H.patientid2 = P2.patientid AND H.patientid1 = p1.patientid) )
GROUP BY (P2.name)  
ORDER BY P2.name;

--=======================================================================================================================

Query 4: Find patients who live in same city as healthfriend.

SELECT P1.name, P2.name 
FROM patient P1, patient P2, health_friends F
WHERE P1.patientid = F.patientid1 AND P2.patientid = F.patientid2 AND REGEXP_SUBSTR(P1.address,',[^,]+,') = REGEXP_SUBSTR(P2.address,',[^,]+,');

--=======================================================================================================================

QUERY 5: For PatientX, list their healthfriends, ordered by date in which friendships were initiated.

Select DISTINCT(P1.name), H.DATEOFFRIENDSHIP
FROM health_friends H, patient P1
WHERE (H.patientid1 = 'tkerr' AND H.patientid2 = p1.patientid) OR (H.patientid2 = 'tkerr' AND H.patientid1 = p1.patientid)
ORDER BY H.DATEOFFRIENDSHIP;

--=======================================================================================================================

Reporting Query 1: For each patient, find the number of healthfriends made in the last month.

( SELECT (P1.name)
FROM health_friends H, patient P1, patient P2
WHERE H.patientid2 = p1.patientid AND MONTHS_BETWEEN (SYSDATE, dateoffriendship) < 1
AND H.patientid1 = ANY (SELECT NP.patientid FROM Patient NP) )

UNION

( SELECT (P1.name)
FROM health_friends H, patient P1, patient P2
WHERE H.patientid1 = p1.patientid AND MONTHS_BETWEEN (SYSDATE, dateoffriendship) < 1
AND H.patientid2 = ANY (SELECT NP.patientid FROM Patient NP) )

--=======================================================================================================================

Reporting Query 2: For each patient and each type of observation, show the number of such observations recorded by the patients.

SELECT P.name, T.obs_typename, COUNT(*)
FROM obs_list L, patient P, observation_type T
WHERE L.patientid = P.patientid AND T.obs_typeid = L.obs_typeid
GROUP BY (P.name, T.obs_typename);

--=======================================================================================================================

Reporting Query 3: For each patient, and each of their healthfriends, list the number of lingering alerts of the healthfriend.


SELECT  P2.name AS PERSON, P3.name AS FRIEND, COUNT(*) as No_Of_Alert 
FROM alert_info A, health_friends H, patient P1, Patient P2, Patient P3
WHERE A.isGenerated = 1 AND A.isSeen = 0 
AND ((H.patientid1 = P2.patientid AND H.patientid2 = p1.patientid) OR (H.patientid2 = P2.patientid AND H.patientid1 = p1.patientid))
AND P1.patientid = A.alert_for
AND A.alert_for = P3.patientid
GROUP BY (P2.name,P3.name);



