package pt.vejasaude.web.services.curriculumVitae.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.curriculumVitae.CurriculumVitae;

import java.io.Serializable;

/**
 * Created by fmorais on 19/07/2016.
 */
public class CreateNewCurriculumResponse implements Serializable{
    @JsonProperty
    private int id;
    @JsonProperty
    private byte[] description;

    public static CreateNewCurriculumResponse of (CurriculumVitae curriculumVitae)
    {
        CreateNewCurriculumResponse curriculumResponse = new CreateNewCurriculumResponse();
        curriculumResponse.id = curriculumVitae.getId();
        curriculumResponse.description = curriculumVitae.getDescription();

        return curriculumResponse;
    }
}
