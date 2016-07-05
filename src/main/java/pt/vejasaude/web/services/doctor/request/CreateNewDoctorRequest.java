package pt.vejasaude.web.services.doctor.request;

import pt.vejasaude.unified.data.backofficeuser.CurriculumVitae;
import pt.vejasaude.unified.data.backofficeuser.MedicalSpecialty;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

/**
 * Created by fmorais on 15/06/2016.
 */
public class CreateNewDoctorRequest {

    private String name;

    private int idSpecialty;

    private int idCurriculum;

    public int getIdCurriculum() {
        return idCurriculum;
    }

    public void setIdCurriculum(int idCurriculum) {
        this.idCurriculum = idCurriculum;
    }

    public String getName() { return name;}

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdSpecialty() {return idSpecialty;}

    public void setIdSpecialty(int idSpecialty) {
        this.idSpecialty = idSpecialty;
    }
}
