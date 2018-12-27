package org.calminfotech.consultation.forms;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;

@Entity
public class SearchQueueForm {
	
	private String from;
	
	private String to;
	
	private Integer patientId;
	
	private int mstatus;
	
	private String chkothers;
	
	
	/*public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}*/
	
	public int getMstatus() {
		return mstatus;
	}
	public void setMstatus(int mstatus) {
		this.mstatus = mstatus;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	
	public String getChkothers() {
		return chkothers;
	}
	public void setChkothers(String chkothers) {
		this.chkothers = chkothers;
	}
	
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	
	
}
