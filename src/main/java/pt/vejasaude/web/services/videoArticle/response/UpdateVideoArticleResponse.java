package pt.vejasaude.web.services.videoArticle.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.newsArticle.NewsArticle;
import pt.vejasaude.unified.data.video.Video;
import pt.vejasaude.unified.data.videoArticle.VideoArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmorais on 12/10/2016.
 */
public class UpdateVideoArticleResponse {
    @JsonProperty
    private int id;
    @JsonProperty
    private String description;
    @JsonProperty
    private List <Integer> videos;
    @JsonProperty
    private List<Integer> attachments;

    public UpdateVideoArticleResponse(int id, String description) {
        this.id = id;
        this.description = description;
    }
    public void setAttachments (List<Integer> attachments) {this.attachments = attachments; }

    public void setVideos(List<Integer> idVideo) {this.videos = idVideo;}

    public  static UpdateVideoArticleResponse of (VideoArticle videoArticle){
        UpdateVideoArticleResponse updateVideoArticleResponse = new UpdateVideoArticleResponse(videoArticle.getId(),new String(videoArticle.getContent()));
        if(videoArticle.getAttachment() != null){
            List attachments = new ArrayList<Integer>();
            for (Attachment att:videoArticle.getAttachment()){
                attachments.add((att.getId()));
            }
            updateVideoArticleResponse.setAttachments(attachments);
        }
        if (videoArticle.getVideo() != null){
            List videos = new ArrayList<Integer>();
            for (Video vid:videoArticle.getVideo()){
                videos.add(vid.getId());
            }
            updateVideoArticleResponse.setVideos(videos);
        }
        return updateVideoArticleResponse;
    }
}
