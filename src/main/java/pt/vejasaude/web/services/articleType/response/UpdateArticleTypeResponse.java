package pt.vejasaude.web.services.articleType.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.articleType.ArticleType;

/**
 * Created by fmorais on 26/10/2016.
 */
public class UpdateArticleTypeResponse {
    @JsonProperty
    private int id;
    @JsonProperty
    private String articleType;

    public static UpdateArticleTypeResponse of (ArticleType articleType){
        UpdateArticleTypeResponse articleTypeResponse = new UpdateArticleTypeResponse();

        articleTypeResponse.id = articleType.getId();
        articleTypeResponse.articleType = articleType.getArticleType();

        return articleTypeResponse;
    }
}
