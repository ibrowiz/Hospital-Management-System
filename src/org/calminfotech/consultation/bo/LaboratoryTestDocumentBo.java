package org.calminfotech.consultation.bo;

import java.util.List;

import org.calminfotech.consultation.models.LaboratoryTestDocument;
import org.calminfotech.lab.models.LabTest;
import org.calminfotech.utils.models.Organisation;

public interface LaboratoryTestDocumentBo {
		
		public List<LaboratoryTestDocument> fetchAll();

		public List<LaboratoryTestDocument> fetchAllByOrgainsation(Organisation organisation);

		public List<LaboratoryTestDocument> fetchAllByTest(LabTest LabTest);

		public void save(LaboratoryTestDocument labDocument);

		public LaboratoryTestDocument getLabDocumentById(int id);

		public void delete(LaboratoryTestDocument labDocument);

}
