package org.calminfotech.hmo.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ehmos_item")
public class EhmoItem {
	
	@Id
	@GeneratedValue
	@Column(name ="item_id")
	private Integer itemId;
	
	@Column(name ="name")
	private String name;

	@Column(name="description")
	private String description;


	@Column(name ="item_package_id")
	private Integer packageId;
	
	
	
	@ManyToOne
	@JoinColumn(name = "hmo_id")
	private Ehmo ehmo;

	
	public Integer getPackageId() {
		return packageId;
	}


	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}




	public Integer getItemId() {
		return itemId;
	}


	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public Ehmo getEhmo() {
		return ehmo;
	}


	public void setEhmo(Ehmo ehmo) {
		this.ehmo = ehmo;
	}
	
	
}

	
