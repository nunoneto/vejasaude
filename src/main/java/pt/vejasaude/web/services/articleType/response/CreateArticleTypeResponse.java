package pt.vejasaude.web.services.articleType.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.articleType.ArticleType;

/**
 * Created by fmorais on 26/10/2016.
 */
public class CreateArticleTypeResponse {
    @JsonProperty
    private int id;
    @JsonProperty
    private String articleType;
    @JsonProperty
    private String humanReadableType;

    public static  CreateArticleTypeResponse of (ArticleType articleType){
        CreateArticleTypeResponse articleTypeResponse = new CreateArticleTypeResponse();

        articleTypeResponse.id = articleType.getId();
        articleTypeResponse.articleType = articleType.getArticleType();
        articleTypeResponse.humanReadableType = articleType.getHumanReadableType();

        return articleTypeResponse;
    }
}
