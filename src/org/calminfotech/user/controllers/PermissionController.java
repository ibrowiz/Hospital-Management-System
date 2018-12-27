package org.calminfotech.user.controllers;

import javax.servlet.http.HttpServletResponse;

import org.calminfotech.system.boInterface.GetRoleAssignmentProcBo;
import org.calminfotech.system.boInterface.GetUserAssignmentProcBo;
import org.calminfotech.system.boInterface.RoleAssgnmentBo;
import org.calminfotech.system.boInterface.UserAssgnmentBo;
import org.calminfotech.system.forms.GetRoleAssignmentProcForm;
import org.calminfotech.system.forms.GetUserAssignmentProcForm;
import org.calminfotech.system.models.RoleAssgnment;
import org.calminfotech.system.models.UserAssgnment;
import org.calminfotech.user.boInterface.PermissionBo;
import org.calminfotech.user.boInterface.RoleBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.Role;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
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
@RequestMapping(value = "/system/umgt/permission")
public class PermissionController {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Alert alert;

	@Autowired
	private GetRoleAssignmentProcBo roleAssignProcBo;

	@Autowired
	private GetUserAssignmentProcBo userAssignProcBo;

	@Autowired
	private RoleAssgnmentBo roleAssgned;

	@Autowired
	private UserAssgnmentBo userAssigned;// delete

	@Autowired
	private RoleBo roleBo;

	@Autowired
	private UserBo userBo;

	@Autowired
	private PermissionBo permBo;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String indexRole(Model model) {
		int roleId = 0;
		model.addAttribute("dRoleAssign",
				this.roleAssignProcBo.fetchRolePermission(roleId));
		model.addAttribute("rForm", new GetRoleAssignmentProcForm());
		model.addAttribute("roles", roleBo.fetchAllRoleByOrganisation());
		return "system/umgt/permission/role/index";
	}

	@RequestMapping(value = "/role/save", method = RequestMethod.POST)
	public String entryAction(
			@ModelAttribute("rForm") GetRoleAssignmentProcForm rForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		if (result.hasErrors()) {
			return "";
		}
		if (rForm.getSaveButton() == null) {
			return "redirect:/system/umgt/permission/role/request/"
					+ rForm.getpRole();
		}
		// delete all initial checked value
		this.roleAssgned.deleteAllCheckedValues(rForm.getpRole());
		// Save
		// fetch role by Id
		Role role = roleBo.getRoleById(rForm.getpRole());
		RoleAssgnment roleAssign = new RoleAssgnment();
		String[] checkboxes = rForm.getPermissionCheckboxVals();
		// iterate array list
		for (String checkbox : checkboxes) {
			roleAssign.setRoleId(role.getRoleId());
			roleAssign.setPermissionId(Integer.parseInt(checkbox));
			roleAssgned.save(roleAssign);
		}
		// redirect
		model.addAttribute("dRoleAssign",
				this.roleAssignProcBo.fetchRolePermission(rForm.getpRole()));
		model.addAttribute("rForm", new GetRoleAssignmentProcForm());
		model.addAttribute("roles", roleBo.fetchAllRoleByOrganisation());
		return "redirect:/system/umgt/permission/role/request/"
				+ rForm.getpRole();
	}

	@RequestMapping(value = "/role/request/{roleId}", method = RequestMethod.GET)
	public String request(@PathVariable("roleId") Integer Id, Model model,
			HttpServletResponse response) {
		GetRoleAssignmentProcForm rForm = new GetRoleAssignmentProcForm();
		rForm.setpRole(Id);
		model.addAttribute("dRoleAssign",
				this.roleAssignProcBo.fetchRolePermission(Id));
		model.addAttribute("rForm", rForm);
		model.addAttribute("roles", roleBo.fetchAllRoleByOrganisation());
		return "system/umgt/permission/role/index";
	}
	
	
	
	//users start from here
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String indexUser(Model model) {
		int userId = 0;
		model.addAttribute("dUserAssign",
				this.userAssignProcBo.fetchUserPermission(userId));
		model.addAttribute("uForm", new GetUserAssignmentProcForm());
		model.addAttribute("users", this.userBo.fetchAllByOrganisation());
		return "system/umgt/permission/user/index";
	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public String entryUserAction(
			@ModelAttribute("uForm") GetUserAssignmentProcForm uForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		if (result.hasErrors()) {
			return "";
		}
		if (uForm.getSaveButton() == null) {
			return "redirect:/system/umgt/permission/user/request/"
					+ uForm.getpUser();
		}
		// delete all initial checked value
		this.userAssigned.deleteAllUserCheckedValues(uForm.getpUser());
		// Save
		// fetch role by Id
		User user = this.userBo.getUserById(uForm.getpUser());
		UserAssgnment userAssign = new UserAssgnment();
		String[] checkboxes = uForm.getPermissionCheckboxVals();
		// iterate array list
		for (String checkbox : checkboxes) {
			userAssign.setUserId(user.getUserId());
			userAssign.setPermissionId(Integer.parseInt(checkbox));
			userAssigned.save(userAssign);
		}
		// redirect
		model.addAttribute("dUserAssign",
				this.userAssignProcBo.fetchUserPermission(uForm.getpUser()));
		model.addAttribute("uForm", new GetUserAssignmentProcForm());
		model.addAttribute("users", userBo.fetchAllByOrganisation());
		return "redirect:/system/umgt/permission/user/request/"
				+ uForm.getpUser();
	}
	
	@RequestMapping(value = "/user/request/{userId}", method = RequestMethod.GET)
	public String requestUser(@PathVariable("userId") Integer Id, Model model,
			HttpServletResponse response) {
		GetUserAssignmentProcForm uForm = new GetUserAssignmentProcForm();
		uForm.setpUser(Id);
		model.addAttribute("dUserAssign",this.userAssignProcBo.fetchUserPermission(Id));
		model.addAttribute("uForm", uForm);
		model.addAttribute("users", userBo.fetchAllByOrganisation());
		return "system/umgt/permission/user/index";
	}
}
