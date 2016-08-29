package pt.vejasaude.web.services.attachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.vejasaude.unified.data.attachment.IAttachmentRepository;
import pt.vejasaude.web.services.attachment.request.CreateNewAttachmentRequest;
import pt.vejasaude.web.services.attachment.response.CreateNewAttachmentResponse;
import pt.vejasaude.web.services.generic.StatusResponse;

/**
 * Created by fmorais on 25/08/2016.
 */
@RequestMapping("/api/v1/attachment")
@RestController
public class attachmentController {
    //@Autowired
    //private IAttachmentRepository attachmentRepository;

    @RequestMapping(method = RequestMethod.POST)
    public StatusResponse<CreateNewAttachmentResponse> createAttachment (
            @RequestBody CreateNewAttachmentRequest request) {

        try {
            //attachmentRepository.save(request);
        }catch (Exception e){
            e.printStackTrace();
        }
        //CreateNewAttachmentResponse
        return null;
    }
}
