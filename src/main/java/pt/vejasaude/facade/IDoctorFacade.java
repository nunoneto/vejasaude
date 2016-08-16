package pt.vejasaude.facade;

import pt.vejasaude.unified.data.doctor.Doctor;
import pt.vejasaude.web.services.doctor.request.CreateNewDoctorRequest;
import pt.vejasaude.unified.data.MedicalSpecialty.MedicalSpecialty;
import pt.vejasaude.unified.data.CurriculumVitae.CurriculumVitae;
import pt.vejasaude.web.services.doctor.request.UpdateDoctorRequest;

import javax.print.Doc;

/**
 * Created by fmorais on 15/06/2016.
 */
public interface IDoctorFacade {
    Doctor createDoctor (CreateNewDoctorRequest request, MedicalSpecialty speciality, CurriculumVitae curriculum);
    Iterable<Doctor> getAll();
    Doctor findOne(String id);
    Doctor updateDoctor(Doctor changes);
}
