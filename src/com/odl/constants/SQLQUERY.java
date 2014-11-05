package com.odl.constants;

// TODO: Auto-generated Javadoc
/**
 * The Class SQLQUERY.
 */
public class SQLQUERY {

	/** The Constant loginQuery. */
	public static final String loginQuery = "SELECT patientid,pwd,name,age,address,sex,public_status FROM  PATIENT WHERE patientid = ?";
	
	/** The Constant getObsQuery. */
	public static final String getObsQuery = "select ol.OBS_LISTID, p.patientid, oat.attribute_name, oal.attribute_value, to_char(ol.observed_date,'YYYY-DD-MM HH24:MI:SS') as observed_date, to_char(ol.record_date,'YYYY-DD-MM HH24:MI:SS') as record_date, ot.obs_typename " +
	                                          "from obs_list OL, obs_attribute_list OAL, patient P,observation_type OT, observation_attribute OAT where " +
	                                          "p.patientid=ol.patientid and ol.obs_listid=oal.obs_listid and OT.obs_typeid = ol.obs_typeid " +
	                                          "and oal.attributeid = OAT.attributeid and p.patientid=? order by obs_listid asc";
	
    /** The Constant getObsTypeQuery. */
    public static final String getObsTypeQuery = "select obs_typeid, obs_typename from observation_type where categoryid in(select categoryid from "+
		   									"observation_category where categoryname <> 'Physiological') union SELECT c.OBS_TYPEID, c.OBS_TYPENAME "+
		   									"FROM CLASS_HAS_TYPES a,PATIENT_HAS_CLASS b,OBSERVATION_TYPE c where a.classid= b.classid and c.OBS_TYPEID = a.obs_typeid "+
		                                    "and b.patientid=?";
 
 /** The Constant getPatientByObsType. */
 public static final String getPatientByObsType = "select ol.OBS_LISTID, p.patientid, oat.attribute_name, oal.attribute_value, to_char(ol.observed_date,'YYYY-DD-MM HH24:MI:SS') as observed_date, to_char(ol.record_date,'YYYY-DD-MM HH24:MI:SS') as record_date, ot.obs_typename " +
                           "from obs_list OL, obs_attribute_list OAL, patient P,observation_type OT, observation_attribute OAT where " +
                           "p.patientid=ol.patientid and ol.obs_listid=oal.obs_listid and OT.obs_typeid = ol.obs_typeid " +
                           "and oal.attributeid = OAT.attributeid and ol.obs_typeid=? order by obs_listid asc";

    /** The Constant getObsAttributeQuery. */
    public static final String getObsAttributeQuery = "select attributeid, attribute_name from observation_attribute  where obs_typeid = ?";

    /** The get obs ins query. */
    public static String getObsInsQuery = "insert into obs_list(obs_listid,patientid,obs_typeid,observed_date) values(obs_list_id.nextval,?,?,TO_DATE(?,'YYYY-DD-MM HH24:MI:SS'))";

    /** The get obs attr ins query. */
    public static String getObsAttrInsQuery = "insert into obs_attribute_list values(?,?,?)";
    
    /** The add an observation type query. */
    public static String addAnObservationTypeQuery = "INSERT INTO OBSERVATION_TYPE VALUES(observation_typeid.nextVal,?,4)";
    
    /** The add an attribute query. */
    public static String addAnAttributeQuery = "INSERT INTO OBSERVATION_ATTRIBUTE VALUES (observation_attributeid.nextval,?,?)";

	/** The update alert table for observation query. */
	public static String updateAlertTableForObservationQuery = "UPDATE ALERT_INFO set isGenerated = 1 where alert_for = ? AND isGenerated = 0 AND " +
				"( (alert_OnBehalf = ? AND (SYSDATE - observed_date) > 7) OR (alert_OnBehalf <> ? AND (SYSDATE - observed_date) > 14) )";


	/** The get alerts for user query. */
	public static String getAlertsForUserQuery = "SELECT *  FROM ALERT_INFO where alert_for = ? AND isGenerated = 1 Order by isSeen";

	/** The get alerts count for user query. */
	public static String getAlertsCountForUserQuery = "SELECT count(alertid) as noOfAlerts FROM ALERT_INFO where alert_for = ? AND isSeen = 0 AND isGenerated = 1";

	/** The get all attributes. */
	public static String getAllAttributes = "select attribute_name, attributeid from observation_attribute";

	/** The get all observation types. */
	public static String getAllObservationTypes = "select obs_typeid, obs_typename from observation_type";

   //public static String updateAlertAsSeenQuery = "UPDATE ALERT_INFO set isSeen = 1 where isSeen = 0 " +
	//"AND isGenerated = 1 AND ( (alert_type = 1 AND alert_OnBehalf = ?) OR (alert_type = 2 AND alert_for = ?) OR (alert_type = 1 AND alert_for = ?) )";
	
	/** The update alert as seen query. */
   public static String updateAlertAsSeenQuery = "UPDATE ALERT_INFO set isSeen = 1 where isSeen = 0 AND "+
			"(isGenerated = 1 AND alert_type = 1 AND alert_for = ?) OR (alert_type = 1 AND alert_OnBehalf = ? AND alert_for <> ? AND isGenerated = 1) "+
			"OR (alert_type = 2 AND alert_for = ? AND isGenerated = 1)";
	
	/** The clear alertse query. */
	public static String clearAlertseQuery = "Delete from ALERT_INFO where alert_for = ?";

