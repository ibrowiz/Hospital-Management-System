package org.calminfotech.utils.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.calminfotech.admin.boInterface.AilmentBo;
import org.calminfotech.system.models.Ailment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/utilities/ailments")
public class RestfulAilmentController {

	@Autowired
	private AilmentBo ailmentBo;

	@ResponseBody
	@RequestMapping(value = "/search")
	public String searchAilmentsByName(HttpServletRequest request) {

		String q = request.getParameter("data[q]");

		JSONObject obj = new JSONObject();
		obj.accumulate("q", q);

		// Loop and construct the JSON Object
		List<Ailment> list = this.ailmentBo.fetchLikeName(q);
		JSONArray array = new JSONArray();
		for (Ailment ailment : list) {

			JSONObject o = new JSONObject();
			o.accumulate("id", ailment.getId());
			o.accumulate("text", ailment.getName());
			array.add(o);
		}
		
		obj.accumulate("results", array);

		return obj.toString();
	}
}
