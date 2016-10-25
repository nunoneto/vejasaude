package pt.vejasaude.web.services.videoArticle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.attachment.IAttachmentRepository;
import pt.vejasaude.unified.data.video.IVideoRepository;
import pt.vejasaude.unified.data.video.Video;
import pt.vejasaude.unified.data.videoArticle.IVideoArticleRepository;
import pt.vejasaude.unified.data.videoArticle.VideoArticle;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;
import pt.vejasaude.web.services.videoArticle.request.CreateVideoArticleRequest;
import pt.vejasaude.web.services.videoArticle.request.UpdateVideoArticleRequest;
import pt.vejasaude.web.services.videoArticle.response.CreateVideoArticleResponse;
import pt.vejasaude.web.services.videoArticle.response.UpdateVideoArticleResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmorais on 18/10/2016.
 */
@RequestMapping("api/v1/videoArticle")
@RestController
public class videoArticleController {
    @Autowired
    private IVideoArticleRepository videoArticleRepository;
    @Autowired
    private IAttachmentRepository attachmentRepository;
    @Autowired
    private IVideoRepository videoRepository;

    @RequestMapping (method = RequestMethod.POST)
    public StatusResponse <CreateVideoArticleResponse> createVideoArticle(
            @RequestBody CreateVideoArticleRequest request){
        VideoArticle videoArticle = new VideoArticle();

        if (request.getDescription() == null)
            return new StatusResponse<CreateVideoArticleResponse>(Status.NOK, "Preencha os campos obrigatórios");
        else
            videoArticle.setContent(request.getDescription().getBytes());

        if (request.getlistIdAttachments().length > 0) {
            List<Attachment> listAttachment = new ArrayList<Attachment>();
            for (int att:request.getlistIdAttachments()) {
                Attachment attachment = attachmentRepository.findOne(att);
                listAttachment.add(attachment);
            }
            videoArticle.setAttachment(listAttachment);
        }

        if (request.getVideos().length > 0) {
            List<Video> listVideo = new ArrayList<Video>();
            for (int vid:request.getVideos()) {
                Video video = videoRepository.findOne(vid);
                listVideo.add(video);
            }
            videoArticle.setVideo(listVideo);
        }

        try{
            videoArticleRepository.save(videoArticle);
            CreateVideoArticleResponse createVideoArticle = CreateVideoArticleResponse.of(videoArticle);
            return new StatusResponse<CreateVideoArticleResponse>(Status.OK,"Criação efetuada com Sucesso",createVideoArticle);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<CreateVideoArticleResponse>(Status.NOK,null);
        }

    }
    @RequestMapping (value = "/{id}")
    public StatusResponse findOne(
            @PathVariable String id)
    {
        int idVideo = Integer.parseInt(id);
        VideoArticle videoArticle = videoArticleRepository.findOne(idVideo);

        UpdateVideoArticleResponse updateVideoArticleResponse = UpdateVideoArticleResponse.of(videoArticle);
        return new StatusResponse<UpdateVideoArticleResponse>(Status.OK,"Video Article:",updateVideoArticleResponse);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public StatusResponse<UpdateVideoArticleResponse> updateFeaturedArticle (
            @PathVariable String id,
            @RequestBody UpdateVideoArticleRequest request){

        int idVideo = Integer.parseInt(id);
        VideoArticle videoArticle = videoArticleRepository.findOne(idVideo);

        if (videoArticle == null)
            return new StatusResponse (Status.NOK,"O artigo selecionado não existe");

        videoArticle.setContent(request.getDescription().getBytes());
        if (request.getlistIdAttachments().length > 0) {
            List<Attachment> listAttachment = new ArrayList<Attachment>();
            for (int att:request.getlistIdAttachments()) {
                Attachment attachment = attachmentRepository.findOne(att);
                listAttachment.add(attachment);
            }
            videoArticle.setAttachment(listAttachment);
        }else
        {
            videoArticle.setAttachment(null);
        }
        try{
           videoArticleRepository.save(videoArticle);
            UpdateVideoArticleResponse updateVideoArticleRequest = UpdateVideoArticleResponse.of(videoArticle);
            return new StatusResponse<UpdateVideoArticleResponse>(Status.OK,"Alteração efetuada com sucesso.",updateVideoArticleRequest);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<UpdateVideoArticleResponse>(Status.NOK,null);
        }
    }


}
