package pt.vejasaude.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.vejasaude.unified.data.CurriculumVitae.CurriculumVitae;
import pt.vejasaude.unified.data.CurriculumVitae.ICurriculumVitaeRepository;

/**
 * Created by NB20301 on 06/07/2016.
 */
@Service
public class CurriculumFacade implements ICurriculumFacade {

    @Autowired
    private ICurriculumVitaeRepository curriculumDAO;

    @Override
    public Iterable<CurriculumVitae> getAll() {
        return curriculumDAO.findAll();
    }
}
