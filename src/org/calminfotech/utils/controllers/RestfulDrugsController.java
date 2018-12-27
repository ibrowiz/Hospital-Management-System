package org.calminfotech.utils.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.calminfotech.admin.boInterface.DrugBo;
import org.calminfotech.system.models.Ailment;
import org.calminfotech.system.models.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/utilities/drugs")
public class RestfulDrugsController {

	@Autowired
	private DrugBo drugBo;

	@ResponseBody
	@RequestMapping(value = "/search")
	public String searchDrugsByName(HttpServletRequest request) {

		String q = request.getParameter("data[q]");

		JSONObject obj = new JSONObject();
		obj.accumulate("q", q);

		// Loop and construct the JSON Object
		List<Drug> list = this.drugBo.fetchLikeName(q);
		JSONArray array = new JSONArray();
		for (Drug drug : list) {

			JSONObject o = new JSONObject();
			o.accumulate("id", drug.getId());
			o.accumulate("text", drug.getName());
			array.add(o);
		}
		
		obj.accumulate("results", array);

		return obj.toString();
	}

}
