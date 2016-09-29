package pt.vejasaude.web.services.featuredArticle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.attachment.IAttachmentRepository;
import pt.vejasaude.unified.data.featuredArticle.FeaturedArticle;
import pt.vejasaude.unified.data.featuredArticle.IFeaturedArticleRepository;
import pt.vejasaude.web.services.featuredArticle.request.CreateNewFeaturedArticleRequest;
import pt.vejasaude.web.services.featuredArticle.response.CreateNewFeaturedArticleResponse;
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
    public StatusResponse<CreateNewFeaturedArticleResponse> createNewFeaturedArticle (
            @RequestBody CreateNewFeaturedArticleRequest request){

        FeaturedArticle featuredArticle = new FeaturedArticle();

        if (request.getDescription() == null)
            return new StatusResponse<CreateNewFeaturedArticleResponse>(Status.NOK, "Preencha os campos obrigatórios");
        else
            featuredArticle.setDescription(request.getDescription().getBytes());

        if (request.getIdAttachments().length > 0) {
            List<Attachment> listAttachment = new ArrayList<Attachment>();
            Attachment attachment = new Attachment();

            for (int i = 0; i < request.getIdAttachments().length; i++) {

                attachment = attachmentRepository.findOne(i);
                listAttachment.add(attachment);
            }
            featuredArticle.setAttachment(listAttachment);
        }
        try{
            featuredArticleRepository.save(featuredArticle);
            CreateNewFeaturedArticleResponse createFeaturedResponse = CreateNewFeaturedArticleResponse.of(featuredArticle);
            return new StatusResponse<CreateNewFeaturedArticleResponse>(Status.OK,null,createFeaturedResponse);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<CreateNewFeaturedArticleResponse>(Status.NOK,null);
        }
    }

}
