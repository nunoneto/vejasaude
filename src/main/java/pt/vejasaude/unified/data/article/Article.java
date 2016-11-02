package pt.vejasaude.unified.data.article;

import pt.vejasaude.unified.data.articleType.ArticleType;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.backofficeuser.BackOfficeUser;
import pt.vejasaude.unified.data.doctor.Doctor;
import pt.vejasaude.unified.data.medicalSpecialty.MedicalSpecialty;
import pt.vejasaude.unified.data.referenceLink.ReferenceLink;
import pt.vejasaude.unified.data.subSpecialty.SubSpecialty;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by fmorais on 24/08/2016.
 */
@Entity
@Table
public class Article implements Serializable{
    @Id
    @Column (name="idGeneralArticle")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn (name = "idArticleType")
    private ArticleType articleType;
    @Column
    private String title;
    @Column
    private byte[] description;
    @ManyToOne
    @JoinColumn (name = "idSpecialty")
    private MedicalSpecialty specialty;
    @ManyToOne
    @JoinColumn (name = "idSubSpecialty")
    private SubSpecialty subSpecialty;
    @ManyToOne
    @JoinColumn(name = "idDoctor")
    private Doctor doctor;
    @ManyToMany
    @JoinColumn (name = "idAttachment")
    private List<Attachment> attachment;
    @Column
    private Date createdDate;
    @Column
    @ManyToMany
    @JoinColumn (name = "idReferenceLink")
    private List<ReferenceLink> referenceLinks;
    @ManyToOne
    @JoinColumn(name = "username")
    private BackOfficeUser user;

    public Article() {
    }

    public Article(int id, String title,ArticleType articleType, byte[] description, MedicalSpecialty specialty, SubSpecialty subSpecialty, Doctor doctor, List<Attachment> attachment,
                   Date createdDate, List<ReferenceLink> referenceLinks, BackOfficeUser user) {
        this.id = id;
        this.title = title;
        this.articleType = articleType;
        this.description = description;
        this.specialty = specialty;
        this.subSpecialty = subSpecialty;
        this.doctor = doctor;
        this.attachment = attachment;
        this.createdDate = createdDate;
        this.referenceLinks = referenceLinks;
        this.user = user;
    }

    public int getId() {return id;}

    public String getTitle() {return title;}

    public byte[] getDescription() {return description;}

    public MedicalSpecialty getSpecialty() {return specialty;}

    public SubSpecialty getSubSpecialty() {return subSpecialty;}

    public Doctor getDoctor() {return doctor;}

    public List<Attachment> getAttachment() {return attachment;}

    public Date getCreatedDate() {return createdDate;}

    public List<ReferenceLink> getReferenceLinks() {return referenceLinks;}

    public ArticleType getTypeArticle() {return articleType;}

    public BackOfficeUser getUser() {return user;}

    public void setId(int id) {this.id = id;}

    public void setTitle(String title) {this.title = title;}

    public void setDescription(byte[] description) {this.description = description;}

    public void setSpecialty(MedicalSpecialty specialty) {this.specialty = specialty;}

    public void setSubSpecialty(SubSpecialty subSpecialty) {this.subSpecialty = subSpecialty;}

    public void setDoctor(Doctor doctor) {this.doctor = doctor;}

    public void setAttachment(List<Attachment> attachment) {this.attachment = attachment;}

    public void setCreatedDate(Date createdDate) {this.createdDate = createdDate;}

    public void setReferenceLinks(List<ReferenceLink> referenceLinks) {this.referenceLinks = referenceLinks;}

    public void setArticleType(ArticleType articleType) {this.articleType = articleType;}

    public void setUser(BackOfficeUser user) {this.user = user;}
}
