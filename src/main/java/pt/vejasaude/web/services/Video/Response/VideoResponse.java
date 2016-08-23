package pt.vejasaude.web.services.Video.Response;

import pt.vejasaude.unified.data.Video.Video;
import pt.vejasaude.unified.data.doctor.Doctor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fmorais on 23/08/2016.
 */
public class VideoResponse implements Serializable {
    private String linkVideo;
    private Date date;
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
    }
}