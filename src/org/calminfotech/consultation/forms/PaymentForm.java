package org.calminfotech.consultation.forms;

import javax.persistence.Entity;

@Entity
public class PaymentForm {
	//variables
	private Integer id;
	private Integer pPatient;
	private Integer phmoAmount;
	private Integer pCashAmount;
	private Integer pAtmAmount;
	private Integer pItem;
	private Integer phmo;
	private Integer phmoPackage;
	private Integer pBill;
	
	//getters and setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getpPatient() {
		return pPatient;
	}
	public void setpPatient(Integer pPatient) {
		this.pPatient = pPatient;
	}
	public Integer getPhmoAmount() {
		return phmoAmount;
	}
	public void setPhmoAmount(Integer phmoAmount) {
		this.phmoAmount = phmoAmount;
	}
	public Integer getpCashAmount() {
		return pCashAmount;
	}
	public void setpCashAmount(Integer pCashAmount) {
		this.pCashAmount = pCashAmount;
	}
	public Integer getpAtmAmount() {
		return pAtmAmount;
	}
	public void setpAtmAmount(Integer pAtmAmount) {
		this.pAtmAmount = pAtmAmount;
	}
	public Integer getpItem() {
		return pItem;
	}
	public void setpItem(Integer pItem) {
		this.pItem = pItem;
	}
	public Integer getPhmo() {
		return phmo;
	}
	public void setPhmo(Integer phmo) {
		this.phmo = phmo;
	}
	public Integer getPhmoPackage() {
		return phmoPackage;
	}
	public void setPhmoPackage(Integer phmoPackage) {
		this.phmoPackage = phmoPackage;
	}
	public Integer getpBill() {
		return pBill;
	}
	public void setpBill(Integer pBill) {
		this.pBill = pBill;
	}
}
