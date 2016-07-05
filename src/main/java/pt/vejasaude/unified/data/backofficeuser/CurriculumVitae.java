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
    private int id;

    @Column
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CurriculumVitae(int idCurriculum) {
        this.id = idCurriculum;
    }
}
