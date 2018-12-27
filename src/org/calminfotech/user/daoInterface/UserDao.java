package org.calminfotech.user.daoInterface;

import java.util.List;

import org.calminfotech.user.models.User;
import org.calminfotech.utils.models.Organisation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface UserDao {

	List<User> fetchAll();

	void save(User user);

	void delete(User user);

	void update(User user);

	User getUserByEmail(String email);

	User getUserById(int userId);

	User getUserByEmailAndPassword(String email, String password);

	List<User> checkUserCredentialsByEmailAndPassword(String email,
			String password);

	public List<User> fetchAllByOrganisation(Organisation organisation);

}
