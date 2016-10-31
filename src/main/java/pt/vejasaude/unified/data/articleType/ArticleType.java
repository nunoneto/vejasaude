package pt.vejasaude.unified.data.articleType;

import javax.persistence.*;

/**
 * Created by fmorais on 26/10/2016.
 */
@Entity
@Table
public class ArticleType {
    @Id
    @Column(name = "idArticleType")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String articleType;

    public ArticleType() {}

    public ArticleType(int id, String articleType) {
        this.id = id;
        this.articleType = articleType;
    }

    public int getId() {return id;}

    public String getArticleType() {return articleType;}

    public void setId(int id) {this.id = id;}

    public void setArticleType(String articleType) {this.articleType = articleType;}
}
