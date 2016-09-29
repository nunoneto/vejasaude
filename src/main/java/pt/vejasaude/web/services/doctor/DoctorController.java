package pt.vejasaude.web.services.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.vejasaude.facade.doctorFacade.DoctorFacade;
import pt.vejasaude.unified.data.curriculumVitae.CurriculumVitae;
import pt.vejasaude.unified.data.curriculumVitae.ICurriculumVitaeRepository;
import pt.vejasaude.unified.data.medicalSpecialty.IMedicalSpecialtyRepository;
import pt.vejasaude.unified.data.medicalSpecialty.MedicalSpecialty;
import pt.vejasaude.unified.data.doctor.Doctor;
import pt.vejasaude.web.services.doctor.request.CreateNewDoctorRequest;
import pt.vejasaude.web.services.doctor.request.UpdateDoctorRequest;
import pt.vejasaude.web.services.doctor.response.CreateDoctorResponse;
import pt.vejasaude.web.services.doctor.response.UpdateDoctorResponse;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;
import pt.vejasaude.unified.data.doctor.IDoctorRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmorais on 15/06/2016.
 */
@RequestMapping("/api/v1/doctor")
@RestController
public class DoctorController {
    @Autowired
    private DoctorFacade doctorFacade;
    @Autowired
    private IDoctorRepository doctorRep;
    @Autowired
    private IMedicalSpecialtyRepository medicalSpecialtyRep;
    @Autowired
    private ICurriculumVitaeRepository curriculumVitaeRep;

    @RequestMapping(method = RequestMethod.POST)
    public StatusResponse<CreateDoctorResponse> createDoctor(@RequestBody CreateNewDoctorRequest request) {

        if (request.getName().isEmpty())
            return new StatusResponse<>(Status.NOK, "Preencha os campos obrigatórios");

        if (request.getIdSpecialty() == null)
            return new StatusResponse<>(Status.NOK, "ERROR");

        MedicalSpecialty specialty = medicalSpecialtyRep.findOne(request.getIdSpecialty());

        if (specialty == null) {
            return new StatusResponse<CreateDoctorResponse>(Status.NOK, "Especialidade não existe");
        }
        if (request.getIdCurriculum() == null) {
            return new StatusResponse<>(Status.NOK, "ERROR");
        }

        CurriculumVitae curriculum = curriculumVitaeRep.findOne(request.getIdCurriculum());

        if (curriculum == null) {
            return new StatusResponse<CreateDoctorResponse>(Status.NOK, "Curriculo não existe");
        }

        Doctor doctor = doctorFacade.createDoctor(request, specialty, curriculum);
        CreateDoctorResponse createDoctorResponse = CreateDoctorResponse.of(doctor);

        try {
            doctorFacade.createDoctor(request, specialty, curriculum);
            return new StatusResponse<>(Status.OK, null, createDoctorResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return new StatusResponse<>(Status.NOK, null);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public StatusResponse getAll() {
        Iterable<Doctor> doctors = doctorFacade.getAll ();

        List doctorsFinal = new ArrayList<CreateDoctorResponse>();
        for(Doctor d : doctors) {
            doctorsFinal.add(CreateDoctorResponse.of(d));
        }
        return new StatusResponse<>(Status.OK,null,doctorsFinal);
    }

    @RequestMapping (value = "/{id}", method = RequestMethod.POST)
    public StatusResponse<UpdateDoctorResponse> updateDoctor (
            @PathVariable String id,
            @RequestBody UpdateDoctorRequest changes) {
        int idDoctor = Integer.parseInt(id);
        Doctor doctor = doctorRep.findOne(idDoctor);
        if (doctor == null)
            return new StatusResponse(Status.NOK,"A entidade não existe");
        if (changes.getName().isEmpty())
            return new StatusResponse(Status.NOK, "Sem alterações");
        if (!changes.getName().isEmpty())
            doctor.setName(changes.getName());
        if (changes.getIdSpecialty() == null)
            return new StatusResponse<>(Status.NOK, "ERROR");
        CurriculumVitae curriculum = curriculumVitaeRep.findOne(changes.getIdCurriculum());
        if (curriculum != null)
            doctor.setCurriculum(curriculum);
        if (changes.getIdSpecialty() == null)
            return new StatusResponse<>(Status.NOK, "ERROR");
        MedicalSpecialty specialty = medicalSpecialtyRep.findOne(changes.getIdSpecialty());
        if (specialty != null)
            doctor.setSpecialty(specialty);
        doctorFacade.updateDoctor(doctor);
        return new StatusResponse<UpdateDoctorResponse> (Status.NOK, null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public StatusResponse removeUser(@PathVariable String id){
        int idDoctor = Integer.parseInt(id);
        Doctor doctor  = doctorRep.findOne(idDoctor);
        if(doctor == null)
            return new StatusResponse(Status.NOK,"O Médico escolhido não existe");

        try{
            doctorRep.delete(idDoctor);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse(Status.NOK,null);
        }
        return new StatusResponse(Status.OK,"Médico eliminado");
    }


}