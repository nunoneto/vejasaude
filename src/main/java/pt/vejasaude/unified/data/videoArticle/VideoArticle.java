package pt.vejasaude.unified.data.videoArticle;

import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.video.Video;

import javax.persistence.*;
import java.util.List;

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
    private byte[] content;
    @ManyToMany
    @JoinColumn (name = "idAttachment")
    private List<Attachment> attachment;
    @ManyToMany
    @JoinColumn (name = "idVideo")
    private List<Video> video;

    public VideoArticle() {}

    public VideoArticle(int id, byte[] content, List<Attachment> attachment, List<Video> video) {
        this.id = id;
        this.content = content;
        this.attachment = attachment;
        this.video = video;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public byte[] getContent() {return content;}

    public void setContent(byte[] content) {this.content = content;}

    public List<Attachment> getAttachment() {return attachment;}

    public void setAttachment(List<Attachment> attachment) {this.attachment = attachment;}

    public List<Video> getVideo() {return video;}

    public void setVideo(List<Video> video) {this.video = video;}
}
