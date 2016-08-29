package pt.vejasaude.facade.videoFacade;

import pt.vejasaude.unified.data.video.Video;
import pt.vejasaude.web.services.video.request.VideoRequest;

/**
 * Created by fmorais on 23/08/2016.
 */
public interface IVideoFacade {

    Video createVideo(VideoRequest request);
}
