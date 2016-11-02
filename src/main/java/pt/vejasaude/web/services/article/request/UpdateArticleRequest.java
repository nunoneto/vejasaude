package pt.vejasaude.web.services.article.request;

import java.util.Date;

/**
 * Created by fmorais on 11/10/2016.
 */
public class UpdateArticleRequest {
    private Integer typeArticle;
    private String title;
    private String description;
    private int specialty;
    private int subSpecialty;
    private int author;
    private int[] listIdAttachments;
    private Date createdDate;
    private String [] referenceLinks;
    private String user;
}
