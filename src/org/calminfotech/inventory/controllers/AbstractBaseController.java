package org.calminfotech.inventory.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.calminfotech.inventory.boInterface.InventoryManagerBo;
import org.calminfotech.inventory.utils.MenuItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.utils.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

public class AbstractBaseController {
	/**
     * Logger for this class and subclasses
     */
    protected final Log logger = LogFactory.getLog(getClass());
	
    @Autowired
	protected InventoryManagerBo inventoriesManagerBo;


    @ModelAttribute("alert")
    public Object getMsgObj() {
     
     return new Alert();
    
    }

    protected String notNull(String value) {
        return value == null ? "" : value;
    }
    
	protected Object getGlobalItemUnitOfMeasureList(int globalItemId) {
		// TODO Auto-generated method stub
		List<GlobalUnitofMeasure> list = this.inventoriesManagerBo
				.fetchGlobalItemUnitOfMeasure(globalItemId);
		// Map retMap = null;
		List retList = null;
		System.out.print(list);
		if (list != null) {
			// retMap=new HashMap();
			retList = new ArrayList();
			for (GlobalUnitofMeasure item : list) {
				// retMap.put(item.getId(), item.getUnit_of_measure());
				retList.add(new MenuItem(String.valueOf(item.getId()), item
						.getUnit_of_measure()));
			}

		}
		return retList;
	}
	
	protected void alert(boolean msg, String type, String message, ModelMap model) {
		model.addAttribute("msg", msg);
		model.addAttribute("type", type);
		model.addAttribute("message", message);
	}
	
	protected void modalAlert(boolean msg, String type, String message,
			ModelMap model) {
		model.addAttribute("modal_msg", msg);
		model.addAttribute("type", type);
		model.addAttribute("message", message);
	}
}
