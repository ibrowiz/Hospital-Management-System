package org.calminfotech.consultation.controller;

import org.calminfotech.consultation.bo.VitalQueueBo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/consultations/visits/queue")
public class VitalQueueController {
	
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private VitalQueueBo vitalQueueBo;

	@Autowired
	private Alert alert;
	
	
	@RequestMapping(value = { "", "/" })
	@Layout("layouts/datatable")
	public String indexAction(Model model) {

		model.addAttribute("vitalqueue",
				this.vitalQueueBo.fetchAllByUser());

		return "consultations/visits/queue/index";

}
}