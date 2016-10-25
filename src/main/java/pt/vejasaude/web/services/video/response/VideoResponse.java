package pt.vejasaude.web.services.video.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.video.Video;
import pt.vejasaude.unified.data.doctor.Doctor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fmorais on 23/08/2016.
 */
public class VideoResponse implements Serializable {
    @JsonProperty
    private String linkVideo;
    @JsonProperty
    private Date date;
    @JsonProperty
    private Doctor author;

    public VideoResponse(String linkVideo, Date date, Doctor author) {
        this.linkVideo = linkVideo;
        this.date = date;
        this.author = author;
    }
    public VideoResponse(){}

    public static VideoResponse of (Video video)
    {
        VideoResponse videoResponse = new VideoResponse();
        videoResponse.linkVideo = video.getVideoLink();
        videoResponse.author = video.getAuthor();
        videoResponse.date = video.getDate();
        return videoResponse;
    }
}