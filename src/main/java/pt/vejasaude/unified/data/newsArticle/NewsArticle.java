package pt.vejasaude.unified.data.newsArticle;

import pt.vejasaude.unified.data.attachment.Attachment;

import javax.persistence.*;

/**
 * Created by fmorais on 24/08/2016.
 */
@Entity
@Table
public class NewsArticle {
    @Id
    @Column (name = "idGeneralArticle")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private byte[] description;
    //@ManyToMany
    @JoinColumn (name = "idAttachment")
    private Attachment attachment;

    public NewsArticle() {
    }

    public NewsArticle(int id, byte[] description, Attachment attachment) {
        this.id = id;
        this.description = description;
        this.attachment = attachment;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public byte[] getDescription() {return description;}

    public void setDescription(byte[] description) {this.description = description;}

    public pt.vejasaude.unified.data.attachment.Attachment getAttachment() {return attachment;}

    public void setAttachment(Attachment attachment) {this.attachment = attachment;}
}
