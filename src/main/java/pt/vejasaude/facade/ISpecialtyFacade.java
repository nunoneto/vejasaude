package pt.vejasaude.facade;

import pt.vejasaude.unified.data.MedicalSpecialty.MedicalSpecialty;
import pt.vejasaude.web.services.speciality.request.CreateNewSpecialtyRequest;

/**
 * Created by fmorais on 05/07/2016.
 */
public interface ISpecialtyFacade {

    MedicalSpecialty createSpecialty (CreateNewSpecialtyRequest request);

    Iterable<MedicalSpecialty> getAll();
}
