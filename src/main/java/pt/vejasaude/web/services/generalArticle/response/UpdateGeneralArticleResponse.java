package pt.vejasaude.web.services.generalArticle.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.generalArticle.GeneralArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmorais on 11/10/2016.
 */
public class UpdateGeneralArticleResponse {
    @JsonProperty
    private int id;
    @JsonProperty
    private String description;
    @JsonProperty
    private List<Integer> idAttachment;

    public UpdateGeneralArticleResponse(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public void setIdAttachment(List<Integer> idAttachment) {
        this.idAttachment = idAttachment;
    }

    public static UpdateGeneralArticleResponse of (GeneralArticle generalArticles){

        UpdateGeneralArticleResponse updateGeneralArticleResponse = new UpdateGeneralArticleResponse(generalArticles.getId(),new String(generalArticles.getDescription()));
        if(generalArticles.getAttachment() != null) {
            List attachments = new ArrayList<Integer>();
            for (Attachment att : generalArticles.getAttachment()) {
                attachments.add(att.getId());
            }

            updateGeneralArticleResponse.setIdAttachment(attachments);
        }
        return updateGeneralArticleResponse;
    }

}
