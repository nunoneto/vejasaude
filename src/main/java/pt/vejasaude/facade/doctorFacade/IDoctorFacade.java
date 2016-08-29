package pt.vejasaude.facade.doctorFacade;

import pt.vejasaude.unified.data.curriculumVitae.CurriculumVitae;
import pt.vejasaude.unified.data.medicalSpecialty.MedicalSpecialty;
import pt.vejasaude.unified.data.doctor.Doctor;
import pt.vejasaude.web.services.doctor.request.CreateNewDoctorRequest;

/**
 * Created by fmorais on 15/06/2016.
 */
public interface IDoctorFacade {
    Doctor createDoctor (CreateNewDoctorRequest request, MedicalSpecialty speciality, CurriculumVitae curriculum);
    Iterable<Doctor> getAll();
    Doctor findOne(int id);
    Doctor updateDoctor(Doctor changes);

}
