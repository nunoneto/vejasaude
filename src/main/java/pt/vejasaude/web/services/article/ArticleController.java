package pt.vejasaude.web.services.article;

import javafx.scene.shape.Arc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.vejasaude.unified.data.articleType.ArticleType;
import pt.vejasaude.unified.data.articleType.IArticleTypeRepository;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.attachment.IAttachmentRepository;
import pt.vejasaude.unified.data.article.Article;
import pt.vejasaude.unified.data.article.IArticleRepository;
import pt.vejasaude.unified.data.backofficeuser.BackOfficeUser;
import pt.vejasaude.unified.data.doctor.Doctor;
import pt.vejasaude.unified.data.doctor.IDoctorRepository;
import pt.vejasaude.unified.data.medicalSpecialty.IMedicalSpecialtyRepository;
import pt.vejasaude.unified.data.medicalSpecialty.MedicalSpecialty;
import pt.vejasaude.unified.data.referenceLink.IReferenceLinkRepository;
import pt.vejasaude.unified.data.referenceLink.ReferenceLink;
import pt.vejasaude.unified.data.subSpecialty.ISubSpecialtyRepository;
import pt.vejasaude.unified.data.subSpecialty.SubSpecialty;
import pt.vejasaude.web.services.article.request.CreateArticleRequest;
import pt.vejasaude.web.services.article.request.UpdateArticleRequest;
import pt.vejasaude.web.services.article.response.ArticleResponse;
import pt.vejasaude.web.services.article.response.CreateArticleResponse;
import pt.vejasaude.web.services.article.response.FullArticleResponse;
import pt.vejasaude.web.services.article.response.UpdateGeneralArticleResponse;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by fmorais on 28/09/2016.
 */
@RequestMapping("api/v1/article")
@RestController
public class ArticleController {
    @Autowired
    private IArticleRepository articleRepository;
    @Autowired
    private IAttachmentRepository attachmentRepository;
    @Autowired
    private IMedicalSpecialtyRepository specialtyRepository;
    @Autowired
    private ISubSpecialtyRepository subSpecialtyRepository;
    @Autowired
    private IDoctorRepository doctorRepository;
    @Autowired
    private IReferenceLinkRepository referenceLinkRepository;
    @Autowired
    private IArticleTypeRepository  articleTypeRepository;

    public final static String BO_SESSION = "BO_SESSION";

