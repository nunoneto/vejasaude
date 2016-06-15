package pt.vejasaude.facade;

import org.springframework.beans.factory.annotation.Autowired;
import pt.vejasaude.unified.data.doctor.Doctor;
import pt.vejasaude.unified.data.doctor.IDoctor;
import pt.vejasaude.web.services.doctor.request.CreateNewDoctorRequest;

/**
 * Created by fmorais on 15/06/2016.
 */
public class DoctorFacade implements IDoctorFacade{
    @Autowired
    private IDoctor doctorDAO;

    @Override
    public Doctor createDoctor(CreateNewDoctorRequest request) {
        return null;
    }
}
