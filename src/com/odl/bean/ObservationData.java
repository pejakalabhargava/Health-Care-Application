package com.odl.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The Class ObservationData.
 */
public class ObservationData {
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("ObservationId:");
	builder.append(getObservationId());
	builder.append("\n");
	builder.append("Type:");
	builder.append(getType());
	builder.append("\n");
	builder.append("Date Of Observation:");
	builder.append(getObservationDate());
	builder.append("\n");
	builder.append("Date Of Record:");
	builder.append(getRecordDate());
	builder.append("\n");
	for(Entry<String, String> entry : getAttributeValue().entrySet()) {
	    String key = entry.getKey();
	    String value = entry.getValue();
	    builder.append(key);
		builder.append(":");
		builder.append(value);
		builder.append("\n");
	}
	return builder.toString();
	}
	
	/** The observation id. */
	int observationId;
	
	/** The Type. */
	String Type;
	
	/** The attribute value. */
	Map<String,String> attributeValue;
	
	/** The record date. */
	String recordDate;
	
	/** The observation date. */
	String observationDate;
	
	/** The patient name. */
	private String patientName;
	
	/**
	 * Gets the observation id.
	 *
	 * @return the observationId
	 */
	public int getObservationId() {
		return observationId;
	}
	
	/**
	 * Sets the observation id.
	 *
	 * @param observationId the observationId to set
	 */
	public void setObservationId(int observationId) {
		this.observationId = observationId;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return Type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the type to set
	 */
	public void setType(String type) {
		Type = type;
	}
	
	/**
	 * Gets the attribute value.
	 *
	 * @return the attributeValue
	 */
	public Map<String, String> getAttributeValue() {
		if(attributeValue== null) {
			attributeValue = new HashMap<String,String>();
		}
		return attributeValue;
	}
	
	/**
	 * Sets the attribute value.
	 *
	 * @param attributeValue the attributeValue to set
	 */
	public void setAttributeValue(Map<String, String> attributeValue) {
		this.attributeValue = attributeValue;
	}
	
	/**
	 * Gets the record date.
	 *
	 * @return the recordDate
	 */
	public String getRecordDate() {
		return recordDate;
	}
	
	/**
	 * Sets the record date.
	 *
	 * @param recordDate the recordDate to set
	 */
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	
	/**
	 * Gets the observation date.
	 *
	 * @return the observationDate
	 */
	public String getObservationDate() {
		return observationDate;
	}
	
	/**
	 * Sets the observation date.
	 *
	 * @param observationDate the observationDate to set
	 */
	public void setObservationDate(String observationDate) {
		this.observationDate = observationDate;
	}
	
	/**
	 * Gets the patient name.
	 *
	 * @return the patient name
	 */
	public String getPatientName() {
		return patientName;
	}
	
	/**
	 * Sets the patient name.
	 *
	 * @param patientName the new patient name
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
}
