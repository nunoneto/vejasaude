package pt.vejasaude.web.services.featuredArticle.request;

import pt.vejasaude.unified.data.attachment.Attachment;

import java.util.List;

/**
 * Created by fmorais on 30/08/2016.
 */
public class UpdateFeaturedArticleRequest {
    private String description;
    private int[] listIdAttachments;

    public String getDescription() {
        return description;
    }

    public int[] getlistIdAttachments() {return listIdAttachments;}
}
