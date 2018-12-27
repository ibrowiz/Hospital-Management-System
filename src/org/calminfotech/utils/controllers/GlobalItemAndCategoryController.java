package org.calminfotech.utils.controllers;

import java.util.List;
import java.util.Set;

import org.calminfotech.system.boInterface.BillingSchemeItemPriceDetailsBo;
import org.calminfotech.system.boInterface.CategoryBo;
import org.calminfotech.system.boInterface.CategoryTypeBo;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemCategoryBo;
import org.calminfotech.system.boInterface.GlobalItemTypeBo;
import org.calminfotech.system.models.BillingSchemeItemPriceDetails;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemCategory;
import org.calminfotech.system.models.CategoryTypeXX;
import org.calminfotech.system.models.GlobalItemType;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.views.boInterface.VwItemBo;
import org.calminfotech.views.boInterface.VwUnitBo;
import org.calminfotech.views.models.VwItem;
import org.calminfotech.views.models.VwUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/utilities/globalitemandglobalitemcategory")
public class GlobalItemAndCategoryController {

	@Autowired
	private GlobalItemTypeBo globalItemTypeBo; 
	
	@Autowired
	private VwItemBo vwitemBo;
	
	@Autowired
	private VwUnitBo vwunitBo;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private GlobalItemBo globalItemBo;
	
	@Autowired
	private GlobalItemCategoryBo globalItemCategoryBo;
	
	@Autowired
	private BillingSchemeItemPriceDetailsBo billingSchemePriceDetailBo;
	/**
	 * fetch item_type by globalItemType.item_type
	 * **/
	@ResponseBody
	@RequestMapping(value = "/itemType/{id}")
	public Integer fetchItem(@PathVariable("id") Integer id){
		//get Item by Id
		VwItem item = this.vwitemBo.getVwItemById(id);
		//get ItemType by item
		GlobalItemType globalItemType = this.globalItemTypeBo.getGlobalItemTypeById(item.getType());
		return globalItemType.getGlobalitemTypeId();
	}
	
	/**
	 * fetch unit by item
	 * **/
	@ResponseBody
	@RequestMapping(value = "/itemunit/{itemId}")
	public String fetchItemUnit(@PathVariable("itemId") Integer itemId){
		String strUnit = "<option>Select .... </option>";
		//get Item by Id  
		GlobalItem globalItem = this.globalItemBo.getGlobalItemById(itemId);
		if(globalItem == null ){
			return strUnit;
		}
		//get unitofmeasure by item
		List<VwUnit> unitList = vwunitBo.fetchAllByItemId(globalItem.getId());
		for(VwUnit unit : unitList ){
			strUnit +="<option value="+unit.getId()+">"+unit.getDescription()+"</option>";
		}
		return strUnit;
	}

	@RequestMapping(value = "/globalitem", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getGlobalItem() {
		String globalItemStr = "<option value='0'>Select Global Item</option>";
		List<GlobalItem> globalItemList = globalItemBo.fetchAll();
		for (GlobalItem globalItem : globalItemList) {
			globalItemStr += "<option value='" + globalItem.getId() + "'>"
					+ globalItem.getName() + "</option>";
		}
		return globalItemStr;
	}

	@RequestMapping(value = "/category", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getCategory() {
		String categoryStr = "<option value='0'>Select Category</option>";
		List<GlobalItemCategory> list = globalItemCategoryBo.fetchAll();
		for (GlobalItemCategory globalItemCategory : list) {
			categoryStr += "<option value='" + globalItemCategory.getCategoryId()
					+ "'>" + globalItemCategory.getName() + "</option>";
		}
		return categoryStr;
	}

	/*@RequestMapping(value = "/globalitem/id/{globalitemId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getCategorysByGlobalItemId(@PathVariable("globalitemId") Integer id) {
		String categoryStr = "<option value='0'>Select Category</option>";
		//get item
		GlobalItem globalItem = globalItemBo.getGlobalItemById(id);
		if (globalItem == null)
			return categoryStr;
		//Since Globalitem -->> GlobalItemCategory -->> GlobalItemType
		Bad programming, yet to figure out the real intent
		GlobalItemCategory globalItemCategory = globalItemCategoryBo.getGlobalCategoryItemById(globalItem.getCategory().getCategoryId());
		GlobalItemType globalItemType = this.globalItemTypeBo.getGlobalItemTypeById(globalItemCategory.getItemTypeId().getGlobalitemTypeId());
		List<GlobalItemCategory> list = globalItemCategoryBo.fetchGlobalItemCategoryByItemTypeId(globalItemType);

		for (GlobalItemCategory category : list) {
			categoryStr += "<option value='" + category.getCategoryId()
					+ "'>" + category.getName() + "</option>";
		}
		return categoryStr;
	}*/
	
	
	
	/*@RequestMapping(value = "/categorybyglobalitem/{globalitemId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getCategorybyGlobalitem(@PathVariable("globalitemId") Integer globalItemId) {
		
		String categoryStr = "<option value='0'>Select Categoty</option>";

		//CategoryTypeXX catType = globalItemTypeBo.getCategoryTypeById(globalItemId);

		if (catType == null)
			return categoryStr;
		
		Set<GlobalItemCategory> list = catType.getCategory();

		for (GlobalItemCategory category : list) {
			categoryStr += "<option value='" + category.getCategoryId()
					+ "'>" + category.getName() + "</option>";
		}
		return categoryStr;
	}*/
	/**
	 * fetct price by billingId, ItemId and unitofmeasureId
	 * **/
	@ResponseBody
	@RequestMapping(value = "/price/{item}/{uom}/{bill}")
	public String fetchPrice(@PathVariable("item") Integer item, @PathVariable("uom") Integer uom,
			@PathVariable("bill") Integer bill){
		//Integer billingId = this.userIdentity.getBillId();
		System.out.println("uom "+uom);
		System.out.println("bill "+ bill);
		System.out.println("item "+item);
		System.out.println("=======////============////////====");
		String strPrice = "";
		List<BillingSchemeItemPriceDetails> billing =  this.billingSchemePriceDetailBo.getBillingInvoice(bill,uom,item);
		if(billing == null){
			return strPrice = "0:00";
		}
		for(BillingSchemeItemPriceDetails result : billing){
			strPrice = result.getPrice();
		}
		return strPrice;
	}
}
