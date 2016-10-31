package pt.vejasaude.web.services.article.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.article.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmorais on 28/09/2016.
 */
public class CreateArticleResponse {
    @JsonProperty
    private int id;
    @JsonProperty
    private String description;
    @JsonProperty
    private List<Integer> idAttachment;

    public CreateArticleResponse(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public void setIdAttachment(List<Integer> idAttachment) {
        this.idAttachment = idAttachment;
    }

    public static CreateArticleResponse of (Article articles){

        CreateArticleResponse createArticleResponse = new CreateArticleResponse(articles.getId(),new String(articles.getDescription()));
        if(articles.getAttachment() != null) {
            List attachments = new ArrayList<Integer>();

            for (Attachment att : articles.getAttachment()) {
                attachments.add(att.getId());
            }

            createArticleResponse.setIdAttachment(attachments);
        }
        return createArticleResponse;
    }
}
