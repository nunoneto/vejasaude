package pt.vejasaude.web.services.curriculum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.vejasaude.facade.CurriculumFacade;
import pt.vejasaude.unified.data.CurriculumVitae.CurriculumVitae;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;

/**
 * Created by NB20301 on 06/07/2016.
 */
@Controller
@RequestMapping("/api/v1/curriculum")
public class CurriculumController {

    @Autowired
    private CurriculumFacade curriculumFacade;

    @RequestMapping(method = RequestMethod.GET)
    public StatusResponse<Iterable<CurriculumVitae>> getAlll(){
        return new StatusResponse<>(Status.OK,null,curriculumFacade.getAll());
    }

}
