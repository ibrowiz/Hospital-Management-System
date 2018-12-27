package org.calminfotech.system.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemCategoryBo;
import org.calminfotech.system.boInterface.GlobalUnitofMeasureBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.forms.GlobalItemAssignmentForm;
import org.calminfotech.system.forms.GlobalItemForm;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/system/category/{cId}/globalitem")
public class GlobalItemController {
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private Auditor auditor;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private GlobalItemBo itemBo;
	
	@Autowired
	private GlobalItemCategoryBo gCategoryBo;
	
	@Autowired
	private GlobalUnitofMeasureBo unitBo;

	@Autowired
	private VisitWorkflowPointBo pointBo;
	
	@RequestMapping(value = "/view/{iId}", method = RequestMethod.GET)
	public String view(@PathVariable("cId") Integer cId, @PathVariable("iId") Integer id, Model model){
		
		model.addAttribute("item", itemBo.getGlobalItemById(id));
		model.addAttribute("gCategory", gCategoryBo.getGlobalCategoryItemById(cId));
		model.addAttribute("unit", unitBo.fetchAll());
		model.addAttribute("aForm", new GlobalItemAssignmentForm());
		model.addAttribute("point", pointBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		model.addAttribute("unitList", unitBo.fetchAll());
		return "system/category/globalitem/view";		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@PathVariable("cId") Integer cId,Model model, GlobalItemForm iForm){		
				
		GlobalItem item = new GlobalItem();
		
		iForm.setItem_name(item.getName());
		iForm.setDescription(item.getDescription());
		iForm.setCategory(cId);
		model.addAttribute("iForm", iForm);		
		
		return "system/category/globalitem/add";		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAction(@Valid @ModelAttribute("iForm") GlobalItemForm iForm,
							@PathVariable("cId")Integer cId,BindingResult result, Model model,
							RedirectAttributes redirectAttributes){		
		if (result.hasErrors()) {			
			return "redirect:/system/category/view/"+cId;
		}
		itemBo.save(iForm);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! Global Item Added Successfully! ");
		return "redirect:/system/category/view/"+cId;
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id") int id,@PathVariable("cId")Integer cId, Model model,
					   RedirectAttributes redirectAttributes, 
					   HttpServletRequest request){
		
		GlobalItem item = itemBo.getGlobalItemById(id);
		if(null == item){
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/category/view/"+cId;
		}
		
		GlobalItemForm iForm = new GlobalItemForm();
		
		iForm.setId(item.getId());
		iForm.setItem_name(item.getName());
		iForm.setDescription(item.getDescription());
		iForm.setCategory(item.getCategory().getCategoryId());
		
		model.addAttribute("item", item);
		model.addAttribute("iForm", iForm);
		auditor.before(request, "Global Item Form", iForm);
		return "system/category/globalitem/edit";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("iForm") GlobalItemForm iForm,
						BindingResult result, Model model,@PathVariable("cId") Integer cId,
						RedirectAttributes redirectAttributes, HttpServletRequest request
						, HttpServletRequest httpRequest){
		
		if (result.hasErrors()) {
			alert.setAlert(redirectAttributes, "error", "Error occured!. "
					+ iForm.getItem_name() + " failed to update");
			return "redirect:/system/category/view/"+iForm.getId();
		}
		itemBo.update(iForm);
		// auditor
		auditor.after(httpRequest, "GlobalItemForm", iForm, userIdentity.getUsername());
		alert.setAlert(redirectAttributes, "success", iForm.getItem_name() + " "
				+ " item have been updated successfully!!!");
		return "redirect:/system/category/"+cId+"/globalitem/view/"+iForm.getId();
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		GlobalItem item = itemBo.getGlobalItemById(id);
		if (null == item) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/category/view/"+id;
		}
		GlobalItemForm iForm = new GlobalItemForm();
		iForm.setId(item.getId());
		model.addAttribute("iForm", iForm );
		model.addAttribute("item", item );
		return "system/category/globalitem/delete";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("iForm") GlobalItemForm iForm,@PathVariable("cId") Integer cId,
			RedirectAttributes redirectAttributes) {
		GlobalItem item = itemBo.getGlobalItemById(iForm.getId());
		if (null == item) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/categoryitem";
		}
		itemBo.delete(item);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Category Item profile deleted");
		return "redirect:/system/category/view/"+cId;
	}
}
