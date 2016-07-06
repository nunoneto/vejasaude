package pt.vejasaude.facade;

import pt.vejasaude.unified.data.CurriculumVitae.CurriculumVitae;

/**
 * Created by NB20301 on 06/07/2016.
 */
public interface ICurriculumFacade {

    Iterable<CurriculumVitae> getAll();
}
