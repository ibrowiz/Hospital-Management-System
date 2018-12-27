package org.calminfotech.system.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.hr.forms.GetClockingUnitProcForm;
import org.calminfotech.system.boInterface.ClockBo;
import org.calminfotech.system.boInterface.ClockOutBo;
import org.calminfotech.system.boInterface.ClockingBo;
import org.calminfotech.system.boInterface.HrUserDepartmentBo;
import org.calminfotech.system.boInterface.HrUserPointBo;
import org.calminfotech.system.boInterface.HrUserSectionBo;
import org.calminfotech.system.boInterface.HrUserUnitBo;
import org.calminfotech.system.forms.ClockForm;
import org.calminfotech.system.forms.ClockOutForm;
import org.calminfotech.system.forms.ClockingForm;
import org.calminfotech.system.forms.DepartmentForm;
import org.calminfotech.system.forms.HrUserSectionForm;
import org.calminfotech.system.models.Clock;
import org.calminfotech.system.models.ClockOut;
import org.calminfotech.system.models.Clocking;
import org.calminfotech.system.models.GetPointDetails;
import org.calminfotech.system.models.GetUnitDetails;
import org.calminfotech.system.models.HrUserDepartment;
import org.calminfotech.system.models.HrUserPoint;
import org.calminfotech.system.models.HrUserSection;
import org.calminfotech.system.models.HrUserUnit;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.boInterface.UserClockOutBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserClockOut;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.LoginSectionPointList;
import org.calminfotech.utils.SectionList;
import org.calminfotech.utils.annotations.Layout;
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
@RequestMapping(value = "/user/clocking")
public class ClockingController {

	private static final Date NULL = null;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private ClockingBo clockingBo;

	@Autowired
	private ClockOutBo clocOutBo;
	
	@Autowired
	private ClockBo clockBo;

	@Autowired
	private SectionList sectionList;

	@Autowired
	private LoginSectionPointList loginSectionPointList;

	@Autowired
	private UserBo userBo;
	
	@Autowired
	private HrUserSectionBo hrUserSectionBo;
	
	@Autowired
	private HrUserDepartmentBo hrUserDepartmentBo;
	
	@Autowired
	private HrUserUnitBo hrUserUnitBo;
	
	@Autowired
	private HrUserPointBo hrUserPointBo;

	@Autowired
	private UserClockOutBo userClockOutBo;
	
	@RequestMapping(value = "/clockingform")
	@Layout("layouts/datatable")
	public String indexAct(@Valid @ModelAttribute("clockForm") ClockForm clockForm,
			BindingResult result, Model model) {
		//List<HrUserSection> hrUserSec = this.hrUserSectionBo.getHrUserSectionByUserId(userIdentity.getUserId());
		List<HrUserDepartment> hrUserdept = this.hrUserDepartmentBo.getHrUserDepartmentByUserId(userIdentity.getUserId());
		//List<GetUnitDetails> hrUserUnit = this.hrUserUnitBo.getHrUserUnitForDep(userIdentity.getDepartment().getId(), userIdentity.getUserId());
	//	List<HrUserPoint> hrUserPoint = this.hrUserPointBo.getHrUserPointByUserId(userIdentity.getUserId());
		//model.addAttribute("hrUserSec", hrUserSec);
		model.addAttribute("hrUserdept", hrUserdept);
		//model.addAttribute("hrUserUnit", hrUserUnit);
		//model.addAttribute("hrUserPoint", hrUserPoint);
		return "user/clocking/ibclocking";
	}

	@RequestMapping(value = "/request/unit/{departmentId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getUnitByDepartment(@PathVariable("departmentId") Integer Id) {
		String pointStr = "<option value='0'>Select unit</option>";
		//HrUserDepartment department = this.hrUserDepartmentBo.getHrUserDepartmentById(Id);
		System.out.println("This is the ajax call id is :" +Id);
		//Set<HrUserUnit> units = hrUserUnitBo.getHrUserUnitForDep(Id, userId)
		List<GetUnitDetails> units = hrUserUnitBo.getHrUserUnitForDep(Id, userIdentity.getUserId());
		if (units == null) {
			return pointStr;
		}
		
		for (GetUnitDetails depts : units) {
			pointStr += "<option value='" + depts.getUnit_Id() + "'>"
					+ depts.getUnit_name() + "</option>";
		}
		return pointStr;
	}
	
	
	@RequestMapping(value = "/request/point/{unitId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getPointByUnit(@PathVariable("unitId") Integer Id) {
		String pointStr = "<option value='0'>Select Point</option>";
		//HrUserDepartment department = this.hrUserDepartmentBo.getHrUserDepartmentById(Id);
		System.out.println("This is the ajax call id is :" +Id);
		//Set<HrUserUnit> units = hrUserUnitBo.getHrUserUnitForDep(Id, userId)
		List<GetPointDetails> points = this.hrUserPointBo.getHrUserPointForUnit(Id, userIdentity.getUserId());
		if (points == null) {
			return pointStr;
		}
		
		for (GetPointDetails units : points) {
			pointStr += "<option value='" + units.getPoint_id() + "'>"
					+ units.getPoint_name() + "</option>";
		}
		return pointStr;
	}
	
	
	@RequestMapping(value = "/save")
	public String addAction(
			@Valid @ModelAttribute("clockForm") ClockForm clockForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

Clock clock = new Clock();
clock.setSectionId(clockForm.getSectionId());
clock.setDepartmentId(clockForm.getDepartmentId());
clock.setUnitId(clockForm.getUnitId());
clock.setPointId(clockForm.getPointId());
clock.setUserId(userIdentity.getUserId());
clock.setClockTime(new GregorianCalendar().getTime());
clock.setClockStatus("clockedin");

		
		this.clockBo.save(clock);

		return null;
	}
	
