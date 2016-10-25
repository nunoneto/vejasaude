package pt.vejasaude.web.services.videoArticle.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.newsArticle.NewsArticle;
import pt.vejasaude.unified.data.videoArticle.VideoArticle;
import pt.vejasaude.unified.data.video.Video;
import pt.vejasaude.web.services.newsArticle.response.CreateNewsArticleResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmorais on 30/08/2016.
 */
public class CreateVideoArticleResponse implements Serializable {
    @JsonProperty
    private int id;
    @JsonProperty
    private String description;
    @JsonProperty
    private List <Integer> videos;
    @JsonProperty
    private List<Integer> attachments;

    public CreateVideoArticleResponse(int id, String description) {
        this.id = id;
        this.description = description;
    }
    public void setAttachments (List<Integer> attachments) {this.attachments = attachments; }

    public void setVideos(List<Integer> idVideo) {this.videos = idVideo;}

    public  static CreateVideoArticleResponse of (VideoArticle videoArticle){
        CreateVideoArticleResponse createVideoArticleResponse = new CreateVideoArticleResponse(videoArticle.getId(),new String(videoArticle.getContent()));
        if(videoArticle.getAttachment() != null){
            List attachments = new ArrayList<Integer>();
            for (Attachment att:videoArticle.getAttachment()){
                attachments.add((att.getId()));
            }
            createVideoArticleResponse.setAttachments(attachments);
        }
        if (videoArticle.getVideo() != null){
            List videos = new ArrayList<Integer>();
            for (Video vid:videoArticle.getVideo()){
                videos.add(vid.getId());
            }
            createVideoArticleResponse.setVideos(videos);
        }
        return createVideoArticleResponse;
    }
}
