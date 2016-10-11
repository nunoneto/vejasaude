package pt.vejasaude.web.services.generalArticle.request;

/**
 * Created by fmorais on 11/10/2016.
 */
public class UpdateGeneralArticleRequest {
    private String description;
    private int[] listIdAttachments;

    public String getDescription() {
        return description;
    }

    public int[] getListIdAttachments() {
        return listIdAttachments;
    }
}
