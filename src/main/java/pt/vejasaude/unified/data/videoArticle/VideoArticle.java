package pt.vejasaude.unified.data.videoArticle;

import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.video.Video;

import javax.persistence.*;

/**
 * Created by fmorais on 24/08/2016.
 */
@Entity
@Table
public class VideoArticle {
    @Id
    @Column(name="idVideoArticle")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private byte[] description;
    //@ManyToMany
    @JoinColumn (name = "idAttachment")
    private Attachment attachment;
    //@ManyToMany
    @JoinColumn (name = "idVideo")
    private Video video;

    public VideoArticle() {}

    public VideoArticle(int id, byte[] description, Attachment attachment, Video video) {
        this.id = id;
        this.description = description;
        this.attachment = attachment;
        this.video = video;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public byte[] getDescription() {return description;}

    public void setDescription(byte[] description) {this.description = description;}

    public Attachment getAttachment() {return attachment;}

    public void setAttachment(Attachment attachment) {this.attachment = attachment;}

    public Video getVideo() {return video;}

    public void setVideo(Video video) {this.video = video;}
}
