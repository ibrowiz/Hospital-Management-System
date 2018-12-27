package org.calminfotech.admin.bo.impl;

import java.util.List;

import org.calminfotech.admin.boInterface.HospitalDirectorBo;
import org.calminfotech.admin.boInterface.OrganisationBo;
import org.calminfotech.admin.daoInterface.HospitalDirectorDao;
import org.calminfotech.admin.forms.HospitalDirectorForm;
import org.calminfotech.admin.models.HospitalDirector;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.BankList;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HospitalDirectorBoImpl implements HospitalDirectorBo {

	@Autowired
	private HospitalDirectorDao directorDao;

	@Autowired
	private StatesList stateList;

	@Autowired
	private LocalGovernmentAreaList lgaList;

	@Autowired
	private BankList bankList;

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	OrganisationBo hospitalBo;

	@Override
	public List<HospitalDirector> fetchAll() {
		return this.directorDao.fetchAll();
	}

	@Override
	public HospitalDirector getHospitalDirectorById(int directorId) {
		return this.directorDao.getHospitalDirectorById(directorId);
	}
	
	@Override
	public void save(HospitalDirector director) {
		directorDao.save(director);
	}
	
	

	/*@Override
	public HospitalDirector save(HospitalDirectorForm directorForm) {
		//Organisation hospital = hospitalBo.getOrganisationById(directorForm.getOrganisationId());
		HospitalDirector director = new HospitalDirector();
		
		try
		{
			director.setDirectorLastName(directorForm.getDirectorLastName());
			director.setDirectorFirstName(directorForm.getDirectorFirstName());
			director.setDirectorEmail(directorForm.getDirectorEmail());
			director.setDirectorPhone(directorForm.getDirectorPhone());
			director.setCreatedBy(userIdentity.getUsername());
			director.setOrganisation(hospitalBo.getOrganisationById(directorForm.getOrganisationId()));
			Blob blob = Hibernate.createBlob(directorForm.getDirectorAvatar().getInputStream());
			director.setDirectorAvatar(blob);
		
			String contentType =  directorForm.getDirectorAvatar().getContentType();
			director.setAvatarContentType(contentType);
		
			directorDao.save(director);
		}
		catch(IOException ie){
			
		}
			return director;
	}*/

	@Override
	public void delete(HospitalDirector director) {
		this.directorDao.delete(director);
	}

	@Override
	public void update(HospitalDirectorForm directorForm) {
		HospitalDirector director = directorDao.getHospitalDirectorById(directorForm.getDirectorId());
		
			director.setDirectorId(directorForm.getDirectorId());
			director.setDirectorLastName(directorForm.getDirectorLastName());
			director.setDirectorFirstName(directorForm.getDirectorFirstName());
			director.setDirectorEmail(directorForm.getDirectorEmail());
			director.setDirectorPhone(directorForm.getDirectorPhone());		
			
			directorDao.update(director);
	

	}

	@Override
	public List<HospitalDirector> fetchAllDirectorByOrganisation(
			Organisation organisation) {
		return directorDao.fetchAllDirectorByOrganisation(organisation);
	}
}
