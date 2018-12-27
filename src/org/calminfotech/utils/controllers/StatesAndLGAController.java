package org.calminfotech.utils.controllers;

import java.util.List;
import java.util.Set;

import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.models.LocalGovernmentArea;
import org.calminfotech.utils.models.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/utilities/statesandlgas")
public class StatesAndLGAController {

	@Autowired
	private StatesList statesList; 
	@Autowired
	private LocalGovernmentAreaList localGovtAreaList;

	@RequestMapping(value = "/states", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getStates() {
		String statesStr = "<option value='0'>Select States</option>";
		List<State> stateList = statesList.fetchAll();
		for (State state : stateList) {
			statesStr += "<option value='" + state.getStateId() + "'>"
					+ state.getStateName() + "</option>";
		}
		return statesStr;
	}

	@RequestMapping(value = "/lgas", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getLgas() {
		String lgasStr = "<option value='0'>Select LGA</option>";
		List<LocalGovernmentArea> list = localGovtAreaList.fetchAll();
		for (LocalGovernmentArea lga : list) {
			lgasStr += "<option value='" + lga.getLocalGovernmentAreaId()
					+ "'>" + lga.getLocalGovernmentAreasName() + "</option>";
		}
		return lgasStr;
	}

	@RequestMapping(value = "/state/id/{stateId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getLgasByStateIdCodethag(@PathVariable("stateId") Integer id) {
		String lgasStr = "<option value='0'>Select LGA</option>";

		State state = statesList.getStateById(id);

		if (state == null)
			return lgasStr;

		Set<LocalGovernmentArea> list = state.getLocalGovernmentArea();

		for (LocalGovernmentArea lga : list) {
			lgasStr += "<option value='" + lga.getLocalGovernmentAreaId()
					+ "'>" + lga.getLocalGovernmentAreasName() + "</option>";
		}
		return lgasStr;
	}

	@RequestMapping(value = "/lgabystate/{stateId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getLgasByStateId(@PathVariable("stateId") Integer stateid) {
		String lgasStr = "<option value='0'>Select LGA</option>";

		State state = statesList.getStateById(stateid);

		if (state == null)
			return lgasStr;
		Set<LocalGovernmentArea> list = state.getLocalGovernmentArea();

		for (LocalGovernmentArea lga : list) {
			lgasStr += "<option value='" + lga.getLocalGovernmentAreaId()
					+ "'>" + lga.getLocalGovernmentAreasName() + "</option>";
		}
		return lgasStr;
	}
}
