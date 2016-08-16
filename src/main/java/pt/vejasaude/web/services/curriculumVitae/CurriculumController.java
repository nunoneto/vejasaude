package pt.vejasaude.web.services.curriculumVitae;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.vejasaude.facade.CurriculumFacade;
import pt.vejasaude.facade.ICurriculumFacade;
import pt.vejasaude.unified.data.CurriculumVitae.CurriculumVitae;
import pt.vejasaude.web.services.curriculumVitae.request.CreateNewCurriculumRequest;
import pt.vejasaude.web.services.curriculumVitae.response.CreateNewCurriculumResponse;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;

/**
 * Created by fmorais on 19/07/2016.
 */
@RequestMapping("/api/v1/curriculum")
@RestController
public class CurriculumController {
    @Autowired
    private ICurriculumFacade curriculumFacade;

    @RequestMapping (method = RequestMethod.POST)
    public StatusResponse<CreateNewCurriculumResponse> createCurriculum (@RequestBody CreateNewCurriculumRequest request) {
        CurriculumVitae curriculumVitae = curriculumFacade.createCurriculum(request);
        CreateNewCurriculumResponse createCurriculumResponse = CreateNewCurriculumResponse.of(curriculumVitae);
        try{
            curriculumFacade.createCurriculum(request);
            return new StatusResponse<CreateNewCurriculumResponse>(Status.OK, null, createCurriculumResponse);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<CreateNewCurriculumResponse>(Status.NOK,null);
        }
    }

}
