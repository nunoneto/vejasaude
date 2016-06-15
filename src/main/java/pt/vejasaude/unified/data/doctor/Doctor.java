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
    private String username;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="IdSpecialty")
    private MedicalSpecialty Specialty;


    @OneToOne
    @JoinColumn(name="idCurriculum")
    private CurriculumVitae Curriculum;

    public Doctor() {
    }
    public Doctor(String username, String name, MedicalSpecialty specialty, CurriculumVitae curriculum) {
        this.username = username;
        this.name = name;
        Specialty = specialty;
        Curriculum = curriculum;
    }
    /*
    public Doctor(CreateNewDoctorRequest doctor){
        this.username = doctor.getUsername();
        this.name = doctor.getName();
    }
    */

    public String getUsername() {return username;
    }

    public String getName() {return name;
    }

    public MedicalSpecialty getSpecialty() {return Specialty;
    }

    public CurriculumVitae getCurriculum() {return Curriculum;
    }
    public void setUsername(String username) {this.username = username;
    }

    public void setName(String name) {this.name = name;
    }

    public void setCurriculum(CurriculumVitae curriculum) {Curriculum = curriculum;
    }

    public void setSpecialty(MedicalSpecialty specialty) {Specialty = specialty;
    }
}
