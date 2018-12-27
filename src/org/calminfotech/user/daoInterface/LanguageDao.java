package org.calminfotech.user.daoInterface;

import java.util.List;


import org.calminfotech.user.models.Language;
import org.calminfotech.utils.models.Organisation;

public interface LanguageDao {

	
	public List<Language> fetchAll();

	public Language getLanguageById(int id);

	public void save(Language language);

	public void delete(Language language);

	public void update(Language language);

	public List<Language> fetchAllByOrganisation(Organisation organisation);
	
}
