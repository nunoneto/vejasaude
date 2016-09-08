package pt.vejasaude.web.services.featuredArticle.request;

import pt.vejasaude.unified.data.attachment.Attachment;

import java.util.List;


public class CreateNewFeaturedArticleRequest {
    private byte[] description;
    private List<Attachment> attachments;
}
