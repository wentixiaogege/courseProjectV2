package edu.itu.course.dropwizard.api.beans;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author  作者 E-mail:
 * @date 创建时间：Sep 15, 2015 5:27:13 PM
 * @version 1.0
 * @parameter 
 * @since
 * @return
 */
@XmlRootElement
public class Manipulation {
	
	@Override
	public String toString() {
		return "Manipulation [relayOn=" + relayOn + ", relayOff=" + relayOff
				+ ", frequency=" + frequency + "]";
	}
	@JsonProperty("relayOn")
	int relayOn;
	@JsonProperty("relayOff")
	int relayOff;
	@JsonProperty("frequency")
	int frequency;
	
	public Manipulation() {
		super();
	}
	
	public Manipulation(int relayOn, int relayOff, int frequency) {
		
		this.relayOn = relayOn;
		this.relayOff = relayOff;
		this.frequency = frequency;
	}
	public int getRelayOn() {
		return relayOn;
	}
	public void setRelayOn(int relayOn) {
		this.relayOn = relayOn;
	}
	public int getRelayOff() {
		return relayOff;
	}
	public void setRelayOff(int relayOff) {
		this.relayOff = relayOff;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
}
