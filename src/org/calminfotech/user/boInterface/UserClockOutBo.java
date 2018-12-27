package org.calminfotech.user.boInterface;


import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserClockOut;

public interface UserClockOutBo {
	
	void save(UserClockOut userClockOut);

	void delete(UserClockOut userClockOut);

	void update(UserClockOut userClockOut);
	

	UserClockOut getUserClockOutById(int userId);

}
