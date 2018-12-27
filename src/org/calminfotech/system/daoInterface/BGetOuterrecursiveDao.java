package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.BOuterRecursive;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface BGetOuterrecursiveDao {

	List<BOuterRecursive> fetchAllCategories();
}
