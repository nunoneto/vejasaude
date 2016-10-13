package pt.vejasaude.web.services.doctor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.medicalSpecialty.MedicalSpecialty;
import pt.vejasaude.unified.data.doctor.Doctor;
import pt.vejasaude.web.services.curriculumVitae.response.CreateNewCurriculumResponse;
import pt.vejasaude.web.services.medicalSpecialty.response.CreateNewSpecialtyResponse;

import java.io.Serializable;

/**
 * Created by fmorais on 15/06/2016.
 */
public class CreateDoctorResponse implements Serializable {

    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private CreateNewSpecialtyResponse speciality;
    @JsonProperty
    private CreateNewCurriculumResponse curriculum;

    public static CreateDoctorResponse of(Doctor doctor)
    {
        CreateDoctorResponse doctorResponse = new CreateDoctorResponse();

        doctorResponse.id = doctor.getId();
        doctorResponse.name = doctor.getName();

        if (doctor.getCurriculum() != null)
            doctorResponse.curriculum = CreateNewCurriculumResponse.of(doctor.getCurriculum());
        if (doctor.getSpecialty() != null)
            doctorResponse.speciality = CreateNewSpecialtyResponse.of(doctor.getSpecialty());

        return doctorResponse;
    }
}
