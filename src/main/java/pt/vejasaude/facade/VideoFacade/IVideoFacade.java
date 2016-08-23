package pt.vejasaude.facade.VideoFacade;

import pt.vejasaude.unified.data.Video.Video;
import pt.vejasaude.web.services.Video.Request.VideoRequest;

/**
 * Created by fmorais on 23/08/2016.
 */
public interface IVideoFacade {

    Video createVideo(VideoRequest request);
}
