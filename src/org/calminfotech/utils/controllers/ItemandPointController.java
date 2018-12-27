package org.calminfotech.utils.controllers;

import java.util.Date;

import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemPointBo;
import org.calminfotech.system.boInterface.GlobalUnitofMeasureBo;
import org.calminfotech.system.boInterface.UnitItemBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemPoint;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.system.models.UnitItem;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/utilities/itemandpoint")
public class ItemandPointController {
	
	@Autowired
	private GlobalItemPointBo globalItemPointBo;
	
	@Autowired
	private VisitWorkflowPointBo pointBo;
	
	@Autowired
	private GlobalItemBo itemBo;
	
	@Autowired
	private UserIdentity userIdentity;

	@RequestMapping(value = "/assignment/{pointId}/{itemId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getitembytypeId(@PathVariable("pointId") Integer pointId, @PathVariable("itemId") Integer itemId) {
		//VisitWorkflowPoint point = pointBo.getWorkflowPointById(pointId);
		GlobalItem item = itemBo.getGlobalItemById(itemId);
		GlobalItemPoint gPt = globalItemPointBo.getByPointAndItem(itemId, pointId);
		if(gPt != null){
			return "Item Point relationship already exist.";
		}
		GlobalItemPoint pointItem = new GlobalItemPoint();
		pointItem.setItemId(itemId);
		//pointItem.setPoint(point);
		pointItem.setCreatedBy(userIdentity.getUsername());
		pointItem.setCreatedDate(new Date(System.currentTimeMillis()));
		pointItem.setIsDeleted(false);
		pointItem.setOrganisation(userIdentity.getOrganisation());
		globalItemPointBo.save(pointItem);
		return "have been assiged to "+item.getName();
	}
}
