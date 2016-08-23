package pt.vejasaude.facade.CurriculumFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.vejasaude.unified.data.CurriculumVitae.CurriculumVitae;
import pt.vejasaude.unified.data.CurriculumVitae.ICurriculumVitaeRepository;
import pt.vejasaude.web.services.curriculumVitae.request.CreateNewCurriculumRequest;

/**
 * Created by fmorais on 19/07/2016.
 */
@Service
public class CurriculumFacade implements ICurriculumFacade {
    @Autowired
    private ICurriculumVitaeRepository curriculumDAO;

    @Override
    public CurriculumVitae createCurriculum(CreateNewCurriculumRequest request) {
        CurriculumVitae newCurriculumVitae = null;
        try {
            newCurriculumVitae = new CurriculumVitae(request);
            curriculumDAO.save(newCurriculumVitae);

        } catch (Exception e){
                e.printStackTrace();
            }
                return newCurriculumVitae;
    }

    @Override
    public Iterable<CurriculumVitae> getAll() {
        return null;
    }
}
