package pt.vejasaude.unified.data.generalArticle;

import pt.vejasaude.unified.data.attachment.Attachment;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by fmorais on 24/08/2016.
 */
@Entity
@Table
public class GeneralArticle implements Serializable{
    @Id
    @Column (name="idGeneralArticle")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    @Column
    private byte[] description;
    @ManyToMany
    @JoinColumn (name = "idAttachment")
    private List<Attachment> attachment;

    public GeneralArticle() {
    }

    public GeneralArticle(int id, byte[] description, List<Attachment> attachment ) {
        this.id = id;
        this.description = description;
        this.attachment = attachment;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public byte[] getDescription() {return description;}

    public void setDescription(byte[] description) {this.description = description;}

    public List<Attachment> getAttachment() {return attachment;}

    public void setAttachment(List<Attachment> attachment) {this.attachment = attachment;}
}
