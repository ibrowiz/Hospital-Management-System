package org.calminfotech.setup.controller;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.setup.boInterface.UnitCategoryBo;
import org.calminfotech.setup.boInterface.UnitCategoryViewBo;
import org.calminfotech.setup.forms.AllergyCategoryForm;
import org.calminfotech.setup.forms.OrgForm;
import org.calminfotech.setup.forms.UnitCategoryForm;
import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.setup.models.AllergyCategoryView;
import org.calminfotech.setup.models.HrUnitCategory;
import org.calminfotech.setup.models.UnitCategoryView;
import org.calminfotech.system.boInterface.BillingSchemeBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.models.BillingScheme;
import org.calminfotech.system.models.VisitWorkflowPoint;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/system/unit/category")
public class UnitCategoryController {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private BillingSchemeBo billingSchemeBo;

	@Autowired
	private UnitCategoryViewBo unitCategoryViewBo;

	@Autowired
	private UnitCategoryBo unitCategoryBo;
	
	@Autowired
	private VisitWorkflowPointBo visitWorkflowPointBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;

	@Layout("layouts/datatable")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listAll(Model model) {
		List<UnitCategoryView> unitCategoryView = this.unitCategoryViewBo
				.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		OrgForm orgForm = new OrgForm();
		orgForm.setOrgId(userIdentity.getOrganisation().getId());
		model.addAttribute("orgform", orgForm);
		model.addAttribute("unitCategory", unitCategoryView);
		return "system/unitcategory/index";
	}

	@Layout("layouts/form_wizard_layout")
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String add(Model model) {
		// model.addAttribute("globalItemType",
		// globalItemTypeBo.fetchAllByOrganisation());

		List<HrUnitCategory> unitCategory = this.unitCategoryBo
				.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		/*List<BillingScheme> billingScheme = this.billingSchemeBo
				.fetchAllByType();*/
		model.addAttribute("unitCategory", unitCategory);
		model.addAttribute("unitCategoryForm", new UnitCategoryForm());
		return "system/unitcategory/add";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("unitCategoryForm") UnitCategoryForm unitCategoryForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		/*
		 * if (result.hasErrors()) { System.out.println("Errors here!!!");
		 * return "system/unit/add"; }
		 */

		HrUnitCategory unitCategory = unitCategoryBo.save(unitCategoryForm);

		alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! "
				+ unitCategory.getName() + " added Successfully!");
		return "redirect:/system/unit/category/list";
	}
	@Layout("layouts/form_wizard_layout")
	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		List<BillingScheme> billingScheme = this.billingSchemeBo
				.fetchAllByOrganisation();
		List<UnitCategoryView> unitCategoryView = this.unitCategoryViewBo
				.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		HrUnitCategory unitCategory = unitCategoryBo.getCategoryById(id);
		if (null == unitCategory) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
		}
		UnitCategoryForm unitCategoryForm = new UnitCategoryForm();
		unitCategoryForm.setUnitCategoryId(id);
		unitCategoryForm.setParentId(unitCategory.getParentId());
		unitCategoryForm.setName(unitCategory.getName());
		// allergyForm.setCategory(allergy.getCategory());
		model.addAttribute("billingScheme", billingScheme);
		model.addAttribute("unitCategory", unitCategory);
		model.addAttribute("unitCategoryForm", unitCategoryForm);
		model.addAttribute("unitCategoryView", unitCategoryView);

		auditor.before(request, "UnitCategory Form", unitCategoryForm);

		return "system/unitcategory/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("unitCategoryForm") UnitCategoryForm unitCategoryForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		/*
		 * if (result.hasErrors()) { model.addAttribute("categories",
		 * this.unitCategoryBo
		 * .fetchAllByOrganisation(userIdentity.getUserId())); return
		 * "admin/medical/components/allergies/edit"; }
		 */
		if (unitCategoryForm.getParentId() == (unitCategoryForm
				.getUnitCategoryId())
				|| (unitCategoryForm.getUnitCategoryId()) == unitCategoryForm
						.getParentId()) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Parent and Name cannot be same");
			return "redirect:/system/unit/category/list";

		} else {
			unitCategoryBo.update(unitCategoryForm);

			auditor.after(request, "UnitCategory Form", unitCategoryForm,
					userIdentity.getUsername());
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Allergy updated");

		}
		return "redirect:/system/unit/category/list/";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteCategoryAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		HrUnitCategory unitCategory = this.unitCategoryBo.getCategoryById(id);
		/*
		 * if (null == unitCategory) { this.alert.setAlert(redirectAttributes,
		 * Alert.ERROR, "Invalid resource"); return
		 * "redirect:/admin/staffregistration/unitcategory/list/"; }
		 */

		UnitCategoryForm unitCategoryForm = new UnitCategoryForm();
		unitCategoryForm.setUnitCategoryId(unitCategory.getUnitCategoryId());
		unitCategoryForm.setName(unitCategory.getName());

		model.addAttribute("unitCategoryForm", unitCategoryForm);
		model.addAttribute("unitCategory", unitCategory);

		return "system/unitcategory/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String removeCategoryAction(
			@ModelAttribute("unitCategoryForm") UnitCategoryForm unitCategoryForm,
			RedirectAttributes redirectAttributes) {

		HrUnitCategory unitCategory = this.unitCategoryBo
				.getCategoryById(unitCategoryForm.getUnitCategoryId());

		if (null == unitCategory) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/system/unit/category/list";
		}

		this.unitCategoryBo.delete(unitCategory);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Category Deleted!");

		return "redirect:/system/unit/category/list";
	}
	
	@RequestMapping(value = "/refreshAllUnits/{organisationId}", method = RequestMethod.GET)
	@ResponseBody
	public String refreshallUnits(@PathVariable("organisationId") Integer organisationId,Model model)throws HibernateException, SQLException  {
		
		System.out.println("inrefresh");
		
		Session session = sessionFactory.openSession();
		  CallableStatement cs = null;
		  cs = session
		    .connection()
		    .prepareCall("{?= call unit_outerrecursiveproc(?)}");

		  cs.registerOutParameter(1, Types.INTEGER);
		  cs.setInt(2, organisationId);
		  
		  
		  
		  cs.execute();
		  System.out.println(cs.getInt(1));
		  
		  System.out.println("Done with the query");
		  
		;
		return "ok";
		
		
		
	}

}
