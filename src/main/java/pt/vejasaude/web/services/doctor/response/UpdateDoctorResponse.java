package pt.vejasaude.web.services.doctor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.curriculumVitae.CurriculumVitae;
import pt.vejasaude.unified.data.medicalSpecialty.MedicalSpecialty;
import pt.vejasaude.unified.data.doctor.Doctor;

import java.io.Serializable;

/**
 * Created by fmorais on 16/08/2016.
 */
public class UpdateDoctorResponse implements Serializable {
    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private MedicalSpecialty speciality;
    @JsonProperty
    private CurriculumVitae curriculum;

    public static UpdateDoctorResponse of(Doctor doctor) {
        UpdateDoctorResponse doctorResponse = new UpdateDoctorResponse();
        doctorResponse.id = doctor.getId();
        doctorResponse.name = doctor.getName();
        doctorResponse.curriculum = doctor.getCurriculum();
        doctorResponse.speciality = doctor.getSpecialty();

        return doctorResponse;

    }
}