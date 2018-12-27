package org.calminfotech.system.controllers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.BGlobalItemBo;
import org.calminfotech.system.boInterface.BCategoryListBo;
import org.calminfotech.system.boInterface.BGlobalCategoryBo;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemCategoryBo;
import org.calminfotech.system.boInterface.GlobalUnitofMeasureBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.forms.BCategoryItemForm;
import org.calminfotech.system.forms.GlobalItemAssignmentForm;
import org.calminfotech.system.forms.GlobalItemForm;
import org.calminfotech.system.models.BGlobalItem;
import org.calminfotech.system.models.BGlobalCategory;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
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
@RequestMapping(value = "/system/bcategory/{cId}/globalitem")
public class BGlobalItemController {

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

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private BCategoryListBo categoryListBo;

	@RequestMapping(value = "/view/{iId}", method = RequestMethod.GET)
	public String view(@PathVariable("iId") Integer id,
			@PathVariable("cId") Integer cId, Model model) {

		model.addAttribute("item", itemBo.getCategoryItemById(id));
		model.addAttribute("gCategory", categoryBo.getCategoryById(cId));
		model.addAttribute("unit", unitBo.fetchAll());
		model.addAttribute("aForm", new GlobalItemAssignmentForm());
		model.addAttribute("point", pointBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		model.addAttribute("unitList", unitBo.fetchAll());
		return "system/category/globalitem/viewitem";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@PathVariable("cId") Integer cId, Model model,
			BCategoryItemForm iForm) throws HibernateException, SQLException {

		System.out.println("Before query");

		Session session = sessionFactory.openSession();
		CallableStatement cs = null;
		cs = session.connection().prepareCall("{?= call outerrecursiveproc}");

		cs.registerOutParameter(1, Types.INTEGER);

		cs.execute();
		System.out.println(cs.getInt(1));

		System.out.println("Done with the query");

		model.addAttribute("categories", this.categoryListBo.fetchAll());

		BGlobalItem item = new BGlobalItem();

		iForm.setItemName(item.getItemName());
		iForm.setDescription(item.getDescription());
		iForm.setParentCatgoryId(cId);
		model.addAttribute("iForm", iForm);

		return "system/category/globalitem/additem";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAction(
			@Valid @ModelAttribute("iForm") BCategoryItemForm iForm,
			@PathVariable("cId") Integer cId, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "redirect:/system/category/view/" + cId;
		}

		BGlobalCategory category = categoryBo
				.getCategoryById(iForm.getItemId());

		if (null == category) {
			System.out.println("Theris no caterory with the given ID :  "
					+ iForm.getItemId());

			return "redirect:/system/category/view/" + cId;

		}

		BGlobalItem item = new BGlobalItem();

		item.setItemName(iForm.getItemName());
		item.setDescription(iForm.getDescription());
		item.setGlobalCategory(category);

		itemBo.save(item);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! Global Item Added Successfully! ");
		return "redirect:/system/bcategory/view/" + cId;
	}

	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id") int id,
			@PathVariable("cId") Integer cId, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		BGlobalItem item = itemBo.getCategoryItemById(id);
		if (null == item) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/category/view/" + cId;
		}

		BCategoryItemForm iForm = new BCategoryItemForm();

		iForm.setItemId(item.getItemId());
		iForm.setItemName(item.getItemName());
		iForm.setDescription(item.getDescription());
		iForm.setParentCatgoryId(cId);

		model.addAttribute("item", item);
		model.addAttribute("iForm", iForm);
		auditor.before(request, "Global Item Form", iForm);
		return "system/category/globalitem/edititem";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(
			@Valid @ModelAttribute("iForm") BCategoryItemForm iForm,
			BindingResult result, Model model,
			@PathVariable("cId") Integer cId,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletRequest httpRequest) {

		if (result.hasErrors()) {
			alert.setAlert(redirectAttributes, "error", "Error occured!. "
					+ iForm.getItemName() + " failed to update");
			return "redirect:/system/category/view/" + iForm.getItemId();
		}
		BGlobalItem item = this.itemBo.getCategoryItemById(cId);

		BGlobalCategory globalCategory = this.categoryBo.getCategoryById(iForm
				.getParentCatgoryId());

		item.setItemName(iForm.getItemName());

		item.setDescription(iForm.getDescription());

		item.setGlobalCategory(globalCategory);

		item.setModifiedBy(userIdentity.getUsername());

		item.setModifyDate(new GregorianCalendar().getTime());

		itemBo.update(item);
		// auditor
		auditor.after(httpRequest, "BCategoryItemForm", iForm,
				userIdentity.getUsername());
		alert.setAlert(redirectAttributes, "success", iForm.getItemName() + " "
				+ " item have been updated successfully!!!");
		return "redirect:/system/bcategory/" + cId + "/globalitem/view/"
				+ item.getItemId();
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		BGlobalItem item = itemBo.getCategoryItemById(id);
		if (null == item) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/category/view/" + id;
		}
		BCategoryItemForm iForm = new BCategoryItemForm();
		iForm.setItemId(item.getItemId());
		model.addAttribute("iForm", iForm);
		model.addAttribute("item", item);
		return "system/category/globalitem/deleteitem";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("iForm") BCategoryItemForm iForm,
			@PathVariable("cId") Integer cId,
			RedirectAttributes redirectAttributes) {

		BGlobalItem item = itemBo.getCategoryItemById(iForm.getItemId());
		if (null == item) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/categoryitem";
		}
		itemBo.delete(item);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Category Item profile deleted");
		return "redirect:/system/bcategory/view/" + cId;
	}

}
