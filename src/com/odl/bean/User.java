package com.odl.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class User.
 */
public class User {

	/** The user id. */
	String userID;
	
	/** The is patient. */
	boolean isPatient;

	/** The public status. */
	String publicStatus;
	
	/** The sex. */
	String sex;
	
	/** The address. */
	String address;
	
	/** The name. */
	String name;

	/** The age. */
	int age;
	
	/** The alert exits. */
	boolean alertExits;
	
	/** The alert string. */
	String alertString;
	
	/**
	 * Gets the alert string.
	 *
	 * @return the alertString
	 */
	public String getAlertString() {
		return alertString;
	}

	/**
	 * Sets the alert string.
	 *
	 * @param alertString the alertString to set
	 */
	public void setAlertString(String alertString) {
		this.alertString = alertString;
	}

	/** The observation data. */
	List<ObservationData> observationData;
	
	/** The observation types for user. */
	Map<String,String> observationTypesForUser;
	
	/** The alert count. */
	int alertCount;
	
	/**
	 * Checks if is alert exits.
	 *
	 * @return the alertExits
	 */
	public boolean isAlertExits() {
		return alertExits;
	}

	/**
	 * Gets the alert count.
	 *
	 * @return the alertCount
	 */
	public int getAlertCount() {
		return alertCount;
	}

	/**
	 * Sets the alert count.
	 *
	 * @param alertCount the alertCount to set
	 */
	public void setAlertCount(int alertCount) {
		this.alertCount = alertCount;
	}

	/**
	 * Sets the alert exits.
	 *
	 * @param alertExits the alertExits to set
	 */
	public void setAlertExits(boolean alertExits) {
		this.alertExits = alertExits;
	}

	/**
	 * Gets the observation types for user.
	 *
	 * @return the observationTypesForUser
	 */
	public Map<String, String> getObservationTypesForUser() {
		if(observationTypesForUser == null) {
			observationTypesForUser = new HashMap<String,String>();
		}
		return observationTypesForUser;
	}

	/**
	 * Sets the observation types for user.
	 *
	 * @param observationTypesForUser the observationTypesForUser to set
	 */
	public void setObservationTypesForUser(
			Map<String, String> observationTypesForUser) {
		this.observationTypesForUser = observationTypesForUser;
	}

	/**
	 * Gets the observation data.
	 *
	 * @return the observationData
	 */
	public List<ObservationData> getObservationData() {
		if (observationData == null) {
			observationData = new ArrayList<ObservationData>();
		}
		return observationData;
	}

	/**
	 * Sets the observation data.
	 *
	 * @param observationData the observationData to set
	 */
	public void setObservationData(List<ObservationData> observationData) {
		this.observationData = observationData;
	}


	/**
	 * Gets the public status.
	 *
	 * @return the publicStatus
	 */
	public String getPublicStatus() {
		return publicStatus;
	}

	/**
	 * Sets the public status.
	 *
	 * @param publicStatus the publicStatus to set
	 */
	public void setPublicStatus(String publicStatus) {
		this.publicStatus = publicStatus;
	}

	/**
	 * Gets the sex.
	 *
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Sets the sex.
	 *
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 *
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "sex=" + sex + "\nAddress=" + address + "\nName=" + name
				+ "\nAge=" + age;
	}

	/**
	 * Checks if is patient.
	 *
	 * @return the isPatient
	 */
	public boolean isPatient() {
		return isPatient;
	}

	/**
	 * Sets the patient.
	 *
	 * @param isPatient the isPatient to set
	 */
	public void setPatient(boolean isPatient) {
		this.isPatient = isPatient;
	}
	
	
}
