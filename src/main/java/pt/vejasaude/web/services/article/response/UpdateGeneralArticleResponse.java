package pt.vejasaude.web.services.article.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.article.Article;

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

    public static UpdateGeneralArticleResponse of (Article articles){

        UpdateGeneralArticleResponse updateGeneralArticleResponse = new UpdateGeneralArticleResponse(articles.getId(),new String(articles.getDescription()));
        if(articles.getAttachment() != null) {
            List attachments = new ArrayList<Integer>();
            for (Attachment att : articles.getAttachment()) {
                attachments.add(att.getId());
            }

            updateGeneralArticleResponse.setIdAttachment(attachments);
        }
        return updateGeneralArticleResponse;
    }

}
