package org.calminfotech.hmo.models;

//import java.sql.Blob;
import java.util.List;
//import java.util.Set;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ehmos_packageV")

public class EhmoPackageV {
	
	@Id
	@GeneratedValue
	@Column(name ="package_id")
	private Integer packageId;
	
	@Column(name ="name")
	private String name;

	@Column(name="parent")
	private Integer parent;
	
	@Column(name="parent_name")
	private String parentName;

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	
	
	
}

	
