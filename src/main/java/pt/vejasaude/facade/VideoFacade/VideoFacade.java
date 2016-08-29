package pt.vejasaude.facade.VideoFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.vejasaude.unified.data.Video.IVideoRepository;
import pt.vejasaude.unified.data.Video.Video;
import pt.vejasaude.web.services.Video.Request.VideoRequest;

/**
 * Created by fmorais on 23/08/2016.
 */
@Service
public class VideoFacade implements IVideoFacade {
    @Autowired
    private IVideoRepository videoDAO;

    @Override
    public Video createVideo (VideoRequest request){
            Video newVideo = null;
        try{
            newVideo = new Video(request);
            videoDAO.save(newVideo);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  newVideo;
    }
}
