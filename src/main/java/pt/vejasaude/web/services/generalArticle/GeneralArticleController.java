package pt.vejasaude.web.services.generalArticle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.attachment.IAttachmentRepository;
import pt.vejasaude.unified.data.generalArticle.GeneralArticle;
import pt.vejasaude.unified.data.generalArticle.IGereralArticleRepository;
import pt.vejasaude.web.services.generalArticle.request.CreateNewGeneralArticleRequest;
import pt.vejasaude.web.services.generalArticle.request.UpdateGeneralArticleRequest;
import pt.vejasaude.web.services.generalArticle.response.CreateGeneralArticleResponse;
import pt.vejasaude.web.services.generalArticle.response.UpdateGeneralArticleResponse;
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
    public StatusResponse<CreateGeneralArticleResponse> createGeneralArticle(@RequestBody CreateNewGeneralArticleRequest request)
    {
        GeneralArticle generalArticle = new GeneralArticle();


        if (request.getDescription() == null)
            return new StatusResponse<>(Status.NOK, "Preencha os campos obrigatórios");

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
            CreateGeneralArticleResponse createGeneralArticleResponse = CreateGeneralArticleResponse.of(generalArticle);
            return new StatusResponse<CreateGeneralArticleResponse>(Status.OK,null,createGeneralArticleResponse);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<CreateGeneralArticleResponse>(Status.NOK,null);
        }
    }
    @RequestMapping (value = "/{id}")
    public StatusResponse findOne(
            @PathVariable String id)
    {
        int idArticle = Integer.parseInt(id);
        GeneralArticle generalArticle = gereralArticleRepository.findOne(idArticle);

        UpdateGeneralArticleResponse updateGeneralArticleResponse = UpdateGeneralArticleResponse.of(generalArticle);
        return new StatusResponse<UpdateGeneralArticleResponse>(Status.OK,null,updateGeneralArticleResponse);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public StatusResponse <UpdateGeneralArticleResponse> updateGeneralArticle (
            @PathVariable String id,
            @RequestBody UpdateGeneralArticleRequest request) {
        int idArticle = Integer.parseInt(id);
        GeneralArticle generalArticle = gereralArticleRepository.findOne(idArticle);

        if (generalArticle == null)
            return new StatusResponse(Status.NOK,"O artigo selecionado não existe");


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
            UpdateGeneralArticleResponse updateGeneralArticleResponse = UpdateGeneralArticleResponse.of(generalArticle);
            return new StatusResponse<UpdateGeneralArticleResponse>(Status.OK,null,updateGeneralArticleResponse);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<UpdateGeneralArticleResponse>(Status.NOK,null);

        }

    }


}
