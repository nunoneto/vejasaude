package pt.vejasaude.unified.data.subSpecialty;

import pt.vejasaude.unified.data.medicalSpecialty.MedicalSpecialty;

import javax.persistence.*;

/**
 * Created by fmorais on 26/10/2016.
 */
@Entity
@Table
public class SubSpecialty {
    @Id
    @Column (name = "idSubSpecialty")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn (name = "idSpecialty")
    private MedicalSpecialty specialty;
    @Column
    private String subSpecialty;

    public SubSpecialty() {
    }

    public SubSpecialty(int id, MedicalSpecialty specialty, String subSpecialty) {
        this.id = id;
        this.specialty = specialty;
        this.subSpecialty = subSpecialty;
    }

    public int getId() {return id;}

    public MedicalSpecialty getSpecialty() {return specialty;}

    public String getSubSpecialty() {return subSpecialty;}

    public void setId(int id) {this.id = id;}

    public void setSpecialty(MedicalSpecialty specialty) {this.specialty = specialty;}

    public void setSubSpecialty(String subSpecialty) {this.subSpecialty = subSpecialty;}
}
