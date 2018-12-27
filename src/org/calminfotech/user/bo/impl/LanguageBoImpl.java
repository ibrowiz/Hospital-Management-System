package org.calminfotech.user.bo.impl;

import java.util.List;

import org.calminfotech.user.boInterface.LanguageBo;
import org.calminfotech.user.daoInterface.LanguageDao;
import org.calminfotech.user.forms.LanguageForm;
import org.calminfotech.user.models.Language;
import org.calminfotech.user.models.Title;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LanguageBoImpl implements LanguageBo {

	@Autowired
	private LanguageDao languageDao;

	@Autowired
	private UserIdentity userIdentity;

	public List<Language> fetchAll() {
		return languageDao.fetchAll();

	}

	public Language getLanguageById(int id) {

		return languageDao.getLanguageById(id);

	}

	public void save(LanguageForm languageForm) {
		
		Language language = new Language();
		language.setName(languageForm.getName());
		language.setOrganisation(userIdentity.getOrganisation());
		languageDao.save(language);
		
		

	}

	public void delete(Language Language) {

		languageDao.delete(Language);
	}

	public void update(LanguageForm languageForm) {

		
		Language language = languageDao.getLanguageById(languageForm.getId());
		language.setName(languageForm.getName());
	
		languageDao.update(language);
	
		
		
	}

	public List<Language> fetchAllByOrganisation() {
		return this.languageDao.fetchAllByOrganisation(userIdentity
				.getOrganisation());
	}

}
