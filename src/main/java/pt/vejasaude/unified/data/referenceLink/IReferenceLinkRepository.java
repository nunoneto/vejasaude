package pt.vejasaude.unified.data.referenceLink;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

/**
 * Created by fmorais on 26/10/2016.
 */
public interface IReferenceLinkRepository extends CrudRepository<ReferenceLink,Integer>{
}
