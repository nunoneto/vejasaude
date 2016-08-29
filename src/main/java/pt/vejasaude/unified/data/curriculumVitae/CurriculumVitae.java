package pt.vejasaude.unified.data.curriculumVitae;

import pt.vejasaude.web.services.curriculumVitae.request.CreateNewCurriculumRequest;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fmorais on 08/06/2016.
 */
@Entity
@Table
public class CurriculumVitae implements Serializable{
    @Id
    @Column(name="idCurriculum")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private byte[] description;
    public CurriculumVitae(CreateNewCurriculumRequest request){
        this.description = request.getDescription();
    }

    public CurriculumVitae() {}

    public CurriculumVitae(int idCurriculum) {
        this.id = idCurriculum;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getDescription() {return description;}

    public void setDescription(byte[] description) {this.description = description;}


}
