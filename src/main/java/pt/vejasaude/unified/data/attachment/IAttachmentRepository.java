package pt.vejasaude.unified.data.attachment;

import org.springframework.data.repository.CrudRepository;
import pt.vejasaude.web.services.attachment.request.CreateNewAttachmentRequest;

/**
 * Created by fmorais on 25/08/2016.
 */
public interface IAttachmentRepository extends CrudRepository<Attachment,Integer> {
}
