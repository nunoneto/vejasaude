package pt.vejasaude.web.services.videoArticle.request;

import pt.vejasaude.unified.data.video.Video;

/**
 * Created by fmorais on 28/09/2016.
 */
public class CreateVideoArticleRequest {
    private String description;
    private int[] listIdAttachments;
    private int[] videos;
    public String getDescription() {
        return description;
    }
    public int[] getlistIdAttachments() {return listIdAttachments;}
    public int[] getVideos() {return videos;}
}