    @RequestMapping(method = RequestMethod.GET)
    public StatusResponse<List<ArticleResponse>> getAll(){

        Iterable<Article> articles = articleRepository.findAll();

        if (articles == null) {
            return new StatusResponse<List<ArticleResponse>>(Status.NOK,"");
        }

        List<ArticleResponse> articleList = new ArrayList<>();
        StreamSupport
                .stream(articles.spliterator(), false)
                .map(new Function<Article, ArticleResponse>() {
                    @Override
                    public ArticleResponse apply(Article article) {
                        return ArticleResponse.of(article);
                    }
                })
                .collect(Collectors.<ArticleResponse> toList());

        return new StatusResponse<List<ArticleResponse>>(Status.OK,"",articleList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public StatusResponse<CreateArticleResponse> createGeneralArticle(@RequestBody CreateArticleRequest request, HttpSession session)
    {
        Article article = new Article();

        if (request.getTypeArticle() == null) {
            return new StatusResponse<>(Status.NOK, "Preencha os campos obrigatórios");
        }else {
            ArticleType articleType = articleTypeRepository.findOne(request.getTypeArticle());
            if (articleType == null)
                return new StatusResponse<>(Status.NOK, "Tipo de Artigo inexistente");
            else
                article.setArticleType(articleType);
        }
        if (request.getTitle() == null)
            return new StatusResponse<>(Status.NOK,"Preencha os campos obrigatórios");
        else
            article.setTitle(request.getTitle());
        if (request.getDescription() == null)
            return new StatusResponse<>(Status.NOK, "Preencha os campos obrigatórios");
        else
            article.setDescription(request.getDescription().getBytes());

        if (request.getSpecialty() == null) {
            return new StatusResponse<>(Status.NOK, "Preencha os campos obrigatórios");
        } else {
            MedicalSpecialty specialty = specialtyRepository.findOne(request.getSpecialty());

            if (specialty == null){
                return new StatusResponse<>(Status.NOK,"Especialidade não encontrada");
            }else {
                article.setSpecialty(specialty);
            }
        }
        if (request.getSubSpecialty() == null) {
            return new StatusResponse<>(Status.NOK, "Preencha os campos obrigatórios");
        } else {
            SubSpecialty subSpecialty = subSpecialtyRepository.findOne(request.getSubSpecialty());

            if (subSpecialty == null){
                return new StatusResponse<>(Status.NOK,"Subespecialdidade não encontrada");
            }else {
                article.setSubSpecialty(subSpecialty);
            }
        }
        if (request.getAuthor() == null) {
            return new StatusResponse<>(Status.NOK, "Preencha os campos obrigatórios");
        } else {
            Doctor author = doctorRepository.findOne(request.getAuthor());

            if (author == null){
                return new StatusResponse<>(Status.NOK,"Autor não encontrado");
            }else {
                article.setDoctor(author);
            }
        }

        if (request.getListIdAttachments().length > 0) {
            List<Attachment> listAttachment = new ArrayList<Attachment>();
            for (int att:request.getListIdAttachments()) {
                Attachment attachment = attachmentRepository.findOne(att);
                listAttachment.add(attachment);
            }
            article.setAttachment(listAttachment);
        }

        article.setCreatedDate(request.getCreatedDate());

        if(request.getReferenceLinks().length>0) {
            List<ReferenceLink> listReferenceLinks = new ArrayList<ReferenceLink>();
            for (int ref:request.getReferenceLinks()){
                ReferenceLink referenceLink = referenceLinkRepository.findOne(ref);
                listReferenceLinks.add(referenceLink);
            }
           article.setReferenceLinks(listReferenceLinks);
       }

        BackOfficeUser user = (BackOfficeUser) session.getAttribute(BO_SESSION);
        if (user == null)
            return new StatusResponse<>(Status.NOK,"Autor não encontrado");
        else
            article.setUser(user);

        try{
            articleRepository.save(article);
            CreateArticleResponse createArticleResponse = CreateArticleResponse.of(article);
            return new StatusResponse<CreateArticleResponse>(Status.OK,"Criação efetuada com Sucesso", createArticleResponse);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<CreateArticleResponse>(Status.NOK,null);
        }
    }
    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public StatusResponse findOne(
            @PathVariable String id)
    {
        int idArticle = Integer.parseInt(id);
        Article article = articleRepository.findOne(idArticle);

        FullArticleResponse fullArticleResponse = FullArticleResponse.of(article);
        return new StatusResponse<FullArticleResponse>(Status.OK,null,fullArticleResponse);
    }

    /*@RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public StatusResponse <UpdateGeneralArticleResponse> updateGeneralArticle (
            @PathVariable String id,
            @RequestBody UpdateArticleRequest request) {
        int idArticle = Integer.parseInt(id);
        Article article = articleRepository.findOne(idArticle);

        if (article == null)
            return new StatusResponse(Status.NOK,"O artigo selecionado não existe");


        article.setDescription(request.getDescription().getBytes());
        if (request.getListIdAttachments().length > 0) {
            List<Attachment> listAttachment = new ArrayList<Attachment>();
            for (int att:request.getListIdAttachments()) {
                Attachment attachment = attachmentRepository.findOne(att);
                listAttachment.add(attachment);
            }
            article.setAttachment(listAttachment);
        }

        try{
            articleRepository.save(article);
            UpdateGeneralArticleResponse updateGeneralArticleResponse = UpdateGeneralArticleResponse.of(article);
            return new StatusResponse<UpdateGeneralArticleResponse>(Status.OK,"Alteração efetuada com sucesso.",updateGeneralArticleResponse);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<UpdateGeneralArticleResponse>(Status.NOK,null);

        }

    }*/
}
