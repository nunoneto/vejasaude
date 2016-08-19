package pt.vejasaude.unified.data.doctor;

import pt.vejasaude.unified.data.CurriculumVitae.CurriculumVitae;
import pt.vejasaude.unified.data.MedicalSpecialty.MedicalSpecialty;
import pt.vejasaude.web.services.doctor.request.CreateNewDoctorRequest;

import javax.persistence.*;
import javax.print.Doc;
import java.io.Serializable;

/**
 * Created by fmorais on 08/06/2016.
 */
@Entity
@Table
public class Doctor implements Serializable {

    @Id
    @Column(name="idDoctor")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="idSpecialty")
    private MedicalSpecialty specialty;

    @OneToOne
    @JoinColumn(name="idCurriculum")
    private CurriculumVitae curriculum;

    public Doctor(){};


    public Doctor(String name, MedicalSpecialty specialty, CurriculumVitae curriculum) {
        this.name = name;
        this.specialty = specialty;
        this.curriculum = curriculum;
    }
    public Doctor(CreateNewDoctorRequest doctor) {
        this.name = doctor.getName();
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MedicalSpecialty getSpecialty() {
        return specialty;
    }

    public CurriculumVitae getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(CurriculumVitae pCurriculum) {
        curriculum = pCurriculum;
    }

    public void setSpecialty(MedicalSpecialty pSpecialty) {
        specialty = pSpecialty;
    }

}
