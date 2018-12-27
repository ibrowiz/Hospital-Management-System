package org.calminfotech.user.boInterface;

import java.util.List;


import org.calminfotech.user.forms.LanguageForm;
import org.calminfotech.user.models.Language;


public interface LanguageBo {

	public List<Language> fetchAll();

	public Language getLanguageById(int id);

	public void save(LanguageForm languageForm);

	public void delete(Language Language);

	public void update(LanguageForm languageForm);

	public List<Language> fetchAllByOrganisation();
	
}
