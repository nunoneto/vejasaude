package pt.vejasaude.facade;

import pt.vejasaude.unified.data.CurriculumVitae.CurriculumVitae;
import pt.vejasaude.unified.data.MedicalSpecialty.MedicalSpecialty;
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
