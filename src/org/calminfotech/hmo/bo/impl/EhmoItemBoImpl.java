package org.calminfotech.hmo.bo.impl;

import java.util.List;

import org.calminfotech.hmo.boInterface.EhmoItemBo;
import org.calminfotech.hmo.daoInterface.EhmoItemDao;
import org.calminfotech.hmo.forms.EhmoItemForm;
import org.calminfotech.hmo.models.EhmoItem;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EhmoItemBoImpl implements EhmoItemBo {

	@Autowired
	private EhmoItemDao ehmoItemDao;

	/*@Autowired
	private UserIdentity userIdentity;*/

	@Override
	public List<EhmoItem> fetchAll() {
		// TODO Auto-generated method stub
		return ehmoItemDao.fetchAll();
	}

	@Override
	public EhmoItem getEhmoItemById(int id) {
		// TODO Auto-generated method stub
		return ehmoItemDao.getEhmoItemById(id);
	}

	@Override
	public void save(EhmoItem ehmoItem) {

		ehmoItemDao.save(ehmoItem);
		
		
	}

	@Override
	public void delete(EhmoItem ehmoItem) {
		// TODO Auto-generated method stub
		ehmoItemDao.delete(ehmoItem);
	}

	@Override
	public void update(EhmoItem ehmoItem) {
		/*EhmoItem category = ehmoItemDao.getEhmoItemById(ehmoItem.getItemId());
		ehmoItem.setName(category.getName());
		ehmoItem.setDescription(category.getDescription());
		ehmoItem.setPackageId(category.getPackageId());*/
		//System.out.println("yemiId/"+ehmoItem.getItemId());
        this.ehmoItemDao.update(ehmoItem);
        
	
	}

}
