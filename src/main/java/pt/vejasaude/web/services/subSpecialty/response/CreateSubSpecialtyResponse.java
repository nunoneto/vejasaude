package pt.vejasaude.web.services.subSpecialty.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.articleType.ArticleType;
import pt.vejasaude.unified.data.medicalSpecialty.MedicalSpecialty;
import pt.vejasaude.unified.data.subSpecialty.SubSpecialty;
import pt.vejasaude.web.services.medicalSpecialty.SpecialtyController;
import pt.vejasaude.web.services.medicalSpecialty.response.CreateNewSpecialtyResponse;

/**
 * Created by fmorais on 26/10/2016.
 */
public class CreateSubSpecialtyResponse {
    @JsonProperty
    private int id;
    @JsonProperty
    private CreateNewSpecialtyResponse specialty;
    @JsonProperty
    private String subSpecialty;

    public static CreateSubSpecialtyResponse of (SubSpecialty subSpecialty){
        CreateSubSpecialtyResponse subSpecialtyResponse = new CreateSubSpecialtyResponse();

        subSpecialtyResponse.id = subSpecialty.getId();
        subSpecialtyResponse.subSpecialty = subSpecialty.getSubSpecialty();
        subSpecialtyResponse.specialty = CreateNewSpecialtyResponse.of(subSpecialty.getSpecialty());

        return subSpecialtyResponse;
    }
}
