package pt.vejasaude.web.services.medicalSpecialty.request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.vejasaude.facade.ISpecialtyFacade;
import pt.vejasaude.unified.data.MedicalSpecialty.MedicalSpecialty;
import pt.vejasaude.web.services.generic.StatusResponse;
import pt.vejasaude.web.services.medicalSpecialty.request.CreateNewSpecialtyRequest;
import pt.vejasaude.web.services.medicalSpecialty.response.CreateNewSpecialtyResponse;

/**
 * Created by fmorais on 16/08/2016.
 */
@RequestMapping ("/api/v1/specialty")
public class CreateNewSpecialtyRequest {

    private String specialty;

    public String getSpecialty() {return specialty;}

    public void setSpecialty(String specialty) {this.specialty = specialty;}




}
