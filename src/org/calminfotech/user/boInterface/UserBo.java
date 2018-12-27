package org.calminfotech.user.boInterface;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.calminfotech.system.forms.UserForm;
import org.calminfotech.user.models.User;

public interface UserBo {

	void save(User user);

	void delete(User user);

	void update(User user);

	User getUserByEmail(String email);

	List<User> fetchAll();

	User getUserById(int userId);

	User getUserByEmailAndPassword(String email, String password);

	List<User> checkUserCredentialsByEmailAndPassword(String email,
			String password);

	boolean createUser(UserForm userForm, HttpServletRequest httpServletRequest);

	public List<User> fetchAllByOrganisation();

}
