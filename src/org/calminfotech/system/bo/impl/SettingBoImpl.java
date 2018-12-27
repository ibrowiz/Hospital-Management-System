package org.calminfotech.system.bo.impl;

import org.calminfotech.admin.daoInterface.OrganisationDao;
import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.system.daoInterface.SettingDao;
import org.calminfotech.system.forms.OrganisationSettingForm;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SettingBoImpl implements SettingBo {

	@Autowired
	private SettingDao settingDao;

	@Autowired
	private OrganisationDao organisationDao;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public void update(OrganisationSettingForm organisationSettingForm) {
		// TODO Auto-generated method stub

		Organisation organisation = this.organisationDao
				.getOrganisationById(this.userIdentity.getOrganisation()
						.getId());
		organisation.setName(organisationSettingForm.getName());
		organisation.setAddress(organisationSettingForm.getAddress());
		organisation.setSystemEmail(organisationSettingForm.getSystemEmail());
		organisation.setConsultationCode(organisationSettingForm
				.getConsultationCode());

		this.organisationDao.update(organisation);
		// Update the userIdentity Bean
		this.userIdentity.setOrganisation(organisation);
	}

}
