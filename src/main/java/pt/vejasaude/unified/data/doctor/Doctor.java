package pt.vejasaude.unified.data.doctor;

import pt.vejasaude.unified.data.backofficeuser.CurriculumVitae;
import pt.vejasaude.unified.data.backofficeuser.MedicalSpecialty;
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
    private MedicalSpecialty Specialty;

    @OneToOne
    @JoinColumn(name="idCurriculum")
    private CurriculumVitae Curriculum;

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
        return Specialty;
    }

    public void setSpecialty(MedicalSpecialty specialty) {
        Specialty = specialty;
    }

    public CurriculumVitae getCurriculum() {
        return Curriculum;
    }

    public void setCurriculum(CurriculumVitae curriculum) {
        Curriculum = curriculum;
    }
}
