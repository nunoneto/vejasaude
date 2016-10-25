package pt.vejasaude.web.services.newsArticle.request;

/**
 * Created by fmorais on 28/09/2016.
 */
public class CreateNewsArticleRequest {
    private String description;
    private int[] listIdAttachments;

    public String getDescription() {
        return description;
    }

    public int[] getlistIdAttachments() {return listIdAttachments;}
}

