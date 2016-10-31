package pt.vejasaude.web.services.referenceLink.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.referenceLink.ReferenceLink;

import java.io.Serializable;

/**
 * Created by fmorais on 26/10/2016.
 */
public class ReferenceLinkResponse implements Serializable {
    @JsonProperty
    private int id;
    @JsonProperty
    private String referenceLink;

    public static ReferenceLinkResponse of (ReferenceLink referenceLink)
    {
        ReferenceLinkResponse ReferenceLinkResponse = new ReferenceLinkResponse();
        ReferenceLinkResponse.id = referenceLink.getId();
        ReferenceLinkResponse.referenceLink = referenceLink.getReferenceLink();

        return ReferenceLinkResponse;
    }

}
