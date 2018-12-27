package org.calminfotech.system.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(
	name = "spGetDeleteUserCheckedVal",
	query = "{CALL sp_delete_user_checked_values(:userid)}",
	callable = true,
	resultClass = UserAssgnment.class
)
@Table(name = "usr_user_permission_assignment")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class UserAssgnment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "user_id",unique = false, nullable = true)
	private Integer userId;
	
	@Column(name = "permission_id", nullable = true)
	private Integer permissionId;
	
	//getter and setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
}
