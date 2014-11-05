package com.odl.facade;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;

import com.odl.bean.ObservationData;
import com.odl.bean.User;
import com.odl.constants.SQLQUERY;
import com.odl.dataaccess.DBUtil;


/**
 * The Class ODLDataFacade.
 */
public class ODLDataFacade {

	/** The con. */
	Connection con;
	
	/** The all attributes. */
	Map<String,String> allAttributes = new HashMap<String,String>();
	
	/** The all observation types. */
	private Map<String,String> allObservationTypes = new HashMap<String,String>();
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public static Connection getConnection() {
		try {
			return DBUtil.loadDriver();
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Gets the login.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the login
	 */
	public User getLogin(String username, String password) {
		Connection con = getConnection();
		if(con == null) {
			return null;
		}
		String query = SQLQUERY.loginQuery;
		try {
			PreparedStatement pstmt = con.prepareStatement(query,
					new String[] { "patientid" });
			pstmt.setString(1, username);
			ResultSet results = pstmt.executeQuery();
			String dbUserName = null;
			String dbPassword = null;
			String dbName = null;
			int dbAge = 0;
			String dbAddress = null;
			String dbSex = null;
			String dbPublicStatus = null;
			while (results.next()) {
				dbUserName = results.getString("patientid");
				dbPassword = results.getString("pwd");
				dbName = results.getString("name");
				dbAge = results.getInt("age");
				dbAddress = results.getString("address");
				dbSex = results.getString("sex");
				dbPublicStatus = results.getString("public_status");
			}
			pstmt.close();
			results.close();
			con.close();
			if ((dbUserName != null && dbUserName.equals(username))
					&& dbPassword != null && dbPassword.equals(password)) {
				User user = new User();
				user.setPatient(true);
				user.setUserID(username);
				user.setName(dbName);
				user.setAge(dbAge);
				user.setAddress(dbAddress);
				user.setSex(dbSex);
				user.setPublicStatus(dbPublicStatus);
				return user;
			}
		} catch (SQLException e) {
			try {
				con.close();
			} catch (SQLException e1) {
				System.out.println("Login Failed");
			}
			System.out.println("Login Failed");
			return null;
		}
		return null;
	}

	/**
	 * Gets the observation data for user.
	 *
	 * @param user the user
	 * @return the observation data for user
	 */
	public void getObservationDataForUser(User user) {
		if (user == null || user.getUserID() == null) {
			return;
		}
		Connection con = getConnection();
		if(con == null) {
			return;
		}
		
		String query = SQLQUERY.getObsQuery;
		try {
			PreparedStatement pstmt = con.prepareStatement(query,
					new String[] { "patientid" });
			pstmt.setString(1, user.getUserID());
			ResultSet results = pstmt.executeQuery();
			int obsListId= 0;
			String attribute_name = null;
			String attribute_value = null;
			String observationDate = null;
			String recordDate = null;
			String obs_type = null;
			int prev_obs_list_id = -999;
			ObservationData data = null;
			while (results.next()) {
				obsListId = results.getInt("OBS_LISTID");
				attribute_name = results.getString("attribute_name");
				attribute_value = results.getString("attribute_value");
				observationDate = results.getString("observed_date");
				recordDate = results.getString("record_date");
				obs_type = results.getString("obs_typename");
				if(prev_obs_list_id == obsListId) {
					data.getAttributeValue().put(attribute_name, attribute_value);
				} else {
				data = new ObservationData();
				data.setObservationId(obsListId);
				data.setType(obs_type);
				data.setRecordDate(recordDate);
				data.setObservationDate(observationDate);
				data.getAttributeValue().put(attribute_name, attribute_value);
				user.getObservationData().add(data);
				}
				prev_obs_list_id = obsListId;
			}
			pstmt.close();
			results.close();
			con.close();
					
		} catch (SQLException e) {
			System.out.println("View Obs failed");
		}
	}
	
	/**
	 * Gets the patient data for obs type.
	 *
	 * @param user the user
	 * @param obs_type_int the obs_type_int
	 * @return the patient data for obs type
	 */
	public Set<String> getpatientDataForObsType(User user,int obs_type_int) {
		if (user == null || user.getUserID() == null) {
			return null;
		}
		Connection con = getConnection();
		if(con == null) {
			return null;
		}
		
		Map<String,String> patients = populateAllPatients();
		String query = SQLQUERY.getPatientByObsType;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, obs_type_int);
			ResultSet results = pstmt.executeQuery();
			int obsListId= 0;
			String attribute_name = null;
			String attribute_value = null;
			String observationDate = null;
			String recordDate = null;
			String obs_type = null;
			String patientid = null;
			int prev_obs_list_id = -999;
			ObservationData data = null;
			Set<String> patienIds = new HashSet<String>();
			while (results.next()) {
				obsListId = results.getInt("OBS_LISTID");
				attribute_name = results.getString("attribute_name");
				attribute_value = results.getString("attribute_value");
				observationDate = results.getString("observed_date");
				recordDate = results.getString("record_date");
				obs_type = results.getString("obs_typename");
				patientid = results.getString("patientid");
				patienIds.add(patientid);
				if(prev_obs_list_id == obsListId) {
					data.getAttributeValue().put(attribute_name, attribute_value);
				} else {
				data = new ObservationData();
				data.setObservationId(obsListId);
				data.setType(obs_type);
				data.setRecordDate(recordDate);
				data.setObservationDate(observationDate);
				data.getAttributeValue().put(attribute_name, attribute_value);
				user.getObservationData().add(data);
				}
				prev_obs_list_id = obsListId;
			}
			pstmt.close();
			results.close();
			con.close();
		    return  patienIds;
		} catch (SQLException e) {
			System.out.println("View Obs failed");
		}
		return null;
	}

