package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.BCategoryList;

public interface BCategoryListBo {

	public List<BCategoryList> fetchAll();

	public BCategoryList getCategoryById(int id);
}
