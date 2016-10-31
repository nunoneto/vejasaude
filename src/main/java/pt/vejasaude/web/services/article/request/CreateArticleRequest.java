package pt.vejasaude.web.services.article.request;

import java.util.Date;

/**
 * Created by fmorais on 28/09/2016.
 */
public class CreateArticleRequest {
    private Integer typeArticle;
    private String title;
    private String description;
    private Integer specialty,subSpecialty,author;
    private int[] listIdAttachments;
    private Date createdDate;
    private int [] referenceLinks;

    public Integer getTypeArticle() {return typeArticle;}
    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public Integer getSpecialty() {return specialty;}
    public Integer getSubSpecialty() {return subSpecialty;}
    public Integer getAuthor() {return author;}
    public int[] getListIdAttachments() {return listIdAttachments;}
    public Date getCreatedDate() {return createdDate;}
    public int[] getReferenceLinks() {return referenceLinks;}
}
