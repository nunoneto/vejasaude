package pt.vejasaude.web.services.featuredArticle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.attachment.IAttachmentRepository;
import pt.vejasaude.unified.data.featuredArticle.FeaturedArticle;
import pt.vejasaude.unified.data.featuredArticle.IFeaturedArticleRepository;
import pt.vejasaude.web.services.featuredArticle.request.CreateFeaturedArticleRequest;
import pt.vejasaude.web.services.featuredArticle.request.UpdateFeaturedArticleRequest;
import pt.vejasaude.web.services.featuredArticle.response.CreateFeaturedArticleResponse;
import pt.vejasaude.web.services.featuredArticle.response.UpdateFeaturedArticleResponse;
import pt.vejasaude.web.services.generalArticle.response.UpdateGeneralArticleResponse;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmorais on 30/08/2016.
 */
@RequestMapping("/api/v1/featuredArticle")
@RestController
public class FeaturedArticleController {
    @Autowired
    private IFeaturedArticleRepository  featuredArticleRepository;
    @Autowired
    private IAttachmentRepository attachmentRepository;

    @RequestMapping(method = RequestMethod.POST)
    public StatusResponse<CreateFeaturedArticleResponse> createNewFeaturedArticle (
            @RequestBody CreateFeaturedArticleRequest request){

        FeaturedArticle featuredArticle = new FeaturedArticle();

        if (request.getDescription() == null)
            return new StatusResponse<CreateFeaturedArticleResponse>(Status.NOK, "Preencha os campos obrigatórios");
        else
            featuredArticle.setDescription(request.getDescription().getBytes());

        if (request.getlistIdAttachments().length > 0) {
            List<Attachment> listAttachment = new ArrayList<Attachment>();
            for (int att:request.getlistIdAttachments()) {
                Attachment attachment = attachmentRepository.findOne(att);
                listAttachment.add(attachment);
            }
                featuredArticle.setAttachment(listAttachment);
        }
        try{
            featuredArticleRepository.save(featuredArticle);
            CreateFeaturedArticleResponse createFeaturedResponse = CreateFeaturedArticleResponse.of(featuredArticle);
            return new StatusResponse<CreateFeaturedArticleResponse>(Status.OK,"Criação efetuada com Sucesso",createFeaturedResponse);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<CreateFeaturedArticleResponse>(Status.NOK,null);
        }
    }

    @RequestMapping (value = "/{id}")
    public StatusResponse findOne(
            @PathVariable String id)
    {
        int idArticle = Integer.parseInt(id);
        FeaturedArticle featuredArticle = featuredArticleRepository.findOne(idArticle);

        UpdateFeaturedArticleResponse updateFeaturedArticleResponse = UpdateFeaturedArticleResponse.of(featuredArticle);
        return new StatusResponse<UpdateFeaturedArticleResponse>(Status.OK,"Curriculo:",updateFeaturedArticleResponse);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public StatusResponse<UpdateFeaturedArticleResponse> updateFeaturedArticle (
            @PathVariable String id,
            @RequestBody UpdateFeaturedArticleRequest request){

        int idArticle = Integer.parseInt(id);
        FeaturedArticle featuredArticle = featuredArticleRepository.findOne(idArticle);

        if (featuredArticle == null)
            return new StatusResponse (Status.NOK,"O artigo selecionado não existe");

        featuredArticle.setDescription(request.getDescription().getBytes());
        if (request.getlistIdAttachments().length > 0) {
            List<Attachment> listAttachment = new ArrayList<Attachment>();
            for (int att:request.getlistIdAttachments()) {
                Attachment attachment = attachmentRepository.findOne(att);
                listAttachment.add(attachment);
            }
            featuredArticle.setAttachment(listAttachment);
        }else
        {
            featuredArticle.setAttachment(null);
        }
        try{
            featuredArticleRepository.save(featuredArticle);
            UpdateFeaturedArticleResponse updateFeaturedArticleRequest = UpdateFeaturedArticleResponse.of(featuredArticle);
            return new StatusResponse<UpdateFeaturedArticleResponse>(Status.OK,"Alteração efetuada com sucesso.",updateFeaturedArticleRequest);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<UpdateFeaturedArticleResponse>(Status.NOK,null);
        }
    }

}
