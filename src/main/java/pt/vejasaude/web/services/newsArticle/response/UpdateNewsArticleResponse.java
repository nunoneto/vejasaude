package pt.vejasaude.web.services.featuredArticle.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.featuredArticle.FeaturedArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmorais on 12/10/2016.
 */
public class UpdateFeaturedArticleResponse {
    @JsonProperty
    private int id;
    @JsonProperty
    private String description;
    @JsonProperty
    private List<Integer> attachments;

    public UpdateFeaturedArticleResponse(int id, String description) {
        this.id = id;
        this.description = description;
    }
    public void setAttachments (List<Integer> attachments) {this.attachments = attachments; }

    public  static UpdateFeaturedArticleResponse of (FeaturedArticle featuredArticle){
        UpdateFeaturedArticleResponse updateFeaturedArticleResponse = new UpdateFeaturedArticleResponse(featuredArticle.getId(),new String(featuredArticle.getDescription()));
        if(featuredArticle.getAttachment() != null){
            List attachments = new ArrayList<Integer>();
            for (Attachment att:featuredArticle.getAttachment()){
                attachments.add((att.getId()));
            }
            updateFeaturedArticleResponse.setAttachments(attachments);
        }
        return updateFeaturedArticleResponse;
    }
}
