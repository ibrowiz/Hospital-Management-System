package org.calminfotech.lab.bo;

import java.util.List;

import org.calminfotech.lab.models.LabTest;
import org.calminfotech.lab.models.LabTestDocument;
import org.calminfotech.utils.models.Organisation;

public interface LabTestDocumentBo {
		
		public List<LabTestDocument> fetchAll();

		public List<LabTestDocument> fetchAllByOrgainsation(Organisation organisation);

		public List<LabTestDocument> fetchAllByTest(LabTest LabTest);

		public void save(LabTestDocument labDocument);

		public LabTestDocument getLabDocumentById(int id);

		public void delete(LabTestDocument labDocument);

}