	/**
	 * Gets the observation type for user.
	 *
	 * @param user the user
	 * @return the observation type for user
	 */
	public void getObservationTypeForUser(User user) {
		if (user == null || user.getUserID() == null) {
			return;
		}
		Connection con = getConnection();
		if(con == null) {
			return;
		}
		String query = SQLQUERY.getObsTypeQuery;
		try {
			PreparedStatement pstmt = con.prepareStatement(query,
					new String[] { "patientid" });
			pstmt.setString(1, user.getUserID());
			ResultSet results = pstmt.executeQuery();
			String obs_type_id = null;
			String obs_type_name = null;
			while (results.next()) {
				obs_type_id = results.getString("obs_typeid");
				obs_type_name = results.getString("obs_typename");
				user.getObservationTypesForUser().put(obs_type_name, obs_type_id);
			}
			pstmt.close();
			results.close();
			con.close();
					
		} catch (SQLException e) {
			System.out.println("View Obs failed");
		}

	}

	/**
	 * Gets the attribute list for type.
	 *
	 * @param obs_typeid the obs_typeid
	 * @return the attribute list for type
	 */
	public Map<String,String> getAttributeListForType(String obs_typeid) {
		if (obs_typeid == null) {
			return null;
		}
		Map<String,String> attributeList = new HashMap<>();
		Connection con = getConnection();
		if(con == null) {
			return null;
		}
		String query = SQLQUERY.getObsAttributeQuery;
		try {
			PreparedStatement pstmt = con.prepareStatement(query,
					new String[] { "obs_typeid" });
			pstmt.setString(1, obs_typeid);
			ResultSet results = pstmt.executeQuery();
			String obs_attr_id = null;
			String obs_attr_name = null;
			while (results.next()) {
				obs_attr_id = results.getString("attributeid");
				obs_attr_name = results.getString("attribute_name");
				attributeList.put(obs_attr_name,obs_attr_id);
			}
			pstmt.close();
			results.close();
			con.close();
			return 	attributeList;
		} catch (SQLException e) {
			System.out.println("View Obs failed");
		}


		return null;
	}

	/**
	 * Save observation data.
	 *
	 * @param userID the user id
	 * @param obsId the obs id
	 * @param valueForAttributes the value for attributes
	 * @param date the date
	 * @return true, if successful
	 */
	public boolean saveObservationData(String userID, String obsId,
			Map<String, String> valueForAttributes, java.util.Date date) {
		if(userID == null || valueForAttributes == null || obsId == null) {
			System.out.println("Entry not possible");
			return false;
		}
		
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("YYYY-dd-MM HH:mm:ss");
		Calendar cal = new GregorianCalendar();
		sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		//sdf.applyPattern("YYYY-dd-MM HH:mm:ss");
		sdf.setCalendar(cal);
		String utilDate = sdf.format(date);
		int obsIdInt = 0;
		
		obsIdInt = Integer.parseInt(obsId);
		
		Connection con = getConnection();
		if(con == null) {
			return false;
		}
		
		String query = SQLQUERY.getObsInsQuery;
		try {
			con.setAutoCommit(false);
			String[] key={"obs_listid"};
			PreparedStatement ps = con.prepareStatement(query,key);  
			ps.setString(1, userID);  
			ps.setInt(2, obsIdInt);  
			ps.setString(3, utilDate);
			ps.executeUpdate(); 
			ResultSet generatedKeys = ps.getGeneratedKeys();
			int obsListId = 0;
			if (null != generatedKeys && generatedKeys.next()) {
				obsListId = generatedKeys.getInt(1);
			}
			ps.close();
			generatedKeys.close();
			 query = SQLQUERY.getObsAttrInsQuery;
			for(Entry<String, String> entry : valueForAttributes.entrySet()) {
			    String attrKey = entry.getKey();
			    int keyInt = Integer.parseInt(attrKey);
			    String attrValue = entry.getValue();
			    ps = con.prepareStatement(query); 
			    ps.setInt(1, obsListId);
			    ps.setInt(2, keyInt);
			    ps.setString(3, attrValue);
			    ps.executeUpdate(); 
			}
			con.commit();
			con.close();
		} catch (SQLException e) {
			try {
				con.rollback();
				con.close();
				return false;
			} catch (SQLException e1) {
				return false;
			}
		}  		
		  return true;
	}

