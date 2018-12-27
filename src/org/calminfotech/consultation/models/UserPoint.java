package org.calminfotech.consultation.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(
	name = "spGetUserVal",
	query = "{CALL sp_user_point(:point, :organisationId)}",
	callable = true,
	resultClass = UserPoint.class
)
@Table(name = "usr_user_permission_assignment")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class UserPoint  implements java.io.Serializable  {

}
