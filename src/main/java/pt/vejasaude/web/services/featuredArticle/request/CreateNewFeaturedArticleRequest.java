package pt.vejasaude.web.services.featuredArticle.request;

/**
 * Created by fmorais on 28/09/2016.
 */
public class CreateNewFeaturedArticleRequest {
    private String description;
    private int[] idAttachments;

    public String getDescription() {
        return description;
    }

    public int[] getIdAttachments() {
        return idAttachments;
    }
}

