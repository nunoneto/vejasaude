package pt.vejasaude.unified.data.backofficeuser;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by NB20301 on 24/05/2016.
 */
@Transactional
public interface IBackOfficeUserRepository extends CrudRepository<BackOfficeUser, String> {

    //implement more queries here first!

    BackOfficeUser findByEmail(String email);

}
