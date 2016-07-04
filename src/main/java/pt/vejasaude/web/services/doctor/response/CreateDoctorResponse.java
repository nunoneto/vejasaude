package pt.vejasaude.web.services.doctor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.CurriculumVitae.CurriculumVitae;
import pt.vejasaude.unified.data.MedicalSpecialty.MedicalSpecialty;
import pt.vejasaude.unified.data.doctor.Doctor;

import java.io.Serializable;

/**
 * Created by fmorais on 15/06/2016.
 */
public class CreateDoctorResponse implements Serializable {

    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    private MedicalSpecialty speciality;
    private CurriculumVitae curriculum;

    public static CreateDoctorResponse of(Doctor doctor)
    {
        CreateDoctorResponse doctorResponse = new CreateDoctorResponse();
        doctorResponse.id = doctor.getId();
        doctorResponse.name = doctor.getName();
        doctorResponse.curriculum = doctor.getCurriculum();
        doctorResponse.speciality = doctor.getSpecialty();

        return doctorResponse;
    }
}
