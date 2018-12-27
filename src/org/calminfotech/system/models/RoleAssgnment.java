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
	name = "spGetDeleteCheckedVal",
	query = "{CALL sp_delete_role_checked_values(:roleid)}",
	callable = true,
	resultClass = RoleAssgnment.class
)
@Table(name = "usr_role_permission_assignment")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class RoleAssgnment implements java.io.Serializable {
	
	private static final long serialVersionUID = -9160881819374280018L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "role_id",unique = false, nullable = true)
	private Integer roleId;
	
	@Column(name = "permission_id", nullable = true)
	private Integer permissionId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

}