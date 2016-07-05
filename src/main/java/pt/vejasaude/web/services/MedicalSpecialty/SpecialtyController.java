package pt.vejasaude.web.services.MedicalSpecialty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.vejasaude.facade.ISpecialtyFacade;
import pt.vejasaude.unified.data.MedicalSpecialty.MedicalSpecialty;
import pt.vejasaude.web.services.MedicalSpecialty.request.CreateNewSpecialtyRequest;
import pt.vejasaude.web.services.MedicalSpecialty.response.CreateNewSpecialtyResponse;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;

/**
 * Created by fmorais on 05/07/2016.
 */
@RequestMapping ("/api/v1/specialty")
@RestController
public class SpecialtyController {
    @Autowired
    private ISpecialtyFacade specialtyFacade;

    @RequestMapping(method = RequestMethod.POST)
    public StatusResponse<CreateNewSpecialtyResponse> createSpecialty (@RequestBody CreateNewSpecialtyRequest request){
        if (request.getSpecialty().isEmpty()) {
            return new StatusResponse<CreateNewSpecialtyResponse>(Status.NOK, "Preencha os campos obrigatórios");
        }
        MedicalSpecialty specialty = specialtyFacade.createSpecialty(request);
        CreateNewSpecialtyResponse createSpecialtyResponse = CreateNewSpecialtyResponse.of(specialty);
        try {
            specialtyFacade.createSpecialty(request);
            return new StatusResponse<CreateNewSpecialtyResponse>(Status.OK, null, createSpecialtyResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return new StatusResponse<CreateNewSpecialtyResponse>(Status.NOK, null);
        }
    }
}
