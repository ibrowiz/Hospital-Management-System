package org.calminfotech.setup.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.setup.boInterface.HrUnitViewBo;
import org.calminfotech.setup.boInterface.UnitBo;
import org.calminfotech.setup.boInterface.UnitCategoryBo;
import org.calminfotech.setup.boInterface.UnitCategoryViewBo;
import org.calminfotech.setup.boInterface.UnitListBo;
import org.calminfotech.setup.forms.OrgForm;
import org.calminfotech.setup.forms.UnitCategoryForm;
import org.calminfotech.setup.forms.UnitForm;
import org.calminfotech.setup.models.HrUnit;
import org.calminfotech.setup.models.HrUnitCategory;
import org.calminfotech.setup.models.HrUnitView;
import org.calminfotech.setup.models.UnitCategoryView;
import org.calminfotech.setup.models.UnitList;
import org.calminfotech.system.boInterface.BillingSchemeBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.models.BillingScheme;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.annotations.Layout;
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
@RequestMapping(value = "/system/unit")
public class UnitController {

	@Autowired
	private BillingSchemeBo billingSchemeBo;
	
	@Autowired
	private VisitWorkflowPointBo visitWorkflowPointBo;

	@Autowired
	private UnitCategoryViewBo unitCategoryViewBo;

	@Autowired
	private UnitCategoryBo unitCategoryBo;
	
	@Autowired
	private UnitBo unitBo;
	
	@Autowired
	private UnitListBo unitListBo;
	
	@Autowired
	private HrUnitViewBo hrUnitViewBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;

	@Layout("layouts/datatable")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listAll(Model model) {
		List<HrUnitView> hrUnitView = this.hrUnitViewBo
				.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		model.addAttribute("hrUnitView", hrUnitView);
		OrgForm orgForm = new OrgForm();
		orgForm.setOrgId(userIdentity.getOrganisation().getId());
		model.addAttribute("orgform", orgForm);
		return "system/unit/index";
	}

	@Layout("layouts/form_wizard_layout")
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String add(Model model) {
		// model.addAttribute("globalItemType",
		// globalItemTypeBo.fetchAllByOrganisation());

		List<HrUnitCategory> unitCategory = this.unitCategoryBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		
		List<UnitList> unitList = this.unitListBo.fetchAllByOrganisationId(userIdentity.getOrganisation().getId());
		
		List<BillingScheme> billingScheme = this.billingSchemeBo
				.fetchAllByType();
		List<VisitWorkflowPoint> visitwfp = this.visitWorkflowPointBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		model.addAttribute("visitpoint", visitwfp);
		model.addAttribute("unitList", unitList);
		model.addAttribute("unitForm", new UnitForm());
		System.out.println("billinglist " + billingScheme);
		model.addAttribute("billingScheme", billingScheme);
		return "system/unit/add";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("unitForm") UnitForm unitForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		/*
		 * if (result.hasErrors()) { System.out.println("Errors here!!!");
		 * return "system/unit/add"; }
		 */

		HrUnit unit = unitBo.save(unitForm);
		UnitCategoryView unitCategoryView = this.unitCategoryViewBo.getUnitCategoryViewById(unit.getUnitId());

		alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! "
				+ unitCategoryView.getName() + " added Successfully!");
		return "redirect:/system/unit/list";
	}
	@Layout("layouts/form_wizard_layout")
	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		List<BillingScheme> billingScheme = this.billingSchemeBo
				.fetchAllByOrganisation();
		List<HrUnitCategory> unitCategory = this.unitCategoryBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());

		/*List<UnitCategoryView> unitCategoryView = this.unitCategoryViewBo
				.fetchAllByOrganisation(userIdentity.getOrganisation().getId());*/
		List<VisitWorkflowPoint> visitWfp = this.visitWorkflowPointBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());

		HrUnit hrUnit = unitBo.getUnitById(id);
		System.out.println("point ID is" + hrUnit.getPoint().getId());
		/*if (null == hrUnit) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
		}*/
		UnitForm unitForm = new UnitForm();
		unitForm.setPointId(hrUnit.getPoint().getId());
		unitForm.setUnitId(id);
		unitForm.setUnitCategoryId(hrUnit.getUnitId());
		unitForm.setAttendQ(hrUnit.isAttendQ());
		unitForm.setBillingScheme(hrUnit.getBillingSchemeId());
		unitForm.setPointId(id);
		// allergyForm.setCategory(allergy.getCategory());
		model.addAttribute("visitpoint", visitWfp);
		model.addAttribute("billingScheme", billingScheme);
		model.addAttribute("hrUnit", hrUnit);
		model.addAttribute("unitForm", unitForm);
		model.addAttribute("unitCategory", unitCategory);

		auditor.before(request, "Unit Form", unitForm);

		return "system/unit/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("unitForm") UnitForm unitForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		/*
		 * if (result.hasErrors()) { model.addAttribute("categories",
		 * this.unitCategoryBo
		 * .fetchAllByOrganisation(userIdentity.getUserId())); return
		 * "admin/medical/components/allergies/edit"; }
		 */
		/*if (unitCategoryForm.getParentId() == (unitCategoryForm
				.getUnitCategoryId())
				|| (unitCategoryForm.getUnitCategoryId()) == unitCategoryForm
						.getParentId()) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Parent and Name cannot be same");
			return "redirect:/system/unit/category/list";

		} else {*/
			unitBo.update(unitForm);

			auditor.after(request, "Unit Form", unitForm,
					userIdentity.getUsername());
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Unit updated");

		//}
		return "redirect:/system/unit/list/";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteCategoryAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {
		HrUnitView unitView = this.hrUnitViewBo.getUnitViewById(id);
		HrUnit hrunit = this.unitBo.getUnitById(id);
		/*
		 * if (null == unitCategory) { this.alert.setAlert(redirectAttributes,
		 * Alert.ERROR, "Invalid resource"); return
		 * "redirect:/admin/staffregistration/unitcategory/list/"; }
		 */

		UnitForm unitForm = new UnitForm();
		unitForm.setUnitId(id);
		//unitCategoryForm.setName(unitCategory.getName());

		model.addAttribute("unitForm", unitForm);
		model.addAttribute("hrunit", hrunit);
		model.addAttribute("unitView", unitView);
		return "system/unit/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String removeCategoryAction(
			@ModelAttribute("unitForm") UnitForm unitForm,
			RedirectAttributes redirectAttributes) {

		HrUnit hrUnit = this.unitBo.getUnitById(unitForm.getUnitId());

		if (null == hrUnit) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/system/unit/list";
		}

		this.unitBo.delete(hrUnit);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Deleted!");

		return "redirect:/system/unit/list";
	}

}
