package pt.vejasaude.web.services.video.request;

import java.util.Date;

/**
 * Created by fmorais on 23/08/2016.
 */
public class VideoRequest {
    private String linkVideo;
    private Date date;
    private Integer author;

    public String getLinkVideo() {return linkVideo;}

    public Date getDate() {return date;}

    public Integer getAuthor() {return author;}

}
