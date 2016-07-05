package pt.vejasaude.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.vejasaude.unified.data.MedicalSpecialty.IMedicalSpecialty;
import pt.vejasaude.unified.data.MedicalSpecialty.MedicalSpecialty;
import pt.vejasaude.web.services.MedicalSpecialty.request.CreateNewSpecialtyRequest;

/**
 * Created by fmorais on 05/07/2016.
 */
@Service
public class SpecialtyFacade implements ISpecialtyFacade{
    @Autowired
    private IMedicalSpecialty specialtyDAO;

    @Override
    public MedicalSpecialty createSpecialty(CreateNewSpecialtyRequest request) {
        MedicalSpecialty newMedicalSpecialty = null;
        try{
            newMedicalSpecialty = new MedicalSpecialty(request);
            specialtyDAO.save(newMedicalSpecialty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newMedicalSpecialty;
    }
}
