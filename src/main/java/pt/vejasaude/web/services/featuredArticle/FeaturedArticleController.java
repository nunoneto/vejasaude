package pt.vejasaude.web.services.featuredArticle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.featuredArticle.FeaturedArticle;
import pt.vejasaude.unified.data.featuredArticle.IFeaturedArticleRepository;
import pt.vejasaude.web.services.featuredArticle.request.CreateNewFeaturedArticleRequest;
import pt.vejasaude.web.services.featuredArticle.response.CreateNewFeaturedArticleResponse;
import pt.vejasaude.web.services.generic.StatusResponse;

import java.util.List;

/**
 * Created by fmorais on 30/08/2016.
 */
@RequestMapping("/api/v1/featuredArticle")
@RestController
public class FeaturedArticleController {
    @Autowired
    private IFeaturedArticleRepository  featuredArticleRepository;

    @RequestMapping(method = RequestMethod.POST)
    public StatusResponse<CreateNewFeaturedArticleResponse> createNewFeaturedArticle (
            @RequestBody CreateNewFeaturedArticleRequest request){

    return null;
    }

}
