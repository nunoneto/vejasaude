package pt.vejasaude.web.services.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.vejasaude.facade.IDoctorFacade;
import pt.vejasaude.unified.data.CurriculumVitae.CurriculumVitae;
import pt.vejasaude.unified.data.CurriculumVitae.ICurriculumVitaeRepository;
import pt.vejasaude.unified.data.MedicalSpecialty.IMedicalSpecialtyRepository;
import pt.vejasaude.unified.data.MedicalSpecialty.MedicalSpecialty;
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
    @Autowired
    private IMedicalSpecialtyRepository medicalSpecialty;
    @Autowired
    private ICurriculumVitaeRepository curriculumVitae;

    @RequestMapping(method = RequestMethod.POST)
    public StatusResponse<CreateDoctorResponse> createDoctor(@RequestBody CreateNewDoctorRequest request) {


        if (request.getName().isEmpty())
            return new StatusResponse<>(Status.NOK, "Preencha os campos obrigatórios");

        if (request.getIdSpecialty() == null)
            return new StatusResponse<>(Status.NOK, "ERROR");

        MedicalSpecialty specialty = medicalSpecialty.findOne(request.getIdSpecialty());
        if (specialty != null) {
            return new StatusResponse<>(Status.NOK, "Especialidade não existe");
        }
        if (request.getIdCurriculum() == null) {
            return new StatusResponse<>(Status.NOK, "ERROR");
        }

        CurriculumVitae curriculum = curriculumVitae.findOne(request.getIdCurriculum());

        if (curriculum != null) {
            return new StatusResponse<>(Status.NOK, "Curriculo não existe");
        }

        Doctor doctor = doctorFacade.createDoctor(request,specialty,curriculum);
        CreateDoctorResponse createDoctorResponse = CreateDoctorResponse.of(doctor);

        try {
            doctorFacade.createDoctor(request,specialty,curriculum);
            return new StatusResponse<>(Status.OK, null, createDoctorResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return new StatusResponse<>(Status.NOK, null);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public StatusResponse<Iterable<Doctor>> getAll(){
        Iterable doctors = doctorFacade.getAll();
        return new StatusResponse<>(Status.OK,null,doctors);
    }

}