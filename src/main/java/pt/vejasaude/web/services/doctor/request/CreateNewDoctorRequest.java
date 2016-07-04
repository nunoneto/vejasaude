package pt.vejasaude.web.services.doctor.request;

/**
 * Created by fmorais on 15/06/2016.
 */
public class CreateNewDoctorRequest {
    private String username;

    private String name;

    private Integer idSpecialty;

    private Integer idCurriculum;

    public Integer getIdCurriculum() {
        return idCurriculum;
    }

    public void setIdCurriculum(int idCurriculum) {
        this.idCurriculum = idCurriculum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdSpecialty() {
        return idSpecialty;
    }

    public void setIdSpecialty(Integer idSpecialty) {
        this.idSpecialty = idSpecialty;
    }
}
