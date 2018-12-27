package org.calminfotech.hmo.daoInterface;

import java.util.List;
import org.calminfotech.hmo.models.EhmoCategoryList;

public interface EhmoCategoryListDao {

public List<EhmoCategoryList> fetchAll();

public EhmoCategoryList getEhmoCategoryListById(int id);

public EhmoCategoryList save(EhmoCategoryList ehmoCategoryList);

public void delete(EhmoCategoryList ehmoCategoryList);

public void update(EhmoCategoryList ehmoCategoryList);

}
