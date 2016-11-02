package pt.vejasaude.unified.data.medicalSpecialty;
import pt.vejasaude.unified.data.subSpecialty.SubSpecialty;
import pt.vejasaude.web.services.medicalSpecialty.request.CreateNewSpecialtyRequest;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by fmorais on 08/06/2016.
 */
@Entity
@Table
public class MedicalSpecialty implements Serializable {


    @Id
    @Column(name="idSpecialty")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String specialty;

    @JoinColumn(name = "idSubSpecialty")
    @OneToMany
    private List<SubSpecialty> subSpecialties;

    public MedicalSpecialty() {}

    public MedicalSpecialty(int idSpecialty, String specialty, String description) {
        idSpecialty = idSpecialty;
        specialty = specialty;
    }

    public MedicalSpecialty(CreateNewSpecialtyRequest request) {specialty = request.getSpecialty();
    }
    public int getId() {return id;}

    public String getSpecialty() {return specialty;}

    public void setSpecialty(String pSpecialty) {specialty = pSpecialty;}

    public List<SubSpecialty> getSubSpecialties() {
        return subSpecialties;
    }

    public void setSubSpecialties(List<SubSpecialty> subSpecialties) {
        this.subSpecialties = subSpecialties;
    }
}
