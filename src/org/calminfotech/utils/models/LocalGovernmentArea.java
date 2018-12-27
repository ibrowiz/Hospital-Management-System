package org.calminfotech.utils.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lgas")
public class LocalGovernmentArea {

	private int localGovernmentAreaId;
	private String localGovernmentAreasName;
	private State state;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lga_id", unique = true, nullable = false)
	public int getLocalGovernmentAreaId() {
		return localGovernmentAreaId;
	}

	public void setLocalGovernmentAreaId(int localGovernmentAreaId) {
		this.localGovernmentAreaId = localGovernmentAreaId;
	}

	@Column(name = "lga_name", nullable = false)
	public String getLocalGovernmentAreasName() {
		return localGovernmentAreasName;
	}

	public void setLocalGovernmentAreasName(String localGovernmentAreasName) {
		this.localGovernmentAreasName = localGovernmentAreasName;
	}

	@ManyToOne
	@JoinColumn(name = "state_id")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
