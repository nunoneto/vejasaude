package pt.vejasaude.web.services.doctor.response;

import pt.vejasaude.unified.data.backofficeuser.CurriculumVitae;
import pt.vejasaude.unified.data.backofficeuser.MedicalSpecialty;
import pt.vejasaude.unified.data.doctor.Doctor;

import javax.persistence.*;

/**
 * Created by fmorais on 15/06/2016.
 */
public class CreateDoctorResponse {

    private int id;
    private String name;
    private MedicalSpecialty speciality;
    private CurriculumVitae curriculum;

    public static CreateDoctorResponse of(Doctor doctor)
    {
        CreateDoctorResponse doctorResponse = new CreateDoctorResponse();

        doctorResponse.curriculum = doctor.getCurriculum();
        doctorResponse.speciality = doctor.getSpecialty();

        return doctorResponse;
    }
}