	/**
	 * Adds the an observation type.
	 *
	 * @param observationType the observation type
	 * @param attributeList the attribute list
	 * @return true, if successful
	 */
	public boolean  addAnObservationType(String observationType, List<String> attributeList) {
		if(observationType == null || attributeList.isEmpty()) {
			System.out.println("Entry not possible");
			return false;
		}
		Connection con = getConnection();
		if(con == null) {
			return false;
		}
		
		String query = SQLQUERY.addAnObservationTypeQuery;
		try {
			con.setAutoCommit(false);
			String[] key={"obs_typeid"};
			PreparedStatement ps = con.prepareStatement(query,key);  
			ps.setString(1, observationType);  
			ps.executeUpdate(); 
			ResultSet generatedKeys = ps.getGeneratedKeys();
			int obsTYpeId = 0;
			if (null != generatedKeys && generatedKeys.next()) {
				obsTYpeId = generatedKeys.getInt(1);
			}
			ps.close();
			generatedKeys.close();
			 query = SQLQUERY.addAnAttributeQuery;
			for (Iterator<String> iterator = attributeList.iterator(); iterator
					.hasNext();) {
				String attributeValue = (String) iterator.next();
			    ps = con.prepareStatement(query); 
			    ps.setString(1, attributeValue);
			    ps.setInt(2, obsTYpeId);
			    ps.executeUpdate(); 
			}
			con.commit();
			con.close();
		} catch (SQLException e) {
			try {
				con.rollback();
				con.close();
				return false;
			} catch (SQLException e1) {
				return false;
			}
		}  		
		  return true;
	}

	/**
	 * Sets the observation type alerts for user.
	 *
	 * @param user the new observation type alerts for user
	 */
	public void setObservationTypeAlertsForUser(User user) {
		if(user == null) {
			System.out.println("Entry not possible");
		}
		Connection con = getConnection();
		if(con == null) {
			return ;
		}
		
		String query = SQLQUERY.updateAlertTableForObservationQuery;
		try {
			PreparedStatement ps = con.prepareStatement(query,new String[] { "alertid" });  
			ps.setString(1, user.getUserID());  
			ps.setString(2, user.getUserID());  
			ps.setString(3, user.getUserID());  
			ps.executeUpdate(); 
			ps.close();
			con.close();
		} catch (SQLException e) {
			try {
				System.out.println("Error");
				con.close();
			} catch (SQLException e1) {
			  System.out.println("Error");
			}

		}  		
	}
	
	/**
	 * Sets the observation type alerts for user.
	 *
	 * @param user the new observation type alerts for user
	 */
	public void setObservationTypeAlertsForUserViaStoredProc(User user) {
		if(user == null) {
			System.out.println("Entry not possible");
		}
		Connection con = getConnection();
		if(con == null) {
			return ;
		}
		CallableStatement callableStatement = null;
		String updateAlertSql = "{call Update_Alert_Proc(?)}";
		try {
			callableStatement = con.prepareCall(updateAlertSql);
			callableStatement.setString(1, user.getUserID());  
			callableStatement.execute();
			callableStatement.close();
			con.close();
		} catch (SQLException e) {
			try {
				System.out.println("Error");
				con.close();
			} catch (SQLException e1) {
			  System.out.println("Error");
			}

		}  		
	}
	

