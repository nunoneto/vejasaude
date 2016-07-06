package pt.vejasaude.unified.data.CurriculumVitae;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fmorais on 08/06/2016.
 */
@Entity
@Table
public class CurriculumVitae implements Serializable{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    public CurriculumVitae() {}

    public CurriculumVitae(int idCurriculum) {
        this.id = idCurriculum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
