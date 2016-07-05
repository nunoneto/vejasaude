package pt.vejasaude.unified.data.backofficeuser;

import pt.vejasaude.web.services.MedicalSpecialty.request.CreateNewSpecialtyRequest;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fmorais on 08/06/2016.
 */
@Entity
@Table
public class MedicalSpecialty implements Serializable {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String specialty;

    public MedicalSpecialty(int idSpecialty, String specialty, String description) {
        idSpecialty = idSpecialty;
        specialty = specialty;
    }

    public MedicalSpecialty(CreateNewSpecialtyRequest request) {
        id = request.getId();
        specialty = request.getSpecialty();
    }

    public int getId() {
        return id;
    }

    public String getSpecialty() {return specialty;}

    public void setSpecialty(String specialty) {specialty = specialty;}
}
