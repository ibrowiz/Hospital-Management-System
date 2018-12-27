package org.calminfotech.utils.controllers;

import java.util.Date;

import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalUnitofMeasureBo;
import org.calminfotech.system.boInterface.UnitItemBo;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.system.models.UnitItem;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/utilities/itemandunit")
public class ItemandUnitController {
	
	@Autowired
	private UnitItemBo unitItemBo;
	
	@Autowired
	private GlobalUnitofMeasureBo unitBo;
	
	@Autowired
	private GlobalItemBo itemBo;
	
	@Autowired
	private UserIdentity userIdentity;

	@RequestMapping(value = "/assignment/{unitId}/{itemId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getitembytypeId(@PathVariable("unitId") Integer unitId, @PathVariable("itemId") Integer itemId) {
		GlobalUnitofMeasure unit = unitBo.getUnitofMeasureById(unitId);
		GlobalItem item = itemBo.getGlobalItemById(itemId);
		UnitItem val = unitItemBo.getByUnitIdAndItemId(unit.getId(), item.getId());
		if(val != null){
			return "Item Unit relationship already exist.";
		}
		UnitItem unitItem = new UnitItem();
		unitItem.setItemId(item.getId());
		unitItem.setUnitId(unit.getId());
		unitItem.setTypeId(item.getCategory().getItemTypeId().getGlobalitemTypeId());
		unitItem.setCreatedby(userIdentity.getUsername());
		unitItem.setCreateDate(new Date(System.currentTimeMillis()));
		unitItem.setDeleted(false);
		unitItemBo.save(unitItem);
		return unit.getUnit_of_measure()+" have been assiged to "+item.getName();
	}
}
