package pt.vejasaude.web.services.generalArticle.request;

/**
 * Created by fmorais on 28/09/2016.
 */
public class CreateNewGeneralArticleRequest {
    private String description;
    private int[] listIdAttachments;

    public String getDescription() {
        return description;
    }

    public int[] getListIdAttachments() {
        return listIdAttachments;
    }
}
