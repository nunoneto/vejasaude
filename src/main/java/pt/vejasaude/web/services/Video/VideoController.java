package pt.vejasaude.web.services.Video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.vejasaude.facade.VideoFacade.IVideoFacade;
import pt.vejasaude.unified.data.Video.IVideoRepository;
import pt.vejasaude.unified.data.Video.Video;
import pt.vejasaude.unified.data.doctor.Doctor;
import pt.vejasaude.unified.data.doctor.IDoctorRepository;
import pt.vejasaude.web.services.Video.Request.VideoRequest;
import pt.vejasaude.web.services.Video.Response.VideoResponse;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;

/**
 * Created by fmorais on 23/08/2016.
 */
@RequestMapping("/api/v1/video")
@RestController
public class VideoController {
    @Autowired
    private IVideoFacade videoFacade;
    @Autowired
    private IVideoRepository videoRep;
    @Autowired
    private IDoctorRepository doctorRep;

    @RequestMapping (method = RequestMethod.POST)
    public StatusResponse<VideoResponse> createVideo (@RequestBody VideoRequest request){
        Video video = videoFacade.createVideo(request);
        VideoResponse createVideoResponse = VideoResponse.of(video);
        try{
            videoFacade.createVideo(request);
            return new StatusResponse<VideoResponse>(Status.OK,null,createVideoResponse);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<VideoResponse>(Status.NOK,null);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public StatusResponse<Iterable<Video>> getAll(){
        return new StatusResponse<>(Status.OK,null,videoRep.findAll());
    }

    @RequestMapping (value = "/{id}", method = RequestMethod.POST)
    public StatusResponse<VideoResponse> updateVideo (
            @PathVariable String id,
            @RequestBody VideoRequest request){
        int idVideo = Integer.parseInt(id);
        Video video = videoRep.findOne(idVideo);

        if (video == null)
            return new StatusResponse(Status.NOK,"O video não existe");
        else {
            video.setVideoLink(request.getLinkVideo());
            Doctor author = doctorRep.findOne(request.getAuthor());
            if (author == null)
                return new StatusResponse(Status.NOK,"O autor do video não foi encontrado");
            else
                video.setAuthor(author);
            video.setDate(request.getDate());
        }
        try {
            videoRep.save(video);
            VideoResponse updatedVideoResponse = VideoResponse.of(video);
            return new StatusResponse<VideoResponse>(Status.OK,"Video Alterado",updatedVideoResponse);
        }catch (Exception e){
            e.printStackTrace();
            return  new StatusResponse(Status.NOK,e.getMessage());
        }
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public StatusResponse removeVideo(
            @PathVariable String id)
    {
        int idVideo = Integer.parseInt(id);
        Video removeVideo = videoRep.findOne(idVideo);

        if (removeVideo == null)
            return new StatusResponse(Status.NOK,"O video não existe");
        try
        {
            videoRep.delete(idVideo);
            return new StatusResponse(Status.OK, "Video eliminado");
        }catch(Exception e){
            e.printStackTrace();
            return new StatusResponse(Status.NOK, e.getMessage());
        }
    }
}
