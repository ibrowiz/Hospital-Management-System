package org.calminfotech.lab.controllers;


import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.lab.bo.LabTestBo;
import org.calminfotech.lab.bo.LabTestCategoryBo;
import org.calminfotech.lab.forms.LabTestCategoryForm;
import org.calminfotech.lab.forms.LabTestForm;
import org.calminfotech.lab.models.LabTest;
import org.calminfotech.lab.models.LabTestCategory;
import org.calminfotech.lab.models.LabTestCategoryView;
import org.calminfotech.setup.forms.OrgForm;
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
@RequestMapping(value = "/system/labtestcat")
public class LabTestCategoryController {
	
	@Autowired
	private LabTestBo labTestBo;
	
	@Autowired
	private LabTestCategoryBo labTestCatBo;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private Auditor auditor;
	
	
	//@Layout("layouts/datatable")
	@RequestMapping(value = "/detail/{id}")
	public String viewDetail(@PathVariable("id") Integer id,Model model,
			RedirectAttributes redirectAttributes) {
		LabTestCategory labcat = this.labTestCatBo.getLabtestCatById(id);
		List<LabTest> labTest = this.labTestBo.getLabtestByCatId(id);
		System.out.println("labtest details " + labTest.size());
		model.addAttribute("list",  this.labTestCatBo.fetchAllCatByOrganisation(userIdentity.getOrganisation().getId()));
		LabTestForm labF = new LabTestForm();
		labF.setCatId(labcat.getId());
		model.addAttribute("lab", labcat);
		model.addAttribute("labTest", labTest);
		model.addAttribute("labTestForm", labF);
		model.addAttribute("List",  labTestBo.getLabtestById(id));
		return "lab/labtestcat/details";
	}
	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/list")
	public String viewAction(Model model) {
		OrgForm orgForm = new OrgForm();
		orgForm.setOrgId(userIdentity.getOrganisation().getId());
		model.addAttribute("orgform", orgForm);
		model.addAttribute("viewList",  labTestCatBo.fetchAllCatByOrg(userIdentity.getOrganisation().getId()));
		model.addAttribute("list",  labTestCatBo.fetchAllCatByOrganisation(userIdentity.getOrganisation().getId()));
		model.addAttribute("labTestCatForm", new LabTestCategoryForm());
		return "lab/labtestcat/list";
	}
	
	@RequestMapping(value = "/add")
	@Layout(value = "layouts/form_wizard_layout")
	public String addAction(Model model) {
		model.addAttribute("labTestCatForm", new LabTestCategoryForm());
		return "system/labtestcat/add";
	}
	
	@RequestMapping(value = "/save")
	public String addAction(
			@Valid @ModelAttribute("labTestCatForm") LabTestCategoryForm labTestCatForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		/*LabTestCategory labTest = new LabTestCategory();
		labTest.setParentId(labTestCatForm.getParentId());
		labTest.setCategoryName(labTestCatForm.getCategoryName());
		labTest.setDescription(labTestCatForm.getDescription());	
		labTest.setCreatedBy(userIdentity.getUsername());
		labTest.setCreatedDate(new GregorianCalendar().getTime());
		labTest.setModifiedBy(labTestCatForm.getModifiedBy());
		labTest.setLastModifiedDate(labTestCatForm.getLastModifiedDate());
		labTest.setStatus("active");
		labTest.setDeleted(false);*/
		labTestCatBo.save(labTestCatForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! "+labTestCatForm.getCategoryName()+" added Successfully!");
		return "redirect:/system/labtestcat/list";
	}
	
	
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/editcat/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		List<LabTestCategoryView> labTestlist = this.labTestCatBo.fetchAllCatByOrg(userIdentity.getOrganisation().getId());
		LabTestCategory labTest = this.labTestCatBo.getLabtestCatById(id);
		if (null == labTest) {

			return "redirect:/index";
		}

		LabTestCategoryForm labTestCatForm = new LabTestCategoryForm();

		labTestCatForm.setId(labTest.getId());
		labTestCatForm.setParentId(labTest.getParentId());
		labTestCatForm.setCategoryName(labTest.getCategoryName());
		labTestCatForm.setDescription(labTest.getDescription());
		/*labTestCatForm.setCreatedBy(labTest.getCreatedBy());
		labTestCatForm.setCreatedDate(labTest.getCreatedDate());
		labTestCatForm.setModifiedBy(labTest.getModifiedBy());
		labTestCatForm.setLastModifiedDate(labTest.getLastModifiedDate());
		labTestCatForm.setStatus(labTest.getStatus());*/
		
		
		List<LabTestCategoryView> labTestCatView = this.labTestCatBo.fetchAllCatByOrg(userIdentity.getOrganisation().getId());		
		
		model.addAttribute("labTestCatView", labTestCatView);

		model.addAttribute("labTestlist", labTestlist);
		model.addAttribute("labTestCatForm", labTestCatForm);
		
		auditor.before(request, "LabTEstCategory Form", labTestCatForm);

		return "lab/labtestcat/edit";

	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("labTestCatForm") LabTestCategoryForm labTestCatForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request)  {
	
		/*LabTestCategory labTestCat = new LabTestCategory();
		labTestCat.setId(labTestCatForm.getId());
		labTestCat.setCategoryName(labTestCatForm.getCategoryName());
		labTestCat.setDescription(labTestCatForm.getDescription());	
		labTestCat.setCreatedBy(labTestCatForm.getCreatedBy());
		labTestCat.setCreatedDate(labTestCatForm.getCreatedDate());
		labTestCat.setModifiedBy(userIdentity.getUsername());
		labTestCat.setLastModifiedDate(new GregorianCalendar().getTime());
		labTestCat.setStatus(labTestCatForm.getStatus());*/
		this.labTestCatBo.update(labTestCatForm);
		
		auditor.after(request, "LabTestCategory Form", labTestCatForm,
				userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! updated");
		 return "redirect:/system/labtestcat/list";
	}
	
	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		LabTestCategory lab = this.labTestCatBo.getLabtestCatById(id);
		model.addAttribute("lab", lab);
		return "system/labtestcat/view";
	}
	
	@RequestMapping(value = "/refreshlabtestcategory/{organisationId}", method = RequestMethod.GET,consumes="application/json")
	@ResponseBody
	public void refreshallerycategory(@PathVariable("organisationId") Integer organisationId,Model model)throws HibernateException, SQLException  {
		
		System.out.println("inrefresh");
		
		Session session = sessionFactory.openSession();
		  CallableStatement cs = null;
		  cs = session
		    .connection()
		    .prepareCall("{?= call lab_test_outerrecursive(?)}");

		  cs.registerOutParameter(1, Types.INTEGER);
		  cs.setInt(2, organisationId);
		  
		  
		  cs.execute();
		  System.out.println(cs.getInt(1));
		  
		  System.out.println("Done with the query");
		  
		;
	}

}
