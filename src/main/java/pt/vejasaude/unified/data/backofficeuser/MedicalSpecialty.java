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
public class MedicalSpecialty implements Serializable {

    @Id
    @Column
    private int IdSpecialty;

    @Column
    private String Specialty;

    @Column
    private String Description;

    public MedicalSpecialty(int idSpecialty, String specialty, String description) {
        IdSpecialty = idSpecialty;
        Specialty = specialty;
        Description = description;
    }
    public int getIdSpecialty() {return IdSpecialty;
    }

    public void setIdSpecialty(int idSpecialty) {IdSpecialty = idSpecialty;
    }

    public String getSpecialty() {return Specialty;
    }

    public void setSpecialty(String specialty) {Specialty = specialty;
    }

    public String getDescription() {return Description;
    }

    public void setDescription(String description) {Description = description;
    }

}
