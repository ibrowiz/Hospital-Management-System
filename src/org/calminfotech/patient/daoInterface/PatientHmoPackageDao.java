package org.calminfotech.patient.daoInterface;

import java.util.List;

import org.calminfotech.patient.models.PatientHmoPackage;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.patient.models.Patient;

public interface PatientHmoPackageDao {

	public List<PatientHmoPackage> fetchAll();

	public List<PatientHmoPackage> fetchAllByPatient(Patient patient);

	public List<PatientHmoPackage> fetchAllByHmoPackage(
			EhmoPackages hmoPackage);

	public PatientHmoPackage getHmoPackageByPatientAndPackage(Patient patient,
			EhmoPackages hmoPackage);

	public void save(PatientHmoPackage hmoPackage);

	public void delete(PatientHmoPackage hmoPackage);

	public void update(PatientHmoPackage hmoPackage);

}
