package pt.vejasaude.web.services.doctor.response;

import pt.vejasaude.unified.data.CurriculumVitae.CurriculumVitae;
import pt.vejasaude.unified.data.MedicalSpecialty.MedicalSpecialty;
import pt.vejasaude.unified.data.doctor.Doctor;

/**
 * Created by fmorais on 15/06/2016.
 */
public class CreateDoctorResponse {

    private String username;

    private String name;

    private MedicalSpecialty Specialty;

    private CurriculumVitae Curriculum;

    public static CreateDoctorResponse of (Doctor doctor)
    {
        CreateDoctorResponse doctorResponse = new CreateDoctorResponse();

        doctorResponse.username = doctor.getUsername();
        doctorResponse.name = doctor.getName();
        doctorResponse.Curriculum = doctor.getCurriculum();
        doctorResponse.Specialty = doctor.getSpecialty();

        return doctorResponse;
    }
}
