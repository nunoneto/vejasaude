package pt.vejasaude.unified.data.CurriculumVitae;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by fmorais on 08/06/2016.
 */
@Entity
@Table
public class CurriculumVitae implements Serializable{


    @Id
    @Column
    private int idCurriculum;


    public CurriculumVitae(int idCurriculum) {
        this.idCurriculum = idCurriculum;
    }
    public int getIdCurriculum() {
        return idCurriculum;
    }

    public void setIdCurriculum(int idCurriculum) {
        this.idCurriculum = idCurriculum;
    }
}
