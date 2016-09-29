package pt.vejasaude.web.services.featuredArticle.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.featuredArticle.FeaturedArticle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fmorais on 30/08/2016.
 */
public class CreateNewFeaturedArticleResponse implements Serializable {
    @JsonProperty
    private int id;
    @JsonProperty
    private byte[] description;
    @JsonProperty
    private List<Attachment> attachments;
    public static CreateNewFeaturedArticleResponse of(FeaturedArticle featuredArticle) {
        CreateNewFeaturedArticleResponse featuredArticleResponse = new CreateNewFeaturedArticleResponse();
        featuredArticleResponse.id = featuredArticle.getId();
        featuredArticleResponse.description = featuredArticle.getDescription();
        featuredArticleResponse.attachments = featuredArticle.getAttachment();

        return featuredArticleResponse;
    }
}
