package pt.vejasaude.web.services.videoArticle.request;

/**
 * Created by fmorais on 30/08/2016.
 */
public class UpdateVideoArticleRequest {
    private String description;
    private int[] listIdAttachments;
    private int[] videos;
    public String getDescription() {
        return description;
    }
    public int[] getlistIdAttachments() {return listIdAttachments;}
    public int[] getVideos() {return videos;}
}
