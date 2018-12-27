package org.calminfotech.setup.daoInterface;

import java.util.List;

import org.calminfotech.setup.models.AllergyCategoryView;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface AllergyCategoryViewDao {
	
	public List<AllergyCategoryView> fetchAll();

	public AllergyCategoryView getAllergyCatViewById(int id);
	
	public List<AllergyCategoryView> fetchAllByOrganisation(int organisationid);

	//public MultilevelCategoryF save(MultilevelCategoryF multilevelCategoryF);

	//public void delete(MultilevelCategoryV multilevelCategoryV);

	//public void update(MultilevelCategoryV multilevelCategoryV);
	
}
