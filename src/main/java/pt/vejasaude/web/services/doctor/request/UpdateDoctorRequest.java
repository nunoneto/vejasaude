package pt.vejasaude.web.services.doctor.request;

/**
 * Created by fmorais on 26/07/2016.
 */
public class UpdateDoctorRequest
{
    private String name;

    private Integer idSpecialty, idCurriculum;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Integer getIdSpecialty() {return idSpecialty;}

    public void setIdSpecialty(Integer idSpecialty) {this.idSpecialty = idSpecialty;}

    public Integer getIdCurriculum() {return idCurriculum;}

    public void setIdCurriculum(Integer idCurriculum) {this.idCurriculum = idCurriculum;}
}
