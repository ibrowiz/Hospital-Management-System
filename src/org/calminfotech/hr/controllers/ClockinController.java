package org.calminfotech.hr.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.calminfotech.hr.boInterface.ClockinBoInterface;
import org.calminfotech.hr.boInterface.GetClockingUnitProcBoInterface;
import org.calminfotech.hr.forms.GetClockingUnitProcForm;
import org.calminfotech.hr.models.Clockin;
import org.calminfotech.hr.models.GetClockingUnitProc;
import org.calminfotech.hr.models.GetClockingUnitProc2;
import org.calminfotech.setup.boInterface.UnitCategoryBo;
import org.calminfotech.system.boInterface.ClockingBo;
import org.calminfotech.system.boInterface.GetRoleAssignmentProcBo;
import org.calminfotech.system.boInterface.GetUserAssignmentProcBo;
import org.calminfotech.system.boInterface.RoleAssgnmentBo;
import org.calminfotech.system.boInterface.UserAssgnmentBo;
import org.calminfotech.system.forms.GetRoleAssignmentProcForm;
import org.calminfotech.system.forms.GetUserAssignmentProcForm;
import org.calminfotech.system.models.Clocking;
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
@RequestMapping(value = "/clocking")
public class ClockinController {
	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Alert alert;
	
	@Autowired
	private ClockinBoInterface clockinBo;

	@Autowired
	private GetClockingUnitProcBoInterface getClockingUnitProcBo;

	/*@Autowired
	private GetUserAssignmentProcBo userAssignProcBo;

	@Autowired
	private RoleAssgnmentBo roleAssgned;

	@Autowired
	private UserAssgnmentBo userAssigned;// delete

	@Autowired
	private RoleBo roleBo;
*/
	@Autowired
	private ClockingBo clockingBo;
	@Autowired
	private UserBo userBo;

	@Autowired
	private UnitCategoryBo unitCatBo;

	
	//users start from here
	@RequestMapping(value = {" ","/"}, method = RequestMethod.GET)
	public String indexUser(Model model) {
		System.out.println("start clocking");
		//int userId = 46;
		List<GetClockingUnitProc> clockinProc =  this.getClockingUnitProcBo.fetchClockinUnit(userIdentity.getUserId());
		model.addAttribute("unitClockin",clockinProc);
		//System.out.println("clocking size is" + clockinProc.size());
		model.addAttribute("clockinForm", new GetClockingUnitProcForm());
		//model.addAttribute("users", this.userBo.fetchAllByOrganisation());
		return "hr/index";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String entryUserAction(
			@ModelAttribute("clockinForm") GetClockingUnitProcForm clockinForm,
			BindingResult result, RedirectAttributes redirectAttributes,HttpServletRequest request,
			Model model) {
		if (result.hasErrors()) {
			return "";
		}
		if (clockinForm.getSaveButton() == null) {
			return "redirect:/request/"
					+ userIdentity.getUserId();
		}
		// delete all initial checked value
		
		
		
		this.clockinBo.deleteAllCheckedValues(userIdentity.getUserId());
	
		//return "error/errorPage";
		
		// Save
		// fetch role by Id
		//User user = this.userBo.getUserById(userIdentity.getUserId());
		Clocking clocking = new Clocking();
		Clockin clockin = new Clockin();
		Integer hours = clockinForm.getHours();
		if(hours==null){
			hours = 0;
		}
		Integer minutes = clockinForm.getMinutes();
		if(minutes==null){
			minutes = 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, hours);
		cal.add(Calendar.MINUTE, minutes);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		System.out.println(dateFormat.format(cal.getTime()));
		String[] checkboxes = clockinForm.getUnitCheckboxVals();
		// iterate array list
		String desc;
		String submit = request.getParameter("saveButton");
		if("clockin".equals(submit)){
			desc="ClockIn";
		}
		else
		{
			desc="ClockOut";
		}
		System.out.println("calminfo1");
		System.out.println("lengthis" + checkboxes.length);
			if(checkboxes.length>0){
				System.out.println("lengthis" + checkboxes.length);
		for (String checkbox : checkboxes) {
			clockin.setUserId(userIdentity.getUserId());
			clockin.setClockInTime(new Date(System.currentTimeMillis()));
			clockin.setUserName(userIdentity.getUsername());
			clockin.setOrganisationId(userIdentity.getOrganisation().getId());
			clockin.setUnitId(Integer.parseInt(checkbox));
			clockin.setExpClockOutTime((cal.getTime()));
			//userAssign.set(Integer.parseInt(checkbox));
			clockinBo.save(clockin);
			clocking.setUsername(userIdentity.getUsername());
			clocking.setClockTime(new Date(System.currentTimeMillis()));
			clocking.setClockingType(desc);
			clocking.setClockingUnit(Integer.parseInt(checkbox));
			clocking.setOrganisationId(userIdentity.getOrganisation().getId());
			clocking.setUserId(userIdentity.getUserId());
			this.clockingBo.save(clocking);
			}	
			}
			/*if button is clin descrtiin=inj
					hfkkou
			boto save arord(useIdi,username,drescryi,,gfh,ghjk)*/
		// redirect
	
		model.addAttribute("unitClockin",this.getClockingUnitProcBo.fetchClockinUnit(userIdentity.getUserId()));
		model.addAttribute("clockinForm", new GetClockingUnitProcForm());
		//model.addAttribute("users", userBo.fetchAllByOrganisation());
		return "redirect:/clocking/request/"
				+ userIdentity.getUserId();
	}
	

	@RequestMapping(value = "/request/{userId}", method = RequestMethod.GET)
	public String requestUser(@PathVariable("userId") Integer Id, Model model,
			HttpServletResponse response) {
		GetClockingUnitProcForm clockinForm = new GetClockingUnitProcForm();
		//uForm.setpUser(Id);
		model.addAttribute("unitClockin",this.getClockingUnitProcBo.fetchClockinUnit(Id));
		model.addAttribute("clockinForm", clockinForm);
		//model.addAttribute("users", userBo.fetchAllByOrganisation());
		return "hr/index";
	}

}
