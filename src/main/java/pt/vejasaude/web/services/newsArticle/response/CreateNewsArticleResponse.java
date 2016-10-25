package pt.vejasaude.web.services.featuredArticle.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.featuredArticle.FeaturedArticle;
import pt.vejasaude.web.services.attachment.response.CreateNewAttachmentResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmorais on 30/08/2016.
 */
public class CreateFeaturedArticleResponse implements Serializable {
    @JsonProperty
    private int id;
    @JsonProperty
    private String description;
    @JsonProperty
    private List<Integer> attachments;

    public CreateFeaturedArticleResponse(int id, String description) {
        this.id = id;
        this.description = description;
    }
    public void setAttachments (List<Integer> attachments) {this.attachments = attachments; }

    public  static CreateFeaturedArticleResponse of (FeaturedArticle featuredArticle){
        CreateFeaturedArticleResponse createFeaturedArticleResponse = new CreateFeaturedArticleResponse(featuredArticle.getId(),new String(featuredArticle.getDescription()));
        if(featuredArticle.getAttachment() != null){
            List attachments = new ArrayList<Integer>();
            for (Attachment att:featuredArticle.getAttachment()){
                attachments.add((att.getId()));
            }
            createFeaturedArticleResponse.setAttachments(attachments);
        }
        return createFeaturedArticleResponse;
    }
}
