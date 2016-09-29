package pt.vejasaude.web.services.generalArticle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.attachment.IAttachmentRepository;
import pt.vejasaude.unified.data.generalArticle.GeneralArticle;
import pt.vejasaude.unified.data.generalArticle.IGereralArticleRepository;
import pt.vejasaude.web.services.attachment.response.CreateNewAttachmentResponse;
import pt.vejasaude.web.services.generalArticle.request.CreateNewGeneralArticleRequest;
import pt.vejasaude.web.services.generalArticle.response.CreateNewGeneralArticleResponse;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmorais on 28/09/2016.
 */
@RequestMapping("api/v1/generalArticle")
@RestController
public class GeneralArticleController {
    @Autowired
    private IGereralArticleRepository gereralArticleRepository;
    @Autowired
    private IAttachmentRepository attachmentRepository;
    @RequestMapping(method = RequestMethod.POST)
    public StatusResponse<CreateNewGeneralArticleResponse> createGeneralArticle(@RequestBody CreateNewGeneralArticleRequest request)
    {
        GeneralArticle generalArticle = new GeneralArticle();


        if (request.getDescription() == null)
            return new StatusResponse<>(Status.NOK, "Preencha os campos obrigatórios");
        else
            generalArticle.setDescription(request.getDescription().getBytes());

        if (request.getListIdAttachments().length > 0) {
            List<Attachment> listAttachment = new ArrayList<Attachment>();
            for (int att:request.getListIdAttachments()) {
                Attachment attachment = attachmentRepository.findOne(att);
                listAttachment.add(attachment);
            }
            generalArticle.setAttachment(listAttachment);
        }

        try{
            gereralArticleRepository.save(generalArticle);
            CreateNewGeneralArticleResponse createGeneralArticleResponse = CreateNewGeneralArticleResponse.of(generalArticle);
            return new StatusResponse<CreateNewGeneralArticleResponse>(Status.OK,null,createGeneralArticleResponse);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<CreateNewGeneralArticleResponse>(Status.NOK,null);
        }
    }
}
