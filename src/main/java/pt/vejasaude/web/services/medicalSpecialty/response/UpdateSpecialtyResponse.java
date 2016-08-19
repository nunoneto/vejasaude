package pt.vejasaude.web.services.medicalSpecialty.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.MedicalSpecialty.MedicalSpecialty;

/**
 * Created by fmorais on 19/08/2016.
 */
public class UpdateSpecialtyResponse {
    @JsonProperty
    private String specialty;
    public static UpdateSpecialtyResponse of (MedicalSpecialty specialty)
    {
        UpdateSpecialtyResponse specialtyResponse = new UpdateSpecialtyResponse();
        specialtyResponse.specialty = specialty.getSpecialty();
        return specialtyResponse;
    }

}
