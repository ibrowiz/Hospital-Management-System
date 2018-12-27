package org.calminfotech.user.boInterface;

import java.util.List;

import org.calminfotech.user.forms.TitleForm;
import org.calminfotech.user.models.Title;

public interface TitleBo {

	public List<Title> fetchAll();

	public Title getTitleById(int id);

	public void save(TitleForm titleForm);

	public void delete(Title title);

	public void update(TitleForm titleForm);

	public List<Title> fetchAllByOrganisation();
}
