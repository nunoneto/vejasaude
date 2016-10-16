package pt.vejasaude.web.services.featuredArticle.request;

/**
 * Created by fmorais on 28/09/2016.
 */
public class CreateFeaturedArticleRequest {
    private String description;
    private int[] listIdAttachments;

    public String getDescription() {
        return description;
    }

    public int[] getlistIdAttachments() {return listIdAttachments;}
}