	/** The login query for physician. */
	public static String loginQueryForPhysician = "SELECT DOCTORID,PWD,name FROM  physician WHERE DOCTORID = ?";

	/** The get all patient list query. */
	public static String getAllPatientListQuery = "select name,patientid from patient";

	/** The get all patient class query. */
	public static String getAllPatientClassQuery = "select classname,classid from patient_class";

	/** The update patient class query. */
	public static String updatePatientClassQuery ="Update PATIENT_HAS_CLASS SET classid = ? WHERE patientid = ?";

	/** The get all categories query. */
	public static String getAllCategoriesQuery = "select categoryid, categoryname from observation_category";

	/** The get all obs types query. */
	public static String getAllObsTypesQuery = "select obs_typeid, obs_typename from observation_type";

	/** The associate obs type category query. */
	public static String associateObsTypeCategoryQuery ="UPDATE OBSERVATION_TYPE SET categoryid = ? where OBS_TYPEID = ?";

	/** The get min aggregated report. */
	public static String getMinAggregatedReport="SELECT MIN(TO_NUMBER(A.attribute_value)) as minimum " +
	"FROM patient P, PATIENT_CLASS PC, PATIENT_HAS_CLASS C, OBS_LIST L, OBS_ATTRIBUTE_LIST A, OBSERVATION_ATTRIBUTE O "+
	"WHERE P.patientid = C.patientid AND PC.classid = C.classid AND PC.classname = ? " +
	"AND P.patientid = L.patientid AND L.obs_listid = A.obs_listid AND A.attributeid = O.attributeid "+
	"AND O.attribute_name = ?";

	/** The get max aggregated report. */
	public static String getMaxAggregatedReport="SELECT MAX(TO_NUMBER(A.attribute_value)) as maximum " +
			"FROM patient P, PATIENT_CLASS PC, PATIENT_HAS_CLASS C, OBS_LIST L, OBS_ATTRIBUTE_LIST A, OBSERVATION_ATTRIBUTE O "+
			"WHERE P.patientid = C.patientid AND PC.classid = C.classid AND PC.classname = ? " +
			"AND P.patientid = L.patientid AND L.obs_listid = A.obs_listid AND A.attributeid = O.attributeid "+
			"AND O.attribute_name = ?";

	/** The get avg aggregated report. */
	public static String getAvgAggregatedReport="SELECT AVG(TO_NUMBER(A.attribute_value)) as average " +
			"FROM patient P, PATIENT_CLASS PC, PATIENT_HAS_CLASS C, OBS_LIST L, OBS_ATTRIBUTE_LIST A, OBSERVATION_ATTRIBUTE O "+
			"WHERE P.patientid = C.patientid AND PC.classid = C.classid AND PC.classname = ? " +
			"AND P.patientid = L.patientid AND L.obs_listid = A.obs_listid AND A.attributeid = O.attributeid "+
			"AND O.attribute_name = ?";

	/** The create patient query. */
	public static String createPatientQuery = "insert into patient(name,age,patientid,pwd,address,sex,public_status) values(?,?,?,?,?,?,?)";

	/** The get new health friends query. */
	public static String getNewHealthFriendsQuery = "SELECT DISTINCT P.name, P.patientid FROM patient_has_class C, Patient P " +
            "WHERE C.patientid <> ? AND P.patientid = C.patientid AND P.public_status = 'Y' " + 
			"AND C.classid = ANY (select classid from patient_has_class where patientid = ?) "+
            "AND C.patientid NOT IN (Select DISTINCT(patientid1) FROM health_friends H2 WHERE H2.patientid2 = ?) "+
            "AND C.patientid NOT IN (Select DISTINCT(patientid2) FROM health_friends H2 WHERE H2.patientid1 = ?)";

	/** The add a new friend query. */
	public static String addANewFriendQuery= "INSERT INTO HEALTH_FRIENDS(friendShipId,patientid1,patientid2) values(friendship_seq.nextval,?,?)";

	/** The get health friends at risk query. */
	public static String getHealthFriendsAtRiskQuery = "Select alert_for AS High_Risk_Friend FROM (SELECT alert_for, count(*) AS No_Of_Alert,ISGENERATED,ISSEEN  FROM ALERT_INFO GROUP BY alert_for,ISGENERATED,ISSEEN having  ISGENERATED=1 and ISSEEN=0) "+
           "WHERE No_Of_Alert > 5 AND (alert_for = ANY (Select DISTINCT(patientid1) FROM health_friends H2 WHERE H2.patientid2 = ?) "+
            "OR alert_for = ANY(Select DISTINCT(patientid2) FROM health_friends H2 WHERE H2.patientid1 = ?))";

	/** The send message query. */
	public static String sendMessageQuery="INSERT INTO FRIENDMESSAGE(messageFrom,messageTo,messageString) VALUES(?,?,?)";

	/** The get message for user query. */
	public static String getMessageForUserQuery = "select p.name as name,MESSAGESTRING from friendmessage ,patient p where MESSAGETO=? and p.patientid=messagefrom";
	
	/** The delete message for user query. */
	public static String deleteMessageForUserQuery = "delete from friendmessage where MESSAGETO=?";

	/** The get health friends for user query. */
	public static String getHealthFriendsForUserQuery="Select DISTINCT(patientid1) as friends FROM health_friends H2 WHERE H2.patientid2 = ? "+
												"UNION select DISTINCT(patientid2) FROM health_friends H2 WHERE H2.patientid1 = ?";
	}
		