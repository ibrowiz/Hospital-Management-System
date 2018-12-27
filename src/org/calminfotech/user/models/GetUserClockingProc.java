package org.calminfotech.user.models;

import javax.persistence.Entity;

import org.calminfotech.system.models.GetUserAssignmentProc;
import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(name = "spGetUserAssignment",
query = "{CALL sp_user_assignment(:userid)}", 
callable = true, 
resultClass = GetUserAssignmentProc.class)
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class GetUserClockingProc {

}
