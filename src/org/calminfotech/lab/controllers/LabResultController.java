package org.calminfotech.lab.controllers;

import java.io.IOException;
import java.sql.Blob;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.calminfotech.consultation.bo.VisitBo;
import org.calminfotech.lab.forms.GetLaboratoryTestProcForm;
import org.calminfotech.lab.forms.LabTestDocumentForm;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.lab.bo.GetLaboratoryTestProcBo;
import org.calminfotech.lab.bo.LabDeleteResultBo;
import org.calminfotech.lab.bo.LabResultBo;
import org.calminfotech.lab.bo.LabResultSetupBo;
import org.calminfotech.lab.bo.LabTestBo;
import org.calminfotech.lab.bo.LabTestDocumentBo;
import org.calminfotech.lab.models.LabResult;
import org.calminfotech.lab.models.LabResultSetup;
import org.calminfotech.lab.models.LabTest;
import org.calminfotech.lab.models.LabTestDocument;
import org.calminfotech.patient.boInterface.PatientBo;


import org.calminfotech.lab.models.GetLaboratoryTestProc;

import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.annotations.Layout;
import org.hibernate.Hibernate;
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
@RequestMapping(value = "/system/laboratoryResult")
public class LabResultController {
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private VisitBo visitBo;
	
	@Autowired
	private LabTestBo labTestBo;
	
	@Autowired
	private LabResultBo labResultBo;
	
	@Autowired
	private PatientBo patientBo;
	
	@Autowired
	private LabTestDocumentBo labTestDocBo;
	
	@Autowired
	private LabResultSetupBo labResultSetupBo;
	
	@Autowired
	private GetLaboratoryTestProcBo labTestProcBo;
	
	@Autowired
	private LabDeleteResultBo deleteResultBo ;
	
	@Autowired
	private UserIdentity userIdentity;
	
	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/enterresult/{id}/{vid}")
	public String enterLabResult(@PathVariable("id") Integer id, @PathVariable("vid") Integer vid,Model model,
			RedirectAttributes redirectAttributes){
		
		
		
		List<GetLaboratoryTestProc> labTestProc = labTestProcBo.fetchResult(id);
		LabResultSetup labResultSetup = this.labResultSetupBo.getLabRSetupByTestId(1);
		System.out.println("result name "+labResultSetup.getResultName());
		System.out.println("max "+labResultSetup.getMaximumValue());
		System.out.println("min "+labResultSetup.getMinimumValue());
		GetLaboratoryTestProcForm lForm = new GetLaboratoryTestProcForm();
		
		lForm.setTestId(id);
		lForm.setVisit_id(vid);
		lForm.setFinal_result(labResultSetup.getResultName());
		lForm.setMaximum_value(labResultSetup.getMaximumValue());
		lForm.setMinimum_value(labResultSetup.getMinimumValue());
		
		
		
		LabTest test = this.labTestBo.getLabtestById(id);
		
		//List<LaboratoryResultSetup> labResultSetup = this.labResultSetupBo.getLabResultSetupById(id);
		LabTestDocumentForm ldForm = new LabTestDocumentForm();
		
		ldForm.setTestId(id);
		ldForm.setVisitId(vid);
		
		
		
		model.addAttribute("lForm", lForm);
		
		model.addAttribute("Labtest", labTestBo.fetchAllByOrganisationId(userIdentity.getOrganisation().getId()));
		
		Visit g =this.visitBo.getVisitationById(id);
		
		 model.addAttribute("queue", g);
		 
		 model.addAttribute("documentForm",ldForm);
		 
		 model.addAttribute("lab", test);
		 
	//	 model.addAttribute("labResultSetup",labResultSetup);
		 
		 model.addAttribute("labtestpro",labTestProc);
		 
		 model.addAttribute("vid",vid);
		 
		 return "consultations/visits/enterresult";
	}
	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/saveresult")
	public String saveLabResult(@ModelAttribute("lForm") GetLaboratoryTestProcForm lForm,
			Model model,
			RedirectAttributes redirectAttributes){
	// delete all initial  value
		
		
		
		
		System.out.println("Deleting");
		System.out.print(lForm.getTestId());	
	
	this.deleteResultBo.deleteResult(lForm.getTestId());
	System.out.println("Deleted");
	String[] actual_values = lForm.getActual_value();
	String[] setupIds = lForm.getSetupId();
	String[] finalResults = lForm.getFinalResult();
	HashMap<String,String> hash = new HashMap();

	
	// iterate array list
	int ct=1;
	for (String actual : actual_values) {
		hash.put("act" + ct ,actual);
		ct++;
	}
	
	//System.out.println("actual_values££");
	System.out.println(actual_values.length);
	
	int ct2=1;
	for (String setup : setupIds) {
		hash.put("setup" + ct2 ,setup.toString());
	ct2++;	
	}
	
	System.out.println("setupIds£££");
	
	System.out.println(setupIds.length);
	
	int ct3=1;
	for (String finalr : finalResults) {
		hash.put("finalr" + ct3 ,finalr);
	ct3++;	
	}
	
	
	
	
	
	//saving here
	for (int i = 1; i <=  actual_values.length; i++)
	
	{
	
		LabResult result = new LabResult();
		
		String setupid= hash.get("setup"+ i);
		
		System.out.println("The SetupId"+ setupid);
		
		String act=hash.get("act"+ i);
		
		String finalresult=hash.get("finalr"+ i);
		
		  //if act is null set it to "";
		if(act.equals(null)){
			act = "";
		}
		
		System.out.println("The act"+ act);
		

			
		LabTest labtest = this.labTestBo.getLabtestById(lForm.getTestId());
	
		int myint =Integer.parseInt(setupid);
		
		System.out.println("VALUE"+ myint);
		
		LabResultSetup labtestsetup = this.labResultSetupBo.getLabResultSetupById(myint);
		System.out.println("MAXVALUE"+ labtestsetup.getMaximumValue());
		System.out.println("MINUE"+ labtestsetup.getMinimumValue());
		System.out.println("RESULTNMAE"+ labtestsetup.getResultName());
		
	
		result.setLabTest(labtest);
		result.setLabRSetup(labtestsetup);
		
		result.setActual_value(act);
		result.setFinalResult(finalresult);
		//result.setCreated_by(userIdentity);
		result.setVisit_id(lForm.getVisit_id());
		
		labResultBo.save(result);
		System.out.println("saved item");
	}

	
	
	
	return "redirect:/system/laboratoryResult/enterresult/"+ lForm.getTestId()+"/"+lForm.getVisit_id();
	//return "redirect:/system";
	}
	
	
	@RequestMapping(value = "/savedocument")
	public String saveLabDocument(
			@Valid @ModelAttribute("documentForm") LabTestDocumentForm labTestDocumentForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		LabTest labTest = this.labTestBo.getLabtestById(labTestDocumentForm.getTestId());
		//Visit visit = this.visitBo.getVisitationById(labTestDocumentForm.getVisitId());

		if (result.hasErrors()) {
			model.addAttribute("labTest", labTest);
			return "consultations/visits/enterresult";
		}

		if (null == labTest) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not upload document. Invalid resource");
			return "redirect:/system/laboratoryResult";
		}

