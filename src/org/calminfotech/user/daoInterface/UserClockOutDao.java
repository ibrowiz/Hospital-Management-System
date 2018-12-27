package org.calminfotech.user.daoInterface;


import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserClockOut;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface UserClockOutDao {
	
	void save(UserClockOut userClockOut);

	void delete(UserClockOut userClockOut);

	void update(UserClockOut userClockOut);
	
	UserClockOut getUserById(int userId);
	
	

}
