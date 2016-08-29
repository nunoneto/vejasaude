package pt.vejasaude.facade.specialtyFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.vejasaude.unified.data.medicalSpecialty.IMedicalSpecialtyRepository;
import pt.vejasaude.unified.data.medicalSpecialty.MedicalSpecialty;
import pt.vejasaude.web.services.medicalSpecialty.request.CreateNewSpecialtyRequest;


/**
 * Created by fmorais on 05/07/2016.
 */
@Service
public class SpecialtyFacade implements ISpecialtyFacade {
    @Autowired
    private IMedicalSpecialtyRepository specialtyDAO;

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

    @Override
    public Iterable<MedicalSpecialty> getAll() {
        return specialtyDAO.findAll();
    }

    @Override
    public MedicalSpecialty updateSpecialty(MedicalSpecialty changes) {

        try{
            specialtyDAO.save(changes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return changes;
    }
}
