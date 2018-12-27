package org.calminfotech.consultation.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.setup.models.HrUnitCategory;
import org.calminfotech.system.models.LoginSection;
import org.calminfotech.system.models.VisitWorkflowPoint;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "visit_timelines")
public class VisitTimeline {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "visit_id")
	private Visit visit;
	
	@ManyToOne
	@JoinColumn(name = "point_id")
	private VisitWorkflowPoint point;
	
	/*@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;*/
	
	@Column(name = "unit_id")
	private Integer unitCategoryId;
	
	/*@ManyToOne
	@JoinColumn(name = "section_id")
	private LoginSection loginSection;*/
	
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "comment")
	private String comment;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "status")
	private boolean status;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the visit
	 */
	public Visit getVisit() {
		return visit;
	}

	/**
	 * @param visit
	 *            the visit to set
	 */
	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	/**
	 * @return the pointName
	 */
	public VisitWorkflowPoint getPoint() {
		return point;
	}

	public void setPoint(VisitWorkflowPoint point) {
		this.point = point;
	}



	/**
	 * @return the pointUsername
	 */


	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createdDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	/*public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public LoginSection getLoginSection() {
		return loginSection;
	}

	public void setLoginSection(LoginSection loginSection) {
		this.loginSection = loginSection;
	}*/

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUnitCategoryId() {
		return unitCategoryId;
	}

	public void setUnitCategoryId(Integer unitCategoryId) {
		this.unitCategoryId = unitCategoryId;
	}

	/*public int getUnitCategoryId() {
		return unitCategoryId;
	}

	public void setUnitCategoryId(int unitCategoryId) {
		this.unitCategoryId = unitCategoryId;
	}*/

}
