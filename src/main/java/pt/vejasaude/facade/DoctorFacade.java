package pt.vejasaude.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.vejasaude.unified.data.backofficeuser.CurriculumVitae;
import pt.vejasaude.unified.data.backofficeuser.MedicalSpecialty;
import pt.vejasaude.unified.data.doctor.Doctor;
import pt.vejasaude.unified.data.doctor.IDoctor;
import pt.vejasaude.web.services.doctor.request.CreateNewDoctorRequest;

/**
 * Created by fmorais on 15/06/2016.
 */
@Service
public class DoctorFacade implements IDoctorFacade{
    @Autowired
    private IDoctor doctorDAO;

    @Override
    public Doctor createDoctor(CreateNewDoctorRequest request, MedicalSpecialty speciality, CurriculumVitae curriculum) {
        Doctor newDoctor = null;
        try{

            newDoctor = new Doctor(request.getName(),speciality,curriculum);
            doctorDAO.save(newDoctor);

        }catch (Exception e){
            e.printStackTrace();
        }
        return newDoctor;
    }
}
