package pt.vejasaude.facade.curriculumFacade;

import pt.vejasaude.unified.data.curriculumVitae.CurriculumVitae;
import pt.vejasaude.web.services.curriculumVitae.request.CreateNewCurriculumRequest;

/**
 * Created by fmorais on 19/07/2016.
 */
public interface ICurriculumFacade {
    CurriculumVitae createCurriculum (CreateNewCurriculumRequest request);
    Iterable<CurriculumVitae> getAll();
}
