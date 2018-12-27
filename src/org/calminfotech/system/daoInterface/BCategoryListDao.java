package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.BGlobalItem;
import org.calminfotech.system.models.BCategoryList;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface BCategoryListDao {

	public List<BCategoryList> fetchAll();

	public BCategoryList getCategoryById(int id);

}
