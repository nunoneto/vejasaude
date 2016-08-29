package pt.vejasaude.unified.data.attachment;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fmorais on 24/08/2016.
 */
@Entity
@Table
public class Attachment implements Serializable{
    @Id
    @Column (name="idAttachment")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String contentType;
    @Column
    private String fileName;
    @Column
    private long size;
    @Column
    private byte[] attachment;

    public Attachment() {
    }

    public Attachment(int id, String contentType, String fileName, long size, byte[] attachment) {
        this.id = id;
        this.contentType = contentType;
        this.fileName = fileName;
        this.size = size;
        this.attachment = attachment;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getContentType() {return contentType;}

    public void setContentType(String contentType) {this.contentType = contentType;}

    public String getFileName() {return fileName;}

    public void setFileName(String fileName) {this.fileName = fileName;}

    public long getSize() {return size;}

    public void setSize(long size) {this.size = size;}

    public byte[] getAttach() {return attachment;}

    public void setAttach(byte[] attachment) {this.attachment = attachment;}
}