	/**
	 * Check for alerts and populate.
	 *
	 * @param user the user
	 */
	public void checkForAlertsAndPopulate(User user) {
		if (user == null) {
			return ;
		}
		
		getAttributesAndObsTypes();
		Connection con = getConnection();
		if(con == null) {
			return;
		}
		
		String query = SQLQUERY.getAlertsForUserQuery;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, user.getUserID());
			ResultSet results = pstmt.executeQuery();
			int alertType = 0;
			String alert_OnBehalf = null;
			String obs_typeid = null;
			Date observerdDate = null;
			boolean isSeen = false;
			boolean historyCheck = true;
			StringBuilder sb = new StringBuilder();
			if(user.isAlertExits()) {
				sb.append("There are new alerts for you ");
				sb.append(user.getName());
				sb.append("\n");
				sb.append("----------------------------------------------------------------------------------------------\n");
				} else {
				sb.append("There are no new alerts for you ");
				sb.append(user.getName());
				sb.append("\n");
				sb.append("----------------------------------------------------------------------------------------------\n");
				}
			while (results.next()) {
				alertType = results.getInt("ALERT_TYPE");
				alert_OnBehalf = results.getString("ALERT_ONBEHALF");
				observerdDate = results.getDate("OBSERVED_DATE");
				isSeen = results.getBoolean("ISSEEN");
				if(historyCheck) {
					if (isSeen == true) {
						historyCheck = false;
						sb.append("\n");
						sb.append("----------------------------------------------------------------------------------------------\n");
						sb.append("You Alert History is:\n");
						sb.append("----------------------------------------------------------------------------------------------\n");
					}
				}
				if(alertType == 1) {
					obs_typeid = results.getString("OBS_TYPEID");
					if(user.getUserID().equals(alert_OnBehalf)) {
						sb.append("You have not added any observation for the type ");
						sb.append(getAllObservationTypes().get(obs_typeid));
						sb.append(" since ");
						sb.append(observerdDate);
						sb.append("\n");
					} else {
						sb.append("Your health friend ");
						sb.append(alert_OnBehalf);
						sb.append(" has not added any observation for the type ");
						sb.append(getAllObservationTypes().get(obs_typeid));
						sb.append(" since ");
						sb.append(observerdDate);
						sb.append("\n");
					}
				} else 
				{
					obs_typeid = results.getString("ATTRIBUTEID");
					if(user.getUserID().equals(alert_OnBehalf)) {
						sb.append("Abnormal value observed for ");
						sb.append(allAttributes.get(obs_typeid));
						sb.append(" on ");
						sb.append(observerdDate);
						sb.append(" for you.\n");
					} else {
						sb.append("Your health friend ");
						sb.append(alert_OnBehalf);
						sb.append(" has abnornal value observed for ");
						sb.append(allAttributes.get(obs_typeid));
						sb.append(" on ");
						sb.append(observerdDate);
						sb.append("\n");
					}
				}
			}
			user.setAlertString(sb.toString());
			pstmt.close();
			results.close();
		} catch (SQLException e) {
			try {
				System.out.println("Alert Fetching Failed");
				con.close();
			} catch (SQLException e1) {
				System.out.println("Alert Fetching Failed");		}
		}
	}

	/**
	 * Gets the attributes and obs types.
	 *
	 * @return the attributes and obs types
	 */
	public void getAttributesAndObsTypes() {
		Connection con = getConnection();
		if(con == null) {
			return;
		}
		
		String query = SQLQUERY.getAllAttributes;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet results = pstmt.executeQuery();
			String attributeName;
			String attributeId;
			while (results.next()) {
				attributeName = results.getString("attribute_name");
				attributeId = results.getString("attributeid");
				allAttributes.put(attributeId, attributeName);
			}
			results.close();
			pstmt.close();
			query = SQLQUERY.getAllObservationTypes;
			pstmt = con.prepareStatement(query);
			results = pstmt.executeQuery();
			while (results.next()) {
				attributeName = results.getString("obs_typeid");
				attributeId = results.getString("obs_typename");
				getAllObservationTypes().put(attributeName, attributeId);
			}
			results.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			try {
				System.out.println("Alert Fetching Failed");
				con.close();
			} catch (SQLException e1) {
				System.out.println("Alert Fetching Failed");		}
		}
	}

	/**
	 * Check for active alerts.
	 *
	 * @param user the user
	 */
	public void checkForActiveAlerts(User user) {
		if (user == null) {
			return ;
		}
		
		Connection con = getConnection();
		if(con == null) {
			return ;
		}
		
		String query = SQLQUERY.getAlertsCountForUserQuery;
		try {
			PreparedStatement pstmt = con.prepareStatement(query,new String[] { "alertid" });
			pstmt.setString(1, user.getUserID());
			ResultSet results = pstmt.executeQuery();
			int alert_count = 0;
			while (results.next()) {
				alert_count = results.getInt("noOfAlerts");
			}
			if (alert_count > 0) {
				user.setAlertExits(true);
				user.setAlertCount(alert_count);
			}
			pstmt.close();
			results.close();
			con.close();
		} catch (SQLException e) {
			try {
				System.out.println("Alert Fetching Failed");
				con.close();
			} catch (SQLException e1) {
				System.out.println("Alert Fetching Failed");		}
		}
	}

	/**
	 * Update seen alerts.
	 *
	 * @param user the user
	 */
	public void updateSeenAlerts(User user) {
		if(user == null) {
			System.out.println("Entry not possible");
		}
		Connection con = getConnection();
		if(con == null) {
			return ;
		}
		
		String query = SQLQUERY.updateAlertAsSeenQuery;
		try {
			PreparedStatement ps = con.prepareStatement(query,new String[] { "alertid" });  
			ps.setString(1, user.getUserID());  
			ps.setString(2, user.getUserID());  
			ps.setString(3, user.getUserID());  
			ps.setString(4, user.getUserID());  
			ps.executeUpdate(); 
			ps.close();
			con.close();
		} catch (SQLException e) {
			try {
				System.out.println("Error");
				con.close();
			} catch (SQLException e1) {
			  System.out.println("Error");
			}

		}  		
	}

	/**
	 * Clear all alerts for user.
	 *
	 * @param user the user
	 */
	public void clearAllAlertsForUser(User user) {
		if(user == null) {
			System.out.println("Entry not possible");
		}
		Connection con = getConnection();
		if(con == null) {
			return ;
		}
		
		String query = SQLQUERY.clearAlertseQuery;
		try {
			PreparedStatement ps = con.prepareStatement(query,new String[] { "alertid" });  
			ps.setString(1, user.getUserID());  
			ps.executeUpdate(); 
			ps.close();
			con.close();
		} catch (SQLException e) {
			try {
				System.out.println("Error");
				con.close();
			} catch (SQLException e1) {
			  System.out.println("Error");
			}
		}  		
	}

	/**
	 * Gets the login for health professional.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the login for health professional
	 */
	public User getLoginForHealthProfessional(String username, String password) {
		Connection con = getConnection();
		if(con == null) {
			return null;
		}
		
		String query = SQLQUERY.loginQueryForPhysician;
		try {
			PreparedStatement pstmt = con.prepareStatement(query,
					new String[] { "DOCTORID" });
			pstmt.setString(1, username);
			ResultSet results = pstmt.executeQuery();
			String dbUserName = null;
			String dbPassword = null;
			String dbName = null;
			while (results.next()) {
				dbUserName = results.getString("DOCTORID");
				dbPassword = results.getString("pwd");
				dbName = results.getString("name");
			}
			pstmt.close();
			results.close();
			con.close();
			if ((dbUserName != null && dbUserName.equals(username))
					&& dbPassword != null && dbPassword.equals(password)) {
				User user = new User();
				user.setPatient(false);
				user.setUserID(username);
				user.setName(dbName);
				return user;
			}
		} catch (SQLException e) {
			try {
				con.close();
			} catch (SQLException e1) {
				System.out.println("Error");
			}
			System.out.println("Login Failed");
			return null;
	}
		return null;
}

	/**
	 * Populate all patients.
	 *
	 * @return the map
	 */
	public Map<String, String> populateAllPatients() {
	    Map<String,String> patientList = new HashMap<String,String>();
	 	Connection con = getConnection();
	 	if(con == null) {
			return null;
		}
		
		String query = SQLQUERY.getAllPatientListQuery;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet results = pstmt.executeQuery();
			String name = null;
			String id = null;
			while (results.next()) {
				name = results.getString("name");
				id = results.getString("patientid");
				patientList.put(name,id);
			}
			pstmt.close();
			results.close();
			con.close();
			return 	patientList;
		} catch (SQLException e) {
			try {
				con.close();
			} catch (SQLException e1) {
				//error
			}
			System.out.println("Fetch Patient info failed");
		}
		return null;
	}
	
	/**
	 * Populate all classes.
	 *
	 * @return the map
	 */
	public Map<String, String> populateAllClasses() {
	    Map<String,String> patientClass = new HashMap<String,String>();
	 	Connection con = getConnection();
	 	if(con == null) {
			return null;
		}
		String query = SQLQUERY.getAllPatientClassQuery;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet results = pstmt.executeQuery();
			String name = null;
			String id = null;
			while (results.next()) {
				name = results.getString("CLASSNAME");
				id = results.getString("CLASSID");
				patientClass.put(name,id);
			}
			pstmt.close();
			results.close();
			con.close();
			return 	patientClass;
		} catch (SQLException e) {
			try {
				con.close();
			} catch (SQLException e1) {
				//error
			}
			System.out.println("Fetch Patient class info failed");
		}
		return null;
	}

	/**
	 * Associate patient to class.
	 *
	 * @param patientId the patient id
	 * @param classId the class id
	 * @return true, if successful
	 */
	public boolean associatePatientToClass(String patientId, int classId) {
		if(patientId == null) {
			System.out.println("Entry not possible");
		return false;
		}
		Connection con = getConnection();
		String query = SQLQUERY.updatePatientClassQuery;
		try {
			PreparedStatement ps = con.prepareStatement(query);  
			ps.setInt(1, classId);  
			ps.setString(2, patientId);  
			ps.executeUpdate(); 
			ps.close();
			con.close();
		   return true;
		} catch (SQLException e) {
			try {
				System.out.println("Error");
				con.close();
			} catch (SQLException e1) {
			  System.out.println("Error");
			}
		}  		
		return false;
	}

	/**
	 * Populate all categories.
	 *
	 * @return the map
	 */
	public Map<String, String> populateAllCategories() {
		  Map<String,String> patientClass = new HashMap<String,String>();
		 	Connection con = getConnection();
		 	if(con == null) {
				return null;
			}
			String query = SQLQUERY.getAllCategoriesQuery;
			try {
				PreparedStatement pstmt = con.prepareStatement(query);
				ResultSet results = pstmt.executeQuery();
				String name = null;
				String id = null;
				while (results.next()) {
					name = results.getString("categoryname");
					id = results.getString("categoryid");
					patientClass.put(name,id);
				}
				pstmt.close();
				results.close();
				con.close();
				return 	patientClass;
			} catch (SQLException e) {
				try {
					con.close();
				} catch (SQLException e1) {
					//error
				}
				System.out.println("Fetch category id info failed");
			}
			return null;
}
	
	/**
	 * Populate all observation types.
	 *
	 * @return the map
	 */
	public Map<String, String> populateAllObservationTypes() {
		  Map<String,String> patientClass = new HashMap<String,String>();
		 	Connection con = getConnection();
			if (con == null) {
				return null;
			}
		 	String query = SQLQUERY.getAllObsTypesQuery;
			try {
				PreparedStatement pstmt = con.prepareStatement(query);
				ResultSet results = pstmt.executeQuery();
				String name = null;
				String id = null;
				while (results.next()) {
					name = results.getString("obs_typename");
					id = results.getString("obs_typeid");
					patientClass.put(name,id);
				}
				pstmt.close();
				results.close();
				con.close();
				return 	patientClass;
			} catch (SQLException e) {
				try {
					con.close();
				} catch (SQLException e1) {
					//error
				}
				System.out.println("Fetch obs type id info failed");
			}
			return null;
}

	/**
	 * Associate category to observation type.
	 *
	 * @param catId the cat id
	 * @param typeID the type id
	 * @return true, if successful
	 */
	public boolean associateCategoryToObservationType(int catId, int typeID) {
		Connection con = getConnection();
		if(con == null) {
			return false;
		}
		
		String query = SQLQUERY.associateObsTypeCategoryQuery;
		try {
			PreparedStatement ps = con.prepareStatement(query);  
			ps.setInt(1, catId);  
			ps.setInt(2, typeID);  
			ps.executeUpdate(); 
			ps.close();
			con.close();
		   return true;
		} catch (SQLException e) {
			try {
				System.out.println("Error");
				con.close();
			} catch (SQLException e1) {
			  System.out.println("Error");
			}
		}  		
		return false;
		}
	
	/**
	 * Populate all attributes.
	 *
	 * @return the map
	 */
	public Map<String, String> populateAllAttributes() {
		  Map<String,String> attributes = new HashMap<String,String>();
		 	Connection con = getConnection();
		 	if(con == null) {
				return null;
			}
			String query = SQLQUERY.getAllAttributes;
			try {
				PreparedStatement pstmt = con.prepareStatement(query);
				ResultSet results = pstmt.executeQuery();
				String name = null;
				String id = null;
				while (results.next()) {
					name = results.getString("attribute_name");
					id = results.getString("attributeid");
					attributes.put(name,id);
				}
				pstmt.close();
				results.close();
				con.close();
				return 	attributes;
			} catch (SQLException e) {
				try {
					con.close();
				} catch (SQLException e1) {
					//error
				}
				System.out.println("Fetch obs type id info failed");
			}
			return null;
}

	/**
	 * Gets the aggregated report.
	 *
	 * @param className the class name
	 * @param attributeName the attribute name
	 * @return the aggregated report
	 */
	public String getAggregatedReport(String className, String attributeName) {
		 Map<String,String> attributes = new HashMap<String,String>();
		 	Connection con = getConnection();
		 	if(con == null) {
				return null;
			}
			float min = 0;
			float max = 0;
			float avg = 0;
			
		 	String query = SQLQUERY.getMinAggregatedReport;
			try {
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, className);
				pstmt.setString(2, attributeName);
				ResultSet results = pstmt.executeQuery();
				while (results.next()) {
					min = results.getFloat("minimum");
				}
				pstmt.close();
				results.close();
			    query = SQLQUERY.getMaxAggregatedReport;
			    pstmt = con.prepareStatement(query);
			    pstmt.setString(1, className);
				pstmt.setString(2, attributeName);
			    results = pstmt.executeQuery();
				while (results.next()) {
					max = results.getFloat("maximum");
				}
				pstmt.close();
				results.close();
				query = SQLQUERY.getAvgAggregatedReport;
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, className);
				pstmt.setString(2, attributeName);
			    results = pstmt.executeQuery();
				while (results.next()) {
					avg = results.getFloat("average");
				}
				pstmt.close();
				results.close();
				con.close();
				StringBuilder sb = new StringBuilder();
				sb.append("Aggregated Report for ");
				sb.append(attributeName);
				sb.append(" under class ");
				sb.append(className);
				sb.append("\n----------------------------------------\n");
				sb.append("Maximum value of ");
				sb.append(attributeName);
				sb.append(" is:");
				sb.append(max);
				sb.append("\n");
				sb.append("Minimum value of ");
				sb.append(attributeName);
				sb.append(" is:");
				sb.append(min);
				sb.append("\n");
				sb.append("Average value of ");
				sb.append(attributeName);
				sb.append(" is:");
				sb.append(avg);
				sb.append("\n");
				sb.append("----------------------------------------------\n");
				return sb.toString();
			} catch (SQLException e) {
				try {
					con.close();
					return null;
				} catch (SQLException e1) {
				}
				System.out.println("Fetch obs type id info failed");
			}
			return null;
			}

	/**
	 * Creates the user.
	 *
	 * @param name the name
	 * @param age the age
	 * @param userId the user id
	 * @param password the password
	 * @param address the address
	 * @param sex the sex
	 * @param publicStatus the public status
	 * @return true, if successful
	 */
	public boolean createUser(String name, int age, String userId,
			String password, String address, String sex, String publicStatus) {
		Connection con = getConnection();
		if(con == null) {
			return false;
		}
		
		String query = SQLQUERY.createPatientQuery;
		try {
			PreparedStatement ps = con.prepareStatement(query);  
			ps.setString(1, name);  
			ps.setInt(2, age);  
			ps.setString(3, userId);  
			ps.setString(4, password);  
			ps.setString(5, address);  
			ps.setString(6, sex);
			ps.setString(7, publicStatus);
			ps.executeUpdate(); 
			ps.close();
			con.close();
		   return true;
		} catch (SQLException e) {
			try {
				System.out.println("Error");
				con.close();
			} catch (SQLException e1) {
			  System.out.println("Error");
			}
		}  		
		return false;
	}

	/**
	 * Populate helathfriends.
	 *
	 * @param user the user
	 * @return the map
	 */
	public Map<String, String> populateHelathfriends(User user) {
		 Map<String,String> potentialFriends = new HashMap<String,String>();
		 	Connection con = getConnection();
		 	if(con == null) {
				return null;
			}
			String query = SQLQUERY.getNewHealthFriendsQuery;
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, user.getUserID());  
				ps.setString(2, user.getUserID());  
				ps.setString(3, user.getUserID());  
				ps.setString(4, user.getUserID());  
				ResultSet results = ps.executeQuery();
				String name = null;
				String id = null;
				while (results.next()) {
					name = results.getString("name");
					id = results.getString("patientid");
					potentialFriends.put(name,id);
				}
				ps.close();
				results.close();
				con.close();
				return 	potentialFriends;
			} catch (SQLException e) {
				try {
					con.close();
				} catch (SQLException e1) {
					//error
				}
				System.out.println("Fetch Friends info failed");
			}
			return null;
		}
	
	/**
	 * Adds the a new friend.
	 *
	 * @param user the user
	 * @param friendId the friend id
	 * @return true, if successful
	 */
	public boolean addANewFriend(User user, String friendId) {
		if(user == null) {
			System.out.println("Entry not possible");
			return false;
		}
		Connection con = getConnection();
		if(con == null) {
			return false;
		}
		
		String query = SQLQUERY.addANewFriendQuery;
		try {
			PreparedStatement ps = con.prepareStatement(query);  
			ps.setString(1, user.getUserID());  
			ps.setString(2, friendId);  
			int status = ps.executeUpdate(); 
			ps.close();
			con.close();
			if(status >0){
				return true;
			}
			return false;
		} catch (SQLException e) {
			try {
				System.out.println("Error");
				con.close();
				return false;
			} catch (SQLException e1) {
			  System.out.println("Error");
			}
			return false;
		}  		
	}

	/**
	 * Populate helathfriends at risk.
	 *
	 * @param user the user
	 * @return the list
	 */
	public List<String> populateHelathfriendsAtRisk(User user) {
		 List<String> riskFriend = new ArrayList<String>();
		 	Connection con = getConnection();
		 	if(con == null) {
				return null;
			}
			String query = SQLQUERY.getHealthFriendsAtRiskQuery;
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, user.getUserID());  
				ps.setString(2, user.getUserID());  
				ResultSet results = ps.executeQuery();
				String name = null;
				String id = null;
				while (results.next()) {
					name = results.getString("High_Risk_Friend");
					riskFriend.add(name);
				}
				ps.close();
				results.close();
				con.close();
				return riskFriend;
			} catch (SQLException e) {
				try {
					con.close();
				} catch (SQLException e1) {
					//error
				}
				System.out.println("Fetch Friends info failed");
			}
			return null;
	}

	/**
	 * Send message.
	 *
	 * @param user the user
	 * @param friendId the friend id
	 * @param message the message
	 * @return true, if successful
	 */
	public boolean SendMessage(User user, String friendId, String message) {
		if(user == null) {
			System.out.println("Entry not possible");
			return false;
		}
		Connection con = getConnection();
		if(con == null) {
			return false;
		}
		
		String query = SQLQUERY.sendMessageQuery;
		try {
			PreparedStatement ps = con.prepareStatement(query);  
			ps.setString(1, user.getUserID());  
			ps.setString(2, friendId);  
			ps.setString(3, message);  
			int status = ps.executeUpdate(); 
			ps.close();
			con.close();
			if(status >0){
				return true;
			}
			return false;
		} catch (SQLException e) {
			try {
				System.out.println("Error");
				con.close();
				return false;
			} catch (SQLException e1) {
			  System.out.println("Error");
			}
			return false;
		}  		
	}

	/**
	 * Clear message for user.
	 *
	 * @param user the user
	 */
	public void clearMessageForUser(User user) {
		if(user == null) {
			System.out.println("Entry not possible");
		}
		Connection con = getConnection();
		if(con == null) {
			return ;
		}
		
		String query = SQLQUERY.deleteMessageForUserQuery;
		try {
			PreparedStatement ps = con.prepareStatement(query);  
			ps.setString(1, user.getUserID());  
			ps.executeUpdate(); 
			ps.close();
			con.close();
		} catch (SQLException e) {
			try {
				System.out.println("Error");
				con.close();
			} catch (SQLException e1) {
			  System.out.println("Error");
			}
		}
		}

	/**
	 * Gets the message foruser.
	 *
	 * @param user the user
	 * @return the message foruser
	 */
	public String getMessageForuser(User user) {
		if (user == null) {
			return null;
		}
		
		Connection con = getConnection();
		if(con == null) {
			return null;
		}
		
		String query = SQLQUERY.getMessageForUserQuery;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, user.getUserID());
			ResultSet results = pstmt.executeQuery();
			StringBuilder sb = new StringBuilder();
			int count=0;
			while (results.next()) {
				count++;
				String MessageFrom = results.getString("name");
				String message  = results.getString("MESSAGESTRING");
				sb.append("------------------------------------------------------------\n");
				sb.append("Meaasge from: "+ MessageFrom);
				sb.append("\n");
				sb.append(message);
				sb.append("\n------------------------------------------------------------\n");
			}
			if(count == 0){ 
				return "There are no messages to you.";
			}
			StringBuilder str = new StringBuilder("There are: "+count+ " messages in you inbox\n");
			str.append(sb);
			pstmt.close();
			results.close();
			con.close();
			return str.toString();
		} catch (SQLException e) {
			try {
				System.out.println("Alert Fetching Failed");
				con.close();
			} catch (SQLException e1) {
				System.out.println("Alert Fetching Failed");		}
		}
		return null;
	}
	
	/**
	 * Populate helathfriends list.
	 *
	 * @param user the user
	 * @return the list
	 */
	public List<String> populateHelathfriendsList(User user) {
		 List<String> riskFriend = new ArrayList<String>();
		 	Connection con = getConnection();
		 	if(con == null) {
				return null;
			}
			String query = SQLQUERY.getHealthFriendsForUserQuery;
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, user.getUserID());  
				ps.setString(2, user.getUserID());  
				ResultSet results = ps.executeQuery();
				String name = null;
				String id = null;
				while (results.next()) {
					name = results.getString("friends");
					riskFriend.add(name);
				}
				ps.close();
				results.close();
				con.close();
				return riskFriend;
			} catch (SQLException e) {
				try {
					con.close();
				} catch (SQLException e1) {
					//error
				}
				System.out.println("Fetch Friends info failed");
			}
			return null;
	}

	/**
	 * Populate health friends.
	 *
	 * @param user the user
	 * @return the string
	 */
	public String populateHealthFriends(User user) {
		if (user == null) {
			return null;
		}
		
		Connection con = getConnection();
		if(con == null) {
			return null;
		}
		
		String query = SQLQUERY.getHealthFriendsForUserQuery;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserID());
			ResultSet results = pstmt.executeQuery();
			StringBuilder sb = new StringBuilder();
			int count=0;
			while (results.next()) {
				count++;
				String friend  = results.getString("friends");
				sb.append("\n---------\n");
				sb.append(friend);
			}
			if(count == 0){ 
				return "There are no health friends.";
			}
			sb.append("\n---------");
			StringBuilder str = new StringBuilder("There are: "+count+ " helath friends for you.\n");
			str.append(sb);
			pstmt.close();
			results.close();
			con.close();
			return str.toString();
		} catch (SQLException e) {
			try {
				System.out.println("Alert Fetching Failed");
				con.close();
			} catch (SQLException e1) {
				System.out.println("Alert Fetching Failed");		}
		}
		return null;
	}

	/**
	 * Gets the all observation types.
	 *
	 * @return the all observation types
	 */
	public Map<String,String> getAllObservationTypes() {
		return allObservationTypes;
	}

	/**
	 * Sets the all observation types.
	 *
	 * @param allObservationTypes the all observation types
	 */
	public void setAllObservationTypes(Map<String,String> allObservationTypes) {
		this.allObservationTypes = allObservationTypes;
	}

	
}