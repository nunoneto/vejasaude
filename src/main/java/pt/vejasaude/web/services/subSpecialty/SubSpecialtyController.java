package pt.vejasaude.web.services.subSpecialty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.vejasaude.unified.data.medicalSpecialty.IMedicalSpecialtyRepository;
import pt.vejasaude.unified.data.medicalSpecialty.MedicalSpecialty;
import pt.vejasaude.unified.data.subSpecialty.ISubSpecialtyRepository;
import pt.vejasaude.unified.data.subSpecialty.SubSpecialty;
import pt.vejasaude.web.services.articleType.response.CreateArticleTypeResponse;
import pt.vejasaude.web.services.articleType.response.UpdateArticleTypeResponse;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;
import pt.vejasaude.web.services.subSpecialty.request.CreateSubSpecialtyRequest;
import pt.vejasaude.web.services.subSpecialty.response.CreateSubSpecialtyResponse;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by fmorais on 26/10/2016.
 */
@RequestMapping("api/v1/subSpecialty")
@RestController
public class SubSpecialtyController {
    @Autowired
    private ISubSpecialtyRepository subSpecialtyRepository;
    @Autowired
    private IMedicalSpecialtyRepository medicalSpecialtyRepository;

    @RequestMapping(method = RequestMethod.POST)
    public StatusResponse<CreateSubSpecialtyResponse> createSubSpecialty
            (@RequestBody CreateSubSpecialtyRequest request){
        SubSpecialty subSpecialty = new SubSpecialty();
        if (request.getSubSpecialty() == null)
            return new StatusResponse<>(Status.NOK,"Preenchimento Obrigatório");
        else
            subSpecialty.setSubSpecialty(request.getSubSpecialty());

        MedicalSpecialty specialty = medicalSpecialtyRepository.findOne(request.getSpecialty()); ;

        if(specialty==null)
            return new StatusResponse<>(Status.NOK,"Preenchimento Obrigatório");
        else
            subSpecialty.setSpecialty(specialty);
        try{
            subSpecialtyRepository.save(subSpecialty);
            CreateSubSpecialtyResponse createSubSpecialtyResponse = CreateSubSpecialtyResponse.of(subSpecialty);
            return new StatusResponse<CreateSubSpecialtyResponse>(Status.OK,"Criado com Sucesso",createSubSpecialtyResponse);
        }catch (Exception e) {
            e.printStackTrace();
            return new StatusResponse<CreateSubSpecialtyResponse>(Status.NOK, null);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public StatusResponse<List<CreateSubSpecialtyResponse>> getSubSpecialityBySpeciality(@RequestParam int specialtyId) {

        MedicalSpecialty medicalSpecialty = medicalSpecialtyRepository.findOne(specialtyId);
        if (medicalSpecialty == null) {
            return new StatusResponse<>(Status.NOK,"A especialidade escolhida não existe!");
        }

        List<CreateSubSpecialtyResponse> subSpecialtyResponse = medicalSpecialty
                .getSubSpecialties()
                .stream()
                .map(new Function<SubSpecialty, CreateSubSpecialtyResponse>() {
                    @Override
                    public CreateSubSpecialtyResponse apply(SubSpecialty subSpecialty) {
                        return CreateSubSpecialtyResponse.of(subSpecialty);
                    }
                }).collect(Collectors.toList());

        return new StatusResponse<>(Status.OK,"",subSpecialtyResponse);

    }

}
