package pt.vejasaude.web.services.newsArticle.request;

/**
 * Created by fmorais on 30/08/2016.
 */
public class UpdateNewsArticleRequest {
    private String description;
    private int[] listIdAttachments;

    public String getDescription() {
        return description;
    }

    public int[] getlistIdAttachments() {return listIdAttachments;}
}
