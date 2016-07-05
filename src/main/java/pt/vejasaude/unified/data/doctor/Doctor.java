package pt.vejasaude.unified.data.doctor;

import pt.vejasaude.unified.data.CurriculumVitae.CurriculumVitae;
import pt.vejasaude.unified.data.MedicalSpecialty.MedicalSpecialty;
import pt.vejasaude.web.services.doctor.request.CreateNewDoctorRequest;
import pt.vejasaude.web.services.user.request.CreateNewUserRequest;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fmorais on 08/06/2016.
 */
@Entity
@Table
public class Doctor implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="IdSpecialty")
    private MedicalSpecialty specialty;

    @OneToOne
    @JoinColumn(name="idCurriculum")
    private CurriculumVitae curriculum;

    public Doctor(String name, MedicalSpecialty specialty, CurriculumVitae curriculum) {
        this.name = name;
        this.specialty = specialty;
        this.curriculum = curriculum;
    }
    public Doctor(CreateNewDoctorRequest doctor){
        this.name = doctor.getName();
        //specialty = doctor.getIdSpecialty();
        //curriculum = doctor.getIdCurriculum();
    }
    /*
    public DoctorController(CreateNewDoctorRequest doctor){
        this.username = doctor.getUsername();
        this.name = doctor.getName();
    }
    */
    public int getId() {return id;}

    public String getName() {return name;
    }

    public MedicalSpecialty getSpecialty() {return specialty;
    }

    public CurriculumVitae getCurriculum() {return curriculum;
    }

    public void setName(String name) {this.name = name;
    }

    public void setCurriculum(CurriculumVitae curriculum) {curriculum = curriculum;
    }

    public void setSpecialty(MedicalSpecialty specialty) {specialty = specialty;
    }
}
