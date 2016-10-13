package pt.vejasaude.web.services.curriculumVitae;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.vejasaude.facade.curriculumFacade.ICurriculumFacade;
import pt.vejasaude.unified.data.curriculumVitae.CurriculumVitae;
import pt.vejasaude.unified.data.curriculumVitae.ICurriculumVitaeRepository;
import pt.vejasaude.web.services.curriculumVitae.request.CreateNewCurriculumRequest;
import pt.vejasaude.web.services.curriculumVitae.request.UpdateCurriculumRequest;
import pt.vejasaude.web.services.curriculumVitae.response.CreateNewCurriculumResponse;
import pt.vejasaude.web.services.curriculumVitae.response.UpdateCurriculumResponse;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;
import pt.vejasaude.web.services.medicalSpecialty.request.UpdateSpecialtyRequest;

/**
 * Created by fmorais on 19/07/2016.
 */
@RequestMapping("/api/v1/curriculum")
@RestController
public class CurriculumController {
    @Autowired
    private ICurriculumFacade curriculumFacade;
    @Autowired
    private ICurriculumVitaeRepository curriculumVitaeRep;

    @RequestMapping (method = RequestMethod.POST)
    public StatusResponse<CreateNewCurriculumResponse> createCurriculum (@RequestBody CreateNewCurriculumRequest request) {
        CurriculumVitae curriculumVitae = null;
        try{
            curriculumVitae = curriculumFacade.createCurriculum(request);
            CreateNewCurriculumResponse createCurriculumResponse = CreateNewCurriculumResponse.of(curriculumVitae);
            return new StatusResponse<CreateNewCurriculumResponse>(Status.OK, null, createCurriculumResponse);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<CreateNewCurriculumResponse>(Status.NOK,null);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public StatusResponse<Iterable<CurriculumVitae>> getAll(){
        return new StatusResponse<>(Status.OK,null,curriculumFacade.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public StatusResponse<UpdateCurriculumResponse> updateCurriculum (
            @PathVariable String id,
            @RequestBody UpdateCurriculumRequest changes){
        int idCurriculum = Integer.parseInt(id);
        CurriculumVitae curriculumVitae = curriculumVitaeRep.findOne(idCurriculum);
        if(curriculumVitae == null)
            return new StatusResponse(Status.NOK,"Curriculum não existe");

        if(changes.getDescription() == null)
            return new StatusResponse(Status.NOK,"");

        try{
            curriculumVitae.setDescription(changes.getDescription().getBytes());
            curriculumVitaeRep.save(curriculumVitae);
            UpdateCurriculumResponse updateCurriculumResponse = UpdateCurriculumResponse.of(curriculumVitae);
            return new StatusResponse<UpdateCurriculumResponse>(Status.OK,"Curriculum Alterado",updateCurriculumResponse);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse(Status.NOK,e.getMessage());
        }
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public StatusResponse removeCurriculum (
            @PathVariable String id){
        int idCurriculum = Integer.parseInt(id);
        CurriculumVitae curriculumVitae = curriculumVitaeRep.findOne(idCurriculum);

        if(curriculumVitae==null)
            return new StatusResponse(Status.OK,"O Curriculum não existe");
        try{
            curriculumVitaeRep.delete(idCurriculum);
            return new StatusResponse(Status.OK,"Curriculum eliminado");
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse(Status.NOK, e.getMessage());
        }
    }
}

