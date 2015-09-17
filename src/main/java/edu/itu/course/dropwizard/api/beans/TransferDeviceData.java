package edu.itu.course.dropwizard.api.beans;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class DeviceData.
 */
@XmlRootElement
public class TransferDeviceData {

	/** The id. */
	@JsonProperty("id")
	private String id;
    
    /** The device id. */
	@JsonProperty("deviceId")
    private String deviceId;
    
    /** The data. */
	@JsonProperty("data")
    private String data;
    
    /** The timestamp. */
	@JsonProperty("timestamp")
    private String timestamp;
	
	
	public TransferDeviceData(){
		super();
	}
	/**
	 * Instantiates a new device data.
	 *
	 * @param id the id
	 * @param deviceId the device id
	 * @param data the data
	 * @param timestamp the timestamp
	 */
	public TransferDeviceData(String id,String deviceId, String data, String timestamp) {
		this.id = id;
		this.deviceId = deviceId;
		this.data = data;
		this.timestamp = timestamp;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/* (non Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/ 
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeviceData [id=" + id + ", deviceId=" + deviceId + ", data="
				+ data + ", timestamp=" + timestamp + "]";
	}

	
}
