package pt.vejasaude.unified.data.MedicalSpecialty;
import pt.vejasaude.web.services.medicalSpecialty.request.CreateNewSpecialtyRequest;
import javax.persistence.*;
import java.io.Serializable;

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
    public MedicalSpecialty() {}

    public MedicalSpecialty(int idSpecialty, String specialty, String description) {
        idSpecialty = idSpecialty;
        specialty = specialty;
    }

    public MedicalSpecialty(CreateNewSpecialtyRequest request) {
        specialty = request.getSpecialty();
    }

    public String getSpecialty() {return specialty;}

    public void setSpecialty(String pSpecialty) {specialty = pSpecialty;}
}
