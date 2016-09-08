package pt.vejasaude.web.services.curriculumVitae.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.curriculumVitae.CurriculumVitae;

/**
 * Created by fmorais on 19/08/2016.
 */
public class UpdateCurriculumResponse {
    @JsonProperty
    private String description;
    public static UpdateCurriculumResponse of (CurriculumVitae curriculumVitae)
    {
        UpdateCurriculumResponse curriculumResponse = new UpdateCurriculumResponse();

        curriculumResponse.description = new String(curriculumVitae.getDescription());

        return curriculumResponse;
    }
}
