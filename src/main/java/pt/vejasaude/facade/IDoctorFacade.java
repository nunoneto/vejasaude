package pt.vejasaude.facade;

import pt.vejasaude.unified.data.doctor.Doctor;
import pt.vejasaude.web.services.doctor.request.CreateNewDoctorRequest;

/**
 * Created by fmorais on 15/06/2016.
 */
public interface IDoctorFacade {
    Doctor createDoctor (CreateNewDoctorRequest request);
}
