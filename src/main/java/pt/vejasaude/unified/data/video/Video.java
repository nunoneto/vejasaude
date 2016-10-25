package pt.vejasaude.unified.data.video;

import pt.vejasaude.unified.data.doctor.Doctor;
import pt.vejasaude.web.services.video.request.VideoRequest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by fmorais on 23/08/2016.
 */
@Entity
@Table
public class Video implements Serializable{
    @Id
    @Column (name="idVideo")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String videoLink;
    @Column
    private Date date;
    @Column
    @JoinColumn(name = "idDoctor")
    private Doctor author;

    public Video(String videoLink, Date date, Doctor author) {
        this.videoLink = videoLink;
        this.date = date;
        this.author = author;
    }
    public Video (Video vid){
        this.videoLink = vid.getVideoLink();
        this.date = vid.getDate();
        this.author = vid.getAuthor();
    }
    public Video() {
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getVideoLink() {return videoLink;}

    public void setVideoLink(String videoLink) {this.videoLink = videoLink;}

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public Doctor getAuthor() {return author;}

    public void setAuthor(Doctor author) {author = author;}
}
