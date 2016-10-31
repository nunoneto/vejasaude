package pt.vejasaude.web.services.articleType;

import org.hibernate.bytecode.buildtime.spi.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.vejasaude.unified.data.articleType.ArticleType;
import pt.vejasaude.unified.data.articleType.IArticleTypeRepository;
import pt.vejasaude.web.services.articleType.request.CreateArticleTypeRequest;
import pt.vejasaude.web.services.articleType.response.CreateArticleTypeResponse;
import pt.vejasaude.web.services.articleType.response.UpdateArticleTypeResponse;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;

/**
 * Created by fmorais on 26/10/2016.
 */
@RequestMapping("api/v1/articleType")
@RestController
public class ArticleTypeController {
    @Autowired
    private IArticleTypeRepository articleTypeRepository;

    @RequestMapping(method = RequestMethod.POST)
    public StatusResponse<CreateArticleTypeResponse> createArticleType (@RequestBody CreateArticleTypeRequest request)
    {
        ArticleType articleType = new ArticleType();
        if (request.getArticleType() == null)
            return new StatusResponse<>(Status.NOK,"Preencha os campos obrigatórios");
        else
            articleType.setArticleType(request.getArticleType());
        try{
            articleTypeRepository.save(articleType);
            CreateArticleTypeResponse createArticleTypeResponse = CreateArticleTypeResponse.of(articleType);
            return new StatusResponse<CreateArticleTypeResponse>(Status.OK,"Criado com sucesso", createArticleTypeResponse);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<CreateArticleTypeResponse>(Status.NOK,null);
        }
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public StatusResponse <UpdateArticleTypeResponse> findOne(
            @PathVariable String id){
        int idArticleType = Integer.parseInt(id);
        ArticleType articleType = articleTypeRepository.findOne(idArticleType);
        UpdateArticleTypeResponse updateArticleTypeResponse = UpdateArticleTypeResponse.of(articleType);
        return new StatusResponse<UpdateArticleTypeResponse>(Status.OK,null,updateArticleTypeResponse);
    }
}
