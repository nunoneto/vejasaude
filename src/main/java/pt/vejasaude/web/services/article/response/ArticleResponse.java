package pt.vejasaude.web.services.article.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.article.Article;
import pt.vejasaude.unified.data.articleType.ArticleType;
import pt.vejasaude.unified.data.backofficeuser.BackOfficeUser;

import java.util.Date;

/**
 * Created by Nuno Neto on 31-10-2016.
 */
public class ArticleResponse {

    @JsonProperty
    private int id;

    @JsonProperty
    private ArticleType articleType;

    @JsonProperty
    private String title;

    @JsonProperty
    private long createdDate;

    @JsonProperty
    private BackOfficeUser createdBy;


    public static ArticleResponse of(Article article){

        ArticleResponse articleResponse = new ArticleResponse();
        articleResponse.articleType = article.getTypeArticle();
        articleResponse.createdDate = article.getCreatedDate().getTime();
        articleResponse.title = article.getTitle();
        articleResponse.id = article.getId();
        articleResponse.createdBy = article.getUser();
        articleResponse.createdBy.setSessionID(null);

        return articleResponse;
    }

}
