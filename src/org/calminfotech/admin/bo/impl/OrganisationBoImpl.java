package org.calminfotech.admin.bo.impl;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.calminfotech.admin.boInterface.OrganisationBo;
import org.calminfotech.admin.daoInterface.OrganisationDao;
import org.calminfotech.admin.forms.OrganisationEditForm;
import org.calminfotech.admin.forms.OrganisationForm;
import org.calminfotech.system.boInterface.ResourceBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.daoInterface.RoleDao;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserActivation;
import org.calminfotech.user.models.UserType;
import org.calminfotech.utils.AppConfig;
import org.calminfotech.utils.EmailDomain;
import org.calminfotech.utils.Encryptor;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.NotificationCentre;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.UserTypesListDao;
import org.calminfotech.utils.email.EmailSend;
import org.calminfotech.utils.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganisationBoImpl implements OrganisationBo {

	@Autowired
	private OrganisationDao organisationDao;

	@Autowired
	private UserTypesListDao typesListDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private Encryptor encryptor;
	
	@Autowired
	private UserBo userBo;
	
	@Autowired
	private EmailSend emailSend;

	@Autowired
	private UserTypesListDao userTypeList;

	@Autowired
	private ResourceBo resourceBo;
	
	@Autowired
	private StatesList stateBo;
	
	@Autowired
	private LocalGovernmentAreaList lgaBo;

	@Autowired
	private NotificationCentre notificationCentre;
	
	@Autowired
	private EmailDomain emailDomain;

	@Override
	public List<Organisation> fetchAll() {
		// TODO Auto-generated method stub
		return this.organisationDao.fetchAll();
	}

	@Override
	public Organisation getOrganisationById(int id) {
		// TODO Auto-generated method stub
		return this.organisationDao.getOrganisationById(id);
	}

	@Override
	public Organisation save(OrganisationForm organisationForm) {
		/**
		 * Create the organisation, the system admin user, and all the necessary
		 * roles {adminstrator}
		 */

		// Organisation
		Organisation organisation = new Organisation();
		organisation.setName(organisationForm.getName());
		organisation.setDescription(organisationForm.getDescription());
		organisation.setAddress(organisationForm.getAddress());
		organisation.setSystemEmail(organisationForm.getSystemEmail());
		organisation.setHospitalType(organisationForm.getHospitalType());
		organisation.setState(stateBo.getStateById(organisationForm.getStateId()));
		organisation.setLga(lgaBo.getLgaById(organisationForm.getLgaId()));
		//get domain
		@SuppressWarnings("static-access")
		String domain = this.emailDomain.fetchAt(organisationForm.getSystemEmail());
		organisation.setDomain(domain);
		
		// Create the first user
		User user = new User();
		user.setUsername(organisationForm.getSystemUserEmail());
		user.setPassword(organisationForm.getSystemUserPassword());
		user.setOrganisation(organisation);
		user.setActivation(true);
		user.setStatus(true);

		// Set the default user type for hospital admin
		UserType userType = this.userTypeList
				.getUserTypeByType(UserType.HOSP_ADMIN);
		user.setUserType(userType);
		
		// The default behavior for an organisation first user
		user.setRole(roleDao.getRoleById(2));
		organisation.getUsers().add(user);
		this.organisationDao.save(organisation);
		
		//Encrypt password
		Set<UserActivation> userActivations = user.getUserActivation();
		// Add new activation code here
		// Create the encrypted code
		String activationCode = encryptor.createActivationCode(organisationForm.getSystemUserEmail());
		System.out.println("activationCode :"+activationCode);
		//Save activation code
		UserActivation userActivation = new UserActivation();
		userActivation.setActivationCode(activationCode);
		userActivation.setUser(user);
		userActivation.setUsed(false);
		userActivation.setExpired(false);
		userActivation.setCreatedDate(new Date(System.currentTimeMillis()));
		userActivations.add(userActivation);
		//save UserActivation through user
		user.setUserActivation(userActivations);
		//update user
		userBo.update(user);
		//String url = AppConfig.DEVELOPMENTAL_URL+"/user/setup/password/"+activationCode;
		String url = AppConfig.APP_URL+"/user/setup/password/"+activationCode;
		// Send the new user an email
		String message = "Thank you for choosing to use our platform. \n";
		message += "Use the following details to login and configure your organisation account \n";
		message += "Username: " + organisationForm.getSystemUserEmail() + "\n";
		message += "Password: "+organisationForm.getSystemUserPassword()+"\n\n";
		message += "To activate your account and set your password, click the lick: <a href='"+ url +"' title = 'Cllick to register'>Activate Account Now</a> \n";
				
		message += "HMS Team";
		//Send mail
		System.out.println("Before mail send");
		emailSend.processMail(AppConfig.NO_REPLY, null , user.getUsername(), "Opening of HMS Portal for " + organisation.getName(), message);
		System.out.println("After mail sent");
		/*this.notificationCentre.sendEmail(user.getUsername(),
		"Opening of HMS Portal for " + organisation.getName(), message);*/
		return organisation;
	}

	@Override
	public void delete(Organisation organisation) {
		// TODO Auto-generated method stub
		this.organisationDao.delete(organisation);
	}

	@Override
	public void update(OrganisationEditForm organisationForm) {
		// TODO Auto-generated method stub
		Organisation organisation = this.getOrganisationById(organisationForm
				.getId());

		organisation.setName(organisationForm.getName());
		organisation.setDescription(organisationForm.getDescription());
		organisation.setAddress(organisationForm.getAddress());
		organisation.setSystemEmail(organisationForm.getSystemEmail());
		organisation.setHospitalType(organisationForm.getHospitalType());
		/*organisation.setState(stateBo.getStateById(organisationForm.getStateId()));
		organisation.setLga(lgaBo.getLgaById(organisationForm.getLgaId()));*/
		organisation.setState(stateBo.getStateById(10));
		organisation.setLga(lgaBo.getLgaById(17));
		
		this.organisationDao.update(organisation);

	}

}
