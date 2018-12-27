package org.calminfotech.utils.controllers;

import java.util.List;

import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemType;

import org.calminfotech.utils.GlobalItemList;
import org.calminfotech.utils.GlobalItemTypeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/utilities/itemtype")
public class ItemTypeandItemController {
	
	
	@Autowired
	private GlobalItemTypeList itemTypeList; 
	
	@Autowired
	private GlobalItemList itemList; 
	
	
	@RequestMapping(value = "/itemtype", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getitemtype() {
		//don't understand the logic yet, check later 
		String ItmtypeStr = "<option value='0'>Select Item Type</option>";
		List<GlobalItemType> itemtyList = itemTypeList.fetchAll();
		for (GlobalItemType IT : itemtyList) {
			ItmtypeStr += "<option value='" + IT.getGlobalitemTypeId() + "'>"
					+ IT.getName() + "</option>";
		}
		return ItmtypeStr;
	}
	
	
	@RequestMapping(value = "/itemdistribution", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getitemdistribution() {
		String ItemStr = "<option value='0'>Select Items</option>";
		List<GlobalItem> list = itemList.fetchAll();
		for (GlobalItem ID : list) {
			ItemStr += "<option value='" + ID.getId()
					+ "'>" + ID.getName() + "</option>";
		}
		return ItemStr;
	}
	
	
	@RequestMapping(value = "/itemtype/id/{id}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getitemByTYPE(@PathVariable("id") Integer id) {
		String ItemStr = "<option value='0'>Select Item</option>";

		GlobalItemType ITP = itemTypeList.getItemTypeById(id);

		if (ITP == null)
			return ItemStr;
		//don't understand the logic yet, check later 
		//Set<GlobalItem> list = ITP.getItem();
		/*Set<GlobalItem> list = ITP.getCategoryItem().
		for (GlobalItem item : list) {
			ItemStr += "<option value='" + item.getId()
					+ "'>" + item.getName() + "</option>";
		}*/
		return ItemStr;
	}
	
	
	@RequestMapping(value = "/itembytype/{id}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getitembytypeId(@PathVariable("id") Integer id) {
		String ItemStr = "<option value='0'>Select Item</option>";

	
		GlobalItemType ITP = itemTypeList.getItemTypeById(id);
		
		
		if (ITP == null)
			return ItemStr;
		//don't understand the logic yet, check later 
		/*Set<GlobalItem> list = ITP.getItem();

		for (GlobalItem ITD : list) {
			ItemStr += "<option value='" + ITD.getId()
					+ "'>" + ITD.getName() + "</option>";
		}*/
		return ItemStr;
	}
}
