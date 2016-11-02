package pt.vejasaude.web.services.medicalSpecialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.vejasaude.facade.specialtyFacade.ISpecialtyFacade;
import pt.vejasaude.unified.data.medicalSpecialty.IMedicalSpecialtyRepository;
import pt.vejasaude.unified.data.medicalSpecialty.MedicalSpecialty;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;
import pt.vejasaude.web.services.medicalSpecialty.request.CreateNewSpecialtyRequest;
import pt.vejasaude.web.services.medicalSpecialty.request.UpdateSpecialtyRequest;
import pt.vejasaude.web.services.medicalSpecialty.response.CreateNewSpecialtyResponse;
import pt.vejasaude.web.services.medicalSpecialty.response.UpdateSpecialtyResponse;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by fmorais on 16/08/2016.
 */
@RequestMapping ("/api/v1/specialty")
@RestController
public class SpecialtyController {
    @Autowired
    private ISpecialtyFacade specialtyFacade;
    @Autowired
    private IMedicalSpecialtyRepository medicalSpecialtyRep;

    @RequestMapping(method = RequestMethod.POST)
    public StatusResponse<CreateNewSpecialtyResponse> createSpecialty (@RequestBody CreateNewSpecialtyRequest request) {
        if (request.getSpecialty().isEmpty()) {
            return new StatusResponse<CreateNewSpecialtyResponse>(Status.NOK, "Preencha os campos obrigatórios");
        }
        try {
            MedicalSpecialty specialty = specialtyFacade.createSpecialty(request);
            CreateNewSpecialtyResponse createSpecialtyResponse = CreateNewSpecialtyResponse.of(specialty);
            return new StatusResponse<CreateNewSpecialtyResponse>(Status.OK, null, createSpecialtyResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return new StatusResponse<CreateNewSpecialtyResponse>(Status.NOK, null);
        }
    }
    @RequestMapping(method = RequestMethod.GET)
    public StatusResponse<Iterable<CreateNewSpecialtyResponse>> getAll(){

        List<CreateNewSpecialtyResponse> specialtyResponse = StreamSupport.stream(specialtyFacade.getAll().spliterator(),false)
                .map(new Function<MedicalSpecialty, CreateNewSpecialtyResponse>() {
                    @Override
                    public CreateNewSpecialtyResponse apply(MedicalSpecialty medicalSpecialty) {
                        return CreateNewSpecialtyResponse.of(medicalSpecialty);
                    }
                }).collect(Collectors.toList());

        return new StatusResponse<>(Status.OK,null,specialtyResponse);
    }

    @RequestMapping (value = "/{id}", method = RequestMethod.POST)
    public StatusResponse<UpdateSpecialtyResponse> updateSpecialty (
            @PathVariable String id,
            @RequestBody UpdateSpecialtyRequest changes){
        int idSpecialty = Integer.parseInt(id);
        MedicalSpecialty  specialty = medicalSpecialtyRep.findOne(idSpecialty);
        if (specialty == null)
            return new StatusResponse(Status.NOK,"A especialidade não existe");
        if (changes.getSpecialty().isEmpty())
            return new StatusResponse(Status.NOK,"Sem alterações");
        else
            specialty.setSpecialty(changes.getSpecialty());
        specialtyFacade.updateSpecialty(specialty);
        UpdateSpecialtyResponse updateSpecialtyResponse = UpdateSpecialtyResponse.of(specialty);
        return new StatusResponse<UpdateSpecialtyResponse>(Status.OK,"Alteração efetuada com sucesso",updateSpecialtyResponse);
    }

     @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
     public StatusResponse removeSpecialty (@PathVariable String id) {
         int idSpecialty = Integer.parseInt(id);
         MedicalSpecialty specialty = medicalSpecialtyRep.findOne(idSpecialty);
         if (specialty == null)
             return new StatusResponse(Status.NOK,"A especialidade escolhida não existe");
         try{
             medicalSpecialtyRep.delete(idSpecialty);
             return new StatusResponse(Status.OK,"Especialidade eliminada");
         }catch (Exception e){
             e.printStackTrace();
             return new StatusResponse(Status.NOK,e.getMessage());
         }
     }
}
