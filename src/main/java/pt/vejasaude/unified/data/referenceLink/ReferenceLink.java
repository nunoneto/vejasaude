package pt.vejasaude.unified.data.referenceLink;

import javax.persistence.*;

/**
 * Created by fmorais on 26/10/2016.
 */
@Entity
@Table
public class ReferenceLink {
    @Id
    @Column(name="idReferenceLink")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String referenceLink;

    public ReferenceLink() {
    }

    public ReferenceLink(int id, String referenceLink) {
        this.id = id;
        this.referenceLink = referenceLink;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getReferenceLink() {return referenceLink;}

    public void setReferenceLink(String referenceLink) {this.referenceLink = referenceLink;}
}
