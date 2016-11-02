package pt.vejasaude.web.services.article.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.article.Article;
import pt.vejasaude.unified.data.articleType.ArticleType;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.doctor.Doctor;
import pt.vejasaude.unified.data.medicalSpecialty.MedicalSpecialty;
import pt.vejasaude.unified.data.referenceLink.ReferenceLink;
import pt.vejasaude.unified.data.subSpecialty.SubSpecialty;
import pt.vejasaude.web.services.articleType.response.CreateArticleTypeResponse;
import pt.vejasaude.web.services.medicalSpecialty.response.CreateNewSpecialtyResponse;
import pt.vejasaude.web.services.referenceLink.response.ReferenceLinkResponse;
import pt.vejasaude.web.services.subSpecialty.request.CreateSubSpecialtyRequest;
import pt.vejasaude.web.services.subSpecialty.response.CreateSubSpecialtyResponse;

import javax.persistence.*;
import javax.print.Doc;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Nuno Neto on 02-11-2016.
 */
public class FullArticleResponse {

    @JsonProperty
    private int id;
    @JsonProperty
    private CreateArticleTypeResponse articleType;
    @JsonProperty
    private String title;
    @JsonProperty
    private String description;
    @JsonProperty
    private CreateNewSpecialtyResponse specialty;
    @JsonProperty
    private CreateSubSpecialtyResponse subSpecialty;
    @JsonProperty
    private DoctorResponse doctor;

    public static class DoctorResponse {

        @JsonProperty
        private int id;
        @JsonProperty
        private String name;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static DoctorResponse of(Doctor doctor){
            DoctorResponse doctorResponse = new DoctorResponse();
            doctorResponse.id = doctor.getId();
            doctorResponse.name = doctor.getName();
            return doctorResponse;
        }

    }


    @JsonProperty
    private long  createdDate;
    @JsonProperty
    private List<ReferenceLinkResponse> referenceLinks;

    public static FullArticleResponse of(Article article){
        FullArticleResponse articleResponse = new FullArticleResponse();

        articleResponse.id = article.getId();
        articleResponse.articleType = CreateArticleTypeResponse.of(article.getTypeArticle());
        articleResponse.title = article.getTitle();
        articleResponse.description = new String(article.getDescription());
        articleResponse.createdDate = article.getCreatedDate().getTime();
        articleResponse.doctor = DoctorResponse.of(article.getDoctor());
        articleResponse.specialty = CreateNewSpecialtyResponse.of(article.getSpecialty());
        articleResponse.subSpecialty = CreateSubSpecialtyResponse.of(article.getSubSpecialty());
        articleResponse.referenceLinks = article.getReferenceLinks()
                                                .stream()
                                                .map(new Function<ReferenceLink, ReferenceLinkResponse>() {
                                                    @Override
                                                    public ReferenceLinkResponse apply(ReferenceLink referenceLink) {
                                                        return ReferenceLinkResponse.of(referenceLink);
                                                    }
                                                }).collect(Collectors.toList());

        return articleResponse;
    }

}
