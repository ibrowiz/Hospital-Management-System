package org.calminfotech.utils.controllers;

import java.util.List;

import org.calminfotech.setup.boInterface.AllergyCategoryBo;
import org.calminfotech.setup.boInterface.AllergyCategoryBo;
import org.calminfotech.setup.models.Allergy;
import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.setup.models.AllergyCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/utilities/allergies")
public class AllergiesCategoriesController {

	@Autowired
	private AllergyCategoryBo allergyCategoryBo1;

	@ResponseBody
	@RequestMapping(value = "categories")
	public String getAllergyCategory() {
		String response = "";
		List<AllergyCategory> categoryList = this.allergyCategoryBo1.fetchAll();

		if (categoryList.size() > 0) {
			response = "<option>Select a category</option>";
			for (AllergyCategory category : categoryList) {
				response += "<option value='" + category.getAllergyCategoryId() + "'>"
						+ category.getName() + "</option>";
			}

		} else {
			response = "<option value=''>No Allergy Category available</option>";
		}

		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/allergiesbycategory/{id}")
	public String getAllergiesByCategoryHTML(@PathVariable("id") Integer id) {
		String response = "";

		AllergyCategory category = this.allergyCategoryBo1.getCategoryById(id);
				

		if (category.getAllergies().size() > 0) {
			response = "<option>Select an allegry</option>";
			for (Allergy allergy : category.getAllergies()) {
				response += "<option value='" + allergy.getAllergyId() + "'>"
						+ allergy.getName() + "</option>";
			}
		} else {
			response = "<option value=''>No Allergy available in this Category</option>";
		}

		return response;
	}
	

/*	@ResponseBody
	@RequestMapping(value = "/allergiesbycategory/{id}")
	public String getAllergiesByCategoryJSON(@PathVariable("id") Integer id) {
		String response = "";

		AllergyCategory category = this.allergyCategoryBo
				.getAllergyCategoryById(id);

		if (category.getAllergies().size() > 0) {
			response = "<option>Select an allegry</option>";
			for (Allergy allergy : category.getAllergies()) {
				response += "<option value='" + allergy.getId() + "'>"
						+ allergy.getName() + "</option>";
			}
		} else {
			response = "<option value=''>No Allergy available in this Category</option>";
		}

		return response;
	}*/
}
