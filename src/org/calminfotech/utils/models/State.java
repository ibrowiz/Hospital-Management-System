package org.calminfotech.utils.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "states")
public class State {

	private int stateId;
	private String stateName;
	private String stateCode;
	private Set<LocalGovernmentArea> localGovernmentArea;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "state_id", unique = true, nullable = false)
	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	@Column(name = "state_name", nullable = false)
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Column(name = "state_code", nullable = false)
	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	public Set<LocalGovernmentArea> getLocalGovernmentArea() {
		return localGovernmentArea;
	}

	public void setLocalGovernmentArea(
			Set<LocalGovernmentArea> localGovernmentArea) {
		this.localGovernmentArea = localGovernmentArea;
	}

}
