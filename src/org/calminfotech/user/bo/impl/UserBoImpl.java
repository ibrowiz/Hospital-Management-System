package org.calminfotech.user.bo.impl;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.calminfotech.system.forms.UserForm;
import org.calminfotech.user.boInterface.RoleBo;
import org.calminfotech.user.boInterface.TitleBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.daoInterface.UserDao;
import org.calminfotech.user.models.Role;
import org.calminfotech.user.models.Title;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserActivation;
import org.calminfotech.user.models.UserProfile;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.AppConfig;
import org.calminfotech.utils.Encryptor;
import org.calminfotech.utils.NotificationCentre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserBoImpl implements UserBo {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleBo roleBo;

	@Autowired
	private Encryptor encryptor;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private TitleBo titleBo;

	@Autowired
	private NotificationCentre notificationCentre;

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		userDao.delete(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public List<User> fetchAll() {
		// TODO Auto-generated method stub
		List<User> list = userDao.fetchAll();
		return list;
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		User user = userDao.getUserById(userId);
		return user;
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		User user = userDao.getUserByEmailAndPassword(email, password);
		return user;
	}

	public List<User> checkUserCredentialsByEmailAndPassword(String email,
			String password) {
		return userDao.checkUserCredentialsByEmailAndPassword(email, password);
	}

	@Override
	public boolean createUser(UserForm userForm,
			HttpServletRequest httpServletRequest) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUsername(userForm.getEmail());
		//fetch role by role bean
		Role role = roleBo.getRoleById(userForm.getUserRole());
		user.setRole(role);
		user.setOrganisation(userIdentity.getOrganisation());
		user.setCreatedBy(userIdentity.getUsername());

		// Users first name and othernames
		UserProfile userProfile = new UserProfile();
		userProfile.setLastName(userForm.getLastName());
		userProfile.setOtherNames(userForm.getOtherNames());

		Title title = this.titleBo.getTitleById(userForm.getTitleId());
		userProfile.setTitle(title);

		user.setUserProfile(userProfile);
		userProfile.setUser(user);

		// Insert the activation to the user
		String code = encryptor.createActivationCode(userForm.getEmail());
		UserActivation userActivation = new UserActivation();
		userActivation.setActivationCode(code);
		//update user save activation code tru user
		Set<UserActivation> userActivations = user.getUserActivation();
		userActivations.add(userActivation);
		user.setUserActivation(userActivations);
		userActivation.setUser(user);
		//save user
		userDao.save(user);
		//save and send mail
		String url = AppConfig.DEVELOPMENTAL_URL + "/user/setup/password/"
				+ code;
		//http://apps.calminfotech.com:9980/HMS/user/setup/password/7dba14030a9e63517a3229443443be0e
		//http://localhost:8080/HMS/user/setup/password/7dba14030a9e63517a3229443443be0e
		String mailContent = "<h1>Activate your account</h1><div>Dear "
				+ userForm.getLastName() + "</div><div>" + "<a href='" + url
				+ "'>Click here to activate your account</a>" + "</div><div>"
				+ userIdentity.getOrganisation().getName() + " Team</div>";

		this.notificationCentre.sendEmail(user.getUsername(),
				"Activate Account", mailContent);

		System.out.println(mailContent);
		return false;
	}  

	@Override
	public List<User> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return this.userDao.fetchAllByOrganisation(userIdentity
				.getOrganisation());
	}

}
