package pt.vejasaude.unified.data.backofficeuser;

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
}
