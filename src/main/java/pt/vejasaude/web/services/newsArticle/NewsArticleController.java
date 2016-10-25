package pt.vejasaude.web.services.newsArticle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.attachment.IAttachmentRepository;
import pt.vejasaude.unified.data.newsArticle.INewsArticleRepository;
import pt.vejasaude.unified.data.newsArticle.NewsArticle;
import pt.vejasaude.web.services.attachment.response.CreateNewAttachmentResponse;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;
import pt.vejasaude.web.services.newsArticle.request.CreateNewsArticleRequest;
import pt.vejasaude.web.services.newsArticle.request.UpdateNewsArticleRequest;
import pt.vejasaude.web.services.newsArticle.response.CreateNewsArticleResponse;
import pt.vejasaude.web.services.newsArticle.response.UpdateNewsArticleResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmorais on 18/10/2016.
 */
@RequestMapping("/api/v1/newsArticle")
@RestController
public class NewsArticleController {
    @Autowired
    private INewsArticleRepository newsArticleRepository;
    @Autowired
    private IAttachmentRepository attachmentRepository;

    @RequestMapping(method = RequestMethod.POST)
    public StatusResponse<CreateNewsArticleResponse> createNewsArticle (
            @RequestBody CreateNewsArticleRequest request) {

        NewsArticle newsArticle = new NewsArticle();
        if (request.getDescription() == null)
            return new StatusResponse<CreateNewsArticleResponse>(Status.NOK, "Preencha os campos obrigatórios");
        else
            newsArticle.setDescription(request.getDescription().getBytes());

        if (request.getlistIdAttachments().length > 0) {
            List<Attachment> listAttachment = new ArrayList<Attachment>();
            for (int att:request.getlistIdAttachments()) {
                Attachment attachment = attachmentRepository.findOne(att);
                listAttachment.add(attachment);
            }
            newsArticle.setAttachment(listAttachment);
        }
        try{
            newsArticleRepository.save(newsArticle);
            CreateNewsArticleResponse createNewsArticle = CreateNewsArticleResponse.of(newsArticle);
            return new StatusResponse<CreateNewsArticleResponse>(Status.OK,"Criação efetuada com Sucesso",createNewsArticle);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<CreateNewsArticleResponse>(Status.NOK,null);
        }

    }

    @RequestMapping (value = "/{id}")
    public StatusResponse findOne(
            @PathVariable String id)
    {
        int idArticle = Integer.parseInt(id);
        NewsArticle newsArticle = newsArticleRepository.findOne(idArticle);

        UpdateNewsArticleResponse updateNewsArticleResponse = UpdateNewsArticleResponse.of(newsArticle);
        return new StatusResponse<UpdateNewsArticleResponse>(Status.OK,"News Article:",updateNewsArticleResponse);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public StatusResponse<UpdateNewsArticleResponse> updateFeaturedArticle (
            @PathVariable String id,
            @RequestBody UpdateNewsArticleRequest request){

        int idArticle = Integer.parseInt(id);
        NewsArticle newsArticle = newsArticleRepository.findOne(idArticle);

        if (newsArticle == null)
            return new StatusResponse (Status.NOK,"O artigo selecionado não existe");

        newsArticle.setDescription(request.getDescription().getBytes());
        if (request.getlistIdAttachments().length > 0) {
            List<Attachment> listAttachment = new ArrayList<Attachment>();
            for (int att:request.getlistIdAttachments()) {
                Attachment attachment = attachmentRepository.findOne(att);
                listAttachment.add(attachment);
            }
            newsArticle.setAttachment(listAttachment);
        }else
        {
            newsArticle.setAttachment(null);
        }
        try{
            newsArticleRepository.save(newsArticle);
            UpdateNewsArticleResponse updateNewsArticleRequest = UpdateNewsArticleResponse.of(newsArticle);
            return new StatusResponse<UpdateNewsArticleResponse>(Status.OK,"Alteração efetuada com sucesso.",updateNewsArticleRequest);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<UpdateNewsArticleResponse>(Status.NOK,null);
        }
    }

}
