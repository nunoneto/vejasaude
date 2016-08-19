package pt.vejasaude.unified.data.doctor;

import org.springframework.data.repository.CrudRepository;
import pt.vejasaude.unified.data.backofficeuser.BackOfficeUser;

/**
 * Created by fmorais on 08/06/2016.
 */
public interface IDoctorRespository extends CrudRepository<Doctor, Integer> {

}
