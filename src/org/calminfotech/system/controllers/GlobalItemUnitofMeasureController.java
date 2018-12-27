package org.calminfotech.system.controllers;

import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemCategoryBo;
import org.calminfotech.system.boInterface.GlobalUnitofMeasureBo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/system/category/{cId}/globalitem/{Id}/unit")
public class GlobalItemUnitofMeasureController {
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private Auditor auditor;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private GlobalUnitofMeasureBo unitBo;
	
	@Autowired
	private GlobalItemBo itemBo;
	
	@Autowired
	private GlobalItemCategoryBo gCategoryBo;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String view(@PathVariable("cId") Integer cId, @PathVariable("iId") Integer id, Model model){			
		model.addAttribute("item", itemBo.getGlobalItemById(id));
		model.addAttribute("gCategory", gCategoryBo.getGlobalCategoryItemById(cId));
		return "system/category/globalitem/view";		
	}
	
	/*// Unit of Measurement inside Category Item
	@RequestMapping(value = "/unit/{id}")
	public String medHistoryAction(@PathVariable("id") Integer id,
								   Model model,  RedirectAttributes redirectAttributes) {

		CategoryItem categoryItem = itemBo.getCategoryItemById(id);
			
			if (null == categoryItem) {
				alert.setAlert(redirectAttributes, Alert.ERROR,
						"Error! Invalid resource");
				return "redirect:/system/categoryitem";
			}	
			
		CategoryItemUnitForm itemUnitForm = new CategoryItemUnitForm();		
		itemUnitForm.setCategoryItem(categoryItem.getItemId());		
		
		model.addAttribute("itemUnitForm", itemUnitForm);
		model.addAttribute("unitlist", unitBo.fetchAll());
		model.addAttribute("categoryItem", categoryItem);

			return "system/categoryitem/itemunit";
	}
	
	
	@RequestMapping(value = "/unit/{id}", method = RequestMethod.POST)
	public String saveUnitAction(
			@Valid @ModelAttribute("itemUnitForm") CategoryItemUnitForm itemUnitForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws IOException {
		
		CategoryItem categoryItem = itemBo.getCategoryItemById(itemUnitForm.getCategoryItem());
		
		if (result.hasErrors()) {
			model.addAttribute("categoryItem", categoryItem);
			return "system/categoryitem/itemunit";
		}

		if (null == categoryItem) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not save history. Invalid resource");
			return "redirect:/system/categoryitem";
		}

		CategoryItemUnit itemUnit = new CategoryItemUnit();
		
		itemUnit.setCategoryItem(categoryItem);
		itemUnit.setUnit(unitBo.getUnitofMeasureById(itemUnitForm.getItemUnitId()));
		categoryItem.getCategoryItemUnit().add(itemUnit);
		
		itemUnitBo.save(itemUnit);		

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! saved successfully");

		return "redirect:/system/categoryitem/unit/" + categoryItem.getItemId();
		
	}

	
	@RequestMapping(value = "/unit/delete/{id}")
	public String unitDeleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		CategoryItemUnit itemUnit = itemUnitBo.getCategoryItemUnitById(id);
		
		if (null == itemUnit) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/categoryitem";
		}

		CategoryItem categoryItem = itemBo.getCategoryItemById(id);	

		CategoryItemUnitForm itemUnitForm = new CategoryItemUnitForm();
		itemUnitForm.setItemUnitId(itemUnit.getItemUnitId());
				

		model.addAttribute("categoryItem", categoryItem);
		model.addAttribute("itemUnitForm", itemUnitForm);
		model.addAttribute("itemUnit", itemUnit);

		return "system/categoryitem/deleteunit";
	}

	@RequestMapping(value = "/unit/delete/{id}", method = RequestMethod.POST)
	public String confirmUnitDeleteAction(
			@ModelAttribute("itemUnitForm") CategoryItemUnitForm itemUnitForm,
			RedirectAttributes redirectAttributes) {

		CategoryItemUnit itemUnit = itemUnitBo.getCategoryItemUnitById(itemUnitForm.getItemUnitId());		

		if (null == itemUnit) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/categoryitem";
		}
		
		int categoryItemId = itemUnit.getCategoryItem().getItemId();
		itemUnitBo.delete(itemUnit);		
		
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Category Item Unit deleted");

		return "redirect:/system/categoryitem/unit/" + categoryItemId;
	}

	// Visits Work Flow Point inside Category Item
	@RequestMapping(value = "/point/{id}")
	public String workflowAction(@PathVariable("id") Integer id,
								 Model model,  
								 RedirectAttributes redirectAttributes) {

			CategoryItem categoryItem = itemBo.getCategoryItemById(id);
				
				if (null == categoryItem) {
					alert.setAlert(redirectAttributes, Alert.ERROR,
							"Error! Invalid resource");
					return "redirect:/system/categoryitem";
				}	
				
			CategoryItemPointForm itemPointForm = new CategoryItemPointForm();		
			itemPointForm.setCategoryItem(categoryItem.getItemId());		
			
			model.addAttribute("itemPointForm", itemPointForm);
			model.addAttribute("workFlowList", workFlowPointBo.fetchAllByOrganisation());
			model.addAttribute("categoryItem", categoryItem);

				return "system/categoryitem/itempoint";
		}
	
	@RequestMapping(value = "/point/{id}", method = RequestMethod.POST)
	public String savePointAction(
			@Valid @ModelAttribute("itemPointForm") CategoryItemPointForm itemPointForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws IOException {
		
		CategoryItem categoryItem = itemBo.getCategoryItemById(itemPointForm.getCategoryItem());
		
		if (result.hasErrors()) {
			model.addAttribute("categoryItem", categoryItem);
			return "system/categoryitem/itempoint";
		}

		if (null == categoryItem) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not save history. Invalid resource");
			return "redirect:/system/categoryitem";
		}
		//check this code pics kunle
		CategoryItemPoint itemPoint = new CategoryItemPoint();
		
		//itemPoint.setCategoryItem(categoryItem);
		itemPoint.setFlowPoint(workFlowPointBo.getWorkflowPointById(itemPointForm.getFlowPointId()));		
		categoryItem.getCategoryItemPoint().add(itemPoint);		
		itemPointBo.save(itemPoint);	

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! saved successfully");

		return "redirect:/system/categoryitem/point/" + categoryItem.getItemId();
		
	}
	
	

	@RequestMapping(value = "/point/delete/{id}")
	public String pointDeleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		CategoryItemPoint itemPoint = itemPointBo.getCategoryItemPointById(id);
		
		if (null == itemPoint) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/categoryitem";
		}

		CategoryItem categoryItem = itemBo.getCategoryItemById(id);	

		
		CategoryItemPointForm itemPointForm = new CategoryItemPointForm();
		itemPointForm.setItemPointId(itemPoint.getItemPointId());				

		model.addAttribute("categoryItem", categoryItem);
		model.addAttribute("itemPointForm", itemPointForm);
		model.addAttribute("itemPoint", itemPoint);

		return "system/categoryitem/deletepoint";
	}

	@RequestMapping(value = "/point/delete/{id}", method = RequestMethod.POST)
	public String confirmPointDeleteAction(
			@ModelAttribute("itemPointForm") CategoryItemPointForm itemPointForm,
			RedirectAttributes redirectAttributes) {

		CategoryItemPoint itemPoint = itemPointBo.getCategoryItemPointById(itemPointForm.getItemPointId());		

		if (null == itemPoint) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/categoryitem";
		}
		//check kunle
int categoryItemId = itemPoint.getGlobalCategoryItem().getItemId();
		itemPointBo.delete(itemPoint);		
		
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Category Item Point deleted");
		//check this kunle
		return "redirect:/system/categoryitem/point/" + categoryItemId;
		return "";
	}
	*/
}
