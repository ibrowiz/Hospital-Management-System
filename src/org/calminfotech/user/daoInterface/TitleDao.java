package org.calminfotech.user.daoInterface;

import java.util.List;

import org.calminfotech.user.models.Title;
import org.calminfotech.utils.models.Organisation;

public interface TitleDao {

	public List<Title> fetchAll();

	public Title getTitleById(int id);

	public void save(Title title);

	public void delete(Title title);

	public void update(Title title);

	public List<Title> fetchAllByOrganisation(Organisation organisation);

}