	@RequestMapping(value = "/user")
	@Layout("layouts/datatable")
	public String indexAction(Model model) {

		model.addAttribute("usertable", this.userBo.fetchAllByOrganisation());

		return "user/clocking/user";
	}

	@RequestMapping(value = "/add/{id}")
	@Layout(value = "layouts/form_wizard_layout")
	public String viewAction(@PathVariable("id") Integer userId, Model model,
			RedirectAttributes redirectAttributes) {

		User user = this.userBo.getUserById(userId);
		if (null == user) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/user/clocking/user";
		}

		ClockingForm clockingForm = new ClockingForm();
		clockingForm.setUserloginId(user.getUserId());
		clockingForm.setUsername(user.getUsername());

		model.addAttribute("clockingForm", clockingForm);
		model.addAttribute("section", this.sectionList.fetchAll());
		model.addAttribute("sectionpoint",
				this.loginSectionPointList.fetchAll());
		/* model.addAttribute("clockingForm", new ClockingForm()); */

		return "user/clocking/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String savemedhs(
			 Model model,
			RedirectAttributes redirectAttributes) throws IOException {

		

		
		
		GetClockingUnitProcForm getClockingUnitProcForm = new GetClockingUnitProcForm();
		Clocking clocking = new Clocking();
		clocking.setUserId(userIdentity.getUserId());

		
		clocking.setUsername(userIdentity.getUsername());
		clocking.setClockTime(new Date(System.currentTimeMillis()));
		clocking.setClockingType("in");
		//clocking.setClockingUnit(getClockingUnitProcForm.getUnitCheckboxVals());
		clocking.setOrganisationId(userIdentity.getOrganisation().getId());
		
		
	//this.clockingBo.save(clocking);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! You have successfully clocked in");

		return "redirect:/user/clocking/user/";
	}

	// to bring the clock out confirmation form
	/*@RequestMapping(value = "/clockout/{id}")
	public String ClockoutAction(@PathVariable("id") Integer userId,
			Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		User user = this.userBo.getUserById(userId);

		if (null == user) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/user/clocking/user";
		}

		ClockOutForm clockOutForm = new ClockOutForm();
		clockOutForm.setUserloginId(user.getUserId());
		clockOutForm.setUsername(user.getUsername());
		clockOutForm.setLoginSectionId(user.getLoginSection().getId());
		clockOutForm
				.setLoginSectionPointId(user.getLoginSectionPoint().getId());

		model.addAttribute("clockoutform", clockOutForm);

		model.addAttribute("section", this.sectionList.fetchAll());
		model.addAttribute("sectionpoint",
				this.loginSectionPointList.fetchAll());

		return "user/clocking/clockout";
	}
*/
	@RequestMapping(value = "/clockout/{id}", method = RequestMethod.POST)
	public String saveclockout(
			@Valid @ModelAttribute("clockoutform") ClockOutForm clockOutForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws IOException {

		// User user = this.userBo.getUserById(clockOutForm.getId());
		UserClockOut user = this.userClockOutBo
				.getUserClockOutById(clockOutForm.getId());

		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "user/clocking/add";
		}

		if (null == user) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not clock you out. Invalid resource");
			return "redirect:/user/clocking/user";
		}

		ClockOut clockOut = new ClockOut();
		clockOut.setUser(user);

		// this is to also save into the user clock out model
		user.setClock_out_time(new Date(System.currentTimeMillis()));
		user.setClocking_status(ClockOut.pclocking_status.Clockout.toString());
		user.setClock_status(false);

		// save the clock out info into the clocking table
		clockOut.setUsername(clockOutForm.getUsername());
		clockOut.setClock_out_time(new Date(System.currentTimeMillis()));
		clockOut.setClocking_status(ClockOut.pclocking_status.Clockout
				.toString());

		clockOut.setLoginSection(this.sectionList
				.getLoginSectionById(clockOutForm.getLoginSectionId()));
		clockOut.setLoginSectionPoint(this.loginSectionPointList
				.getLoginSectionPointById(clockOutForm.getLoginSectionPointId()));
		clockOut.setOrganisation(user.getOrganisation());
		user.getClockout().add(clockOut);
		this.clocOutBo.save(clockOut);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! You have successfully clocked out");

		return "redirect:/user/clocking/user/";
	}

}
