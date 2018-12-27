package org.calminfotech.setup.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "allergy_list")
public class AllergyDetail {

	private Integer allergy_id;
	private String allergy_name;
	private AllergyCategory Allergy_Category;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "allergy_id", unique = true, nullable = false)
	public Integer getAllergy_id() {
		return allergy_id;
	}

	public void setAllergy_id(Integer allergy_id) {
		this.allergy_id = allergy_id;
	}

	public String getAllergy_name() {
		return allergy_name;
	}

	public void setAllergy_name(String allergy_name) {
		this.allergy_name = allergy_name;
	}

	@ManyToOne
	@JoinColumn(name = "allergy_category_id")
	public AllergyCategory getAllergy_Category() {
		return Allergy_Category;
	}

	public void setAllergy_Category(AllergyCategory allergy_Category) {
		Allergy_Category = allergy_Category;
	}

}
