package org.calminfotech.system.controllers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.BGlobalItemBo;
import org.calminfotech.system.boInterface.BCategoryListBo;
import org.calminfotech.system.boInterface.BGetOuterrecursiveBo;
import org.calminfotech.system.boInterface.BGlobalCategoryBo;
import org.calminfotech.system.boInterface.GlobalItemCategoryBo;
import org.calminfotech.system.boInterface.GlobalItemTypeBo;
import org.calminfotech.system.boInterface.GlobalUnitofMeasureBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.forms.BCategoryItemForm;
import org.calminfotech.system.forms.BGlobalCategoryForm;
import org.calminfotech.system.forms.GlobalItemAssignmentForm;
import org.calminfotech.system.forms.GlobalItemForm;
import org.calminfotech.system.models.BGlobalItem;
import org.calminfotech.system.models.BGlobalCategory;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.OuterRecursive;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.annotations.Layout;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
@RequestMapping(value = "/system/bitem")
public class BItemController {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private BGlobalCategoryBo globalCategoryBo;

	@Autowired
	private BGetOuterrecursiveBo bGetOuterrecursiveBo;

	@Autowired
	private BCategoryListBo categoryListBo;

	@Autowired
	private BGlobalItemBo categoryItemBo;

	@Autowired
	private GlobalItemCategoryBo globalItemCategoryBo;

	@Autowired
	private GlobalItemTypeBo globalItemTypeBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private BGlobalItemBo itemBo;

	@Autowired
	private BGlobalCategoryBo categoryBo;

	@Autowired
	private GlobalUnitofMeasureBo unitBo;

	@Autowired
	private VisitWorkflowPointBo pointBo;

	
	
	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String index(Model model) {
		
		System.out.println("ORGANISATION::::" +userIdentity.getOrganisation().getId());
		
		
		this.refreshCategoryList();
		
		model.addAttribute("categories", this.categoryListBo.fetchAll());

		model.addAttribute("iForm", new BCategoryItemForm());

		
		
		model.addAttribute("items", categoryItemBo.fetchAllByOgranisation(userIdentity.getOrganisation().getId()));
		return "system/category/globalitem/details";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addItem(Model model, BCategoryItemForm iForm)
			throws HibernateException, SQLException {




		this.refreshCategoryList();
		
		model.addAttribute("categories", this.categoryListBo.fetchAll());

		model.addAttribute("iForm", iForm);

		return "system/category/globalitem/additem";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("iForm") BCategoryItemForm iForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		System.out.println("Inside Add Controller");
		System.out.println("Inside POST Controller");

		BGlobalCategory category = globalCategoryBo.getCategoryById(iForm
				.getParentCatgoryId());

		BGlobalItem item = new BGlobalItem();

		item.setItemName(iForm.getItemName());
		item.setDescription(iForm.getDescription());
		item.setGlobalCategory(category);
		item.setCreatedBy(userIdentity.getUsername());
		item.setCreateDate(new GregorianCalendar().getTime());
		item.setModifiedBy(userIdentity.getUsername());
		item.setModifyDate(new GregorianCalendar().getTime());
		item.setOrganisationId(userIdentity.getOrganisation().getId());

		categoryItemBo.save(item);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! Global Item Added Successfully! ");
		return "redirect:/system/bitem/add";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletRequest httpRequest) {


		BGlobalItem item = this.categoryItemBo.getCategoryItemById(id);

		if (item == null) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/bitem";
		}

		BCategoryItemForm iForm = new BCategoryItemForm();

		iForm.setItemId(item.getItemId());
		iForm.setDescription(item.getDescription());
		iForm.setItemName(item.getItemName());

		model.addAttribute("categories", categoryItemBo.fetchAll());
		model.addAttribute("globalItemType",
				globalItemTypeBo.fetchAllByOrganisation());

		model.addAttribute("iForm", iForm);
		model.addAttribute("item", item);
		// auditor
		auditor.before(httpRequest, "item", iForm);

		return "system/category/globalitem/edititem";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("iForm") BCategoryItemForm iForm,
			BindingResult result, Model model, HttpServletRequest httpRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		BGlobalItem item = this.categoryItemBo.getCategoryItemById(iForm
				.getItemId());
		this.categoryItemBo.update(item);

		auditor.after(httpRequest, "HMO", iForm, userIdentity.getUsername());
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + iForm.getItemName() + " edited Successfully!");
		return "redirect:/system/bitem/index";
	}
	
	
	@RequestMapping(value = "/view/{id}")
	@Layout("layouts/datatable")
	public String view(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		BGlobalItem item = this.categoryItemBo.getCategoryItemById(id);
		
		if (null == item) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/bitem/index";
				}
		
		model.addAttribute("item", itemBo.getCategoryItemById(id));
		model.addAttribute("gCategory", categoryBo.getCategoryById(item.getGlobalCategory().getCategoryId()));
		model.addAttribute("unit", unitBo.fetchAll());
		model.addAttribute("aForm", new GlobalItemAssignmentForm());
		model.addAttribute("point", pointBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		model.addAttribute("unitList", unitBo.fetchAll());

		model.addAttribute("item", item);
		
		return "system/category/globalitem/viewitem";
	}

	
	public void refreshCategoryList(){
		
		
		System.out.println("Before query");

			
		
		Session session = sessionFactory.openSession();
		try{
		CallableStatement cs = null;
		cs = session.connection().prepareCall("{?= call outerrecursiveproc}");

		cs.registerOutParameter(1, Types.INTEGER);

		cs.execute();
		System.out.println(cs.getInt(1));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}

		System.out.println("Done with the query");
	}

}
