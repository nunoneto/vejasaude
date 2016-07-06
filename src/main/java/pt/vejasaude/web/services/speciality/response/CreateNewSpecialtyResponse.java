package pt.vejasaude.web.services.speciality.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.MedicalSpecialty.MedicalSpecialty;

import java.io.Serializable;

/**
 * Created by fmorais on 05/07/2016.
 */
public class CreateNewSpecialtyResponse  implements Serializable{
    @JsonProperty
    private int id;
    @JsonProperty
    private String specialty;

    public static CreateNewSpecialtyResponse of (MedicalSpecialty specialty)
    {
        CreateNewSpecialtyResponse specialtyResponse = new CreateNewSpecialtyResponse();

        specialtyResponse.id = specialty.getId();
        specialtyResponse.specialty = specialty.getSpecialty();

        return specialtyResponse;
    }
}