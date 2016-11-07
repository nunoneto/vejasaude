package pt.vejasaude.web.services.article.request;

import pt.vejasaude.web.services.referenceLink.request.ReferenceLinkRequest;

import java.util.Date;
import java.util.List;

/**
 * Created by fmorais on 28/09/2016.
 */
public class CreateArticleRequest {
    private Integer typeArticle;
    private String title;
    private String description;
    private Integer specialty,subSpecialty,doctor;
    private int[] listIdAttachments;
    private List<ReferenceLinkRequest> referenceLinks;

    public Integer getTypeArticle() {return typeArticle;}
    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public Integer getSpecialty() {return specialty;}
    public Integer getSubSpecialty() {return subSpecialty;}
    public int[] getListIdAttachments() {return listIdAttachments;}
    public List<ReferenceLinkRequest> getReferenceLinks() {return referenceLinks;}

    public Integer getDoctor() {
        return doctor;
    }
}
