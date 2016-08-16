package pt.vejasaude.web.services.doctor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;
import pt.vejasaude.unified.data.CurriculumVitae.CurriculumVitae;
import pt.vejasaude.unified.data.MedicalSpecialty.MedicalSpecialty;
import pt.vejasaude.unified.data.doctor.Doctor;
import pt.vejasaude.web.services.doctor.request.UpdateDoctorRequest;

import java.io.Serializable;

/**
 * Created by fmorais on 16/08/2016.
 */
public class UpdateDoctorResponse implements Serializable {
    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    private MedicalSpecialty speciality;
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