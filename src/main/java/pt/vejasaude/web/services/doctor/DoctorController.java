package pt.vejasaude.web.services.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.DocumentDefaultsDefinition;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.vejasaude.facade.DoctorFacade;
import pt.vejasaude.facade.IDoctorFacade;
import pt.vejasaude.unified.data.doctor.Doctor;
import pt.vejasaude.web.services.doctor.request.CreateNewDoctorRequest;
import pt.vejasaude.web.services.doctor.response.CreateDoctorResponse;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;

/**
 * Created by fmorais on 15/06/2016.
 */
@RequestMapping ("/api/v1/doctor")
@RestController
public class DoctorController {
    @Autowired
    private IDoctorFacade doctorFacade;

    @RequestMapping (method = RequestMethod.POST)
    public StatusResponse<CreateDoctorResponse>createDoctor(@RequestBody CreateNewDoctorRequest request){
        Doctor doctor = doctorFacade.createDoctor(request);
        CreateDoctorResponse createDoctorResponse = CreateDoctorResponse.of(doctor);
        return new StatusResponse<CreateDoctorResponse>(Status.OK,null,createDoctorResponse);
    }
}
