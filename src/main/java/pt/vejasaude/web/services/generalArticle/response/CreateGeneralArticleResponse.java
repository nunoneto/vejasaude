package pt.vejasaude.web.services.generalArticle.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.generalArticle.GeneralArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmorais on 28/09/2016.
 */
public class CreateGeneralArticleResponse {
    @JsonProperty
    private int id;
    @JsonProperty
    private String description;
    @JsonProperty
    private List<Integer> idAttachment;

    public CreateGeneralArticleResponse(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public void setIdAttachment(List<Integer> idAttachment) {
        this.idAttachment = idAttachment;
    }

    public static CreateGeneralArticleResponse of (GeneralArticle generalArticles){

        CreateGeneralArticleResponse createGeneralArticleResponse = new CreateGeneralArticleResponse(generalArticles.getId(),new String(generalArticles.getDescription()));
        List attachments = new ArrayList<Integer>();

        for (Attachment att:generalArticles.getAttachment()) {
            attachments.add(att.getId());
        }

        createGeneralArticleResponse.setIdAttachment(attachments);

        return createGeneralArticleResponse;
    }
}
