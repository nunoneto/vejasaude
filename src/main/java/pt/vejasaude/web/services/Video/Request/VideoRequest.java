package pt.vejasaude.web.services.Video.Request;

import java.util.Date;

/**
 * Created by fmorais on 23/08/2016.
 */
public class VideoRequest {
    private String linkVideo;
    private Date date;
    private Integer author;

    public String getLinkVideo() {return linkVideo;}

    public void setLinkVideo(String linkVideo) {this.linkVideo = linkVideo;}

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public Integer getAuthor() {return author;}

    public void setAuthor(Integer author) {this.author = author;}
}