		LabTestDocument labTestDocument = new LabTestDocument();
		try {
			//@SuppressWarnings("deprecation")
			Blob blob = Hibernate.getLobCreator(sessionFactory.getCurrentSession()).createBlob(labTestDocumentForm.getDocument()
					.getInputStream(), 0);
			labTestDocument.setFile(blob);

			String contentType = labTestDocumentForm.getDocument()
					.getContentType();

			labTestDocument.setContentType(contentType);
			labTestDocument.setName(labTestDocumentForm.getDocument()
					.getOriginalFilename());
			labTestDocument.setLabTest(labTest);
			labTestDocument.setOrganisationId(userIdentity.getOrganisation().getId());
			labTestDocument.setCreatedDate(new GregorianCalendar().getTime());
			
			labTestDocument.setCreatedBy(userIdentity.getUserId());
			labTestDocument.setModifiedBy(labTestDocumentForm.getModifiedBy());
			labTestDocument.setModifiedDate(labTestDocumentForm.getModifiedDate());
			labTest.getLabTestDoc().add(labTestDocument);

			// Used for saving
			this.labTestDocBo.save(labTestDocument);

			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Document Uploaded successfully");

		} catch (IOException e) {
			e.printStackTrace();
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Document Upload failed");
		}

		return "redirect:/system/laboratoryResult/enterresult/" + labTestDocumentForm.getTestId() + "/" + labTestDocumentForm.getVisitId();
	}
	
	@ResponseBody
	@RequestMapping(value = "/labdocuments/view/{documentId}/{documentName}")
	public String viewLabDocumentAction(@PathVariable Integer documentId,
			@PathVariable("documentName") String documentName,
			RedirectAttributes redirectAttributes, HttpServletResponse response) {
		//LaboratoryTestDocument labTestDocument = new LaboratoryTestDocument();
		LabTestDocument labTestDocument = this.labTestDocBo.getLabDocumentById(documentId);

		if (null == labTestDocument) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/laboratoryResult";
		}

		try {
			response.setContentType(labTestDocument.getContentType());

			response.setHeader("Content-Disposition", "inline;filename=\""
					+ labTestDocument.getName() + "\"");

			OutputStream outputStream = response.getOutputStream();

			IOUtils.copy(labTestDocument.getFile().getBinaryStream(),
					outputStream);

			outputStream.flush();
			outputStream.close();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value = "/labdocuments/delete/{id}")
	public String LabdocumentDeleteAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		LabTestDocument labTestDocument = this.labTestDocBo.getLabDocumentById(id);

		if (null == labTestDocument) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/laboratoryResult";
		}

		LabTestDocumentForm labTestDocumentForm = new LabTestDocumentForm();
		labTestDocumentForm.setId(labTestDocument.getId());

		model.addAttribute("document", labTestDocument);
		model.addAttribute("lForm",labTestDocumentForm);

		return "consultations/visits/laboratory/delete_document";
	}

	@RequestMapping(value = "/labdocuments/delete/{id}", method = RequestMethod.POST)
	public String confirmLabDocumentDeleteAction(
			@ModelAttribute("lForm") LabTestDocumentForm labTestDocumentForm,
			RedirectAttributes redirectAttributes) {
		
		LabTestDocument labTestDocument = this.labTestDocBo.getLabDocumentById(labTestDocumentForm.getId());
		
		if (null == labTestDocument) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/laboratoryResult";
		}

		int testId = labTestDocument.getLabTest().getId();

		this.labTestDocBo.delete(labTestDocument);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! File deleted");

		return "redirect:/system/laboratoryResult/enterresult/" + testId;
	}

	
	
}
	

