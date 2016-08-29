package pt.vejasaude.web.services.medicalSpecialty.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.medicalSpecialty.MedicalSpecialty;
import java.io.Serializable;

/**
 * Created by fmorais on 16/08/2016.
 */
public class CreateNewSpecialtyResponse  implements Serializable{
    @JsonProperty
    private String specialty;
    public static CreateNewSpecialtyResponse of (MedicalSpecialty specialty)
    {
       CreateNewSpecialtyResponse specialtyResponse = new CreateNewSpecialtyResponse();
        specialtyResponse.specialty = specialty.getSpecialty();
        return specialtyResponse;
    }
}
