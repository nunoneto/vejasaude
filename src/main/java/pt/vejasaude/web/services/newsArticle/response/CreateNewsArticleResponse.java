package pt.vejasaude.web.services.newsArticle.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.featuredArticle.FeaturedArticle;
import pt.vejasaude.unified.data.newsArticle.NewsArticle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmorais on 30/08/2016.
 */
public class CreateNewsArticleResponse implements Serializable {
    @JsonProperty
    private int id;
    @JsonProperty
    private String description;
    @JsonProperty
    private List<Integer> attachments;

    public CreateNewsArticleResponse(int id, String description) {
        this.id = id;
        this.description = description;
    }
    public void setAttachments (List<Integer> attachments) {this.attachments = attachments; }

    public  static CreateNewsArticleResponse of (NewsArticle newsArticle){
        CreateNewsArticleResponse createNewsArticleResponse = new CreateNewsArticleResponse(newsArticle.getId(),new String(newsArticle.getDescription()));
        if(newsArticle.getAttachment() != null){
            List attachments = new ArrayList<Integer>();
            for (Attachment att:newsArticle.getAttachment()){
                attachments.add((att.getId()));
            }
            createNewsArticleResponse.setAttachments(attachments);
        }
        return createNewsArticleResponse;
    }
}
