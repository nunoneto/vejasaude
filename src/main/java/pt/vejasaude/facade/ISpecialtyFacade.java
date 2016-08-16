package pt.vejasaude.facade;

import pt.vejasaude.unified.data.MedicalSpecialty.MedicalSpecialty;
import pt.vejasaude.web.services.medicalSpecialty.request.CreateNewSpecialtyRequest;

/**
 * Created by fmorais on 05/07/2016.
 */
public interface ISpecialtyFacade {
    MedicalSpecialty createSpecialty (CreateNewSpecialtyRequest request);
}
