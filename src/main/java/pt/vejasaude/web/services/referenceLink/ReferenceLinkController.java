package pt.vejasaude.web.services.referenceLink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.vejasaude.unified.data.referenceLink.IReferenceLinkRepository;
import pt.vejasaude.unified.data.referenceLink.ReferenceLink;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;
import pt.vejasaude.web.services.referenceLink.response.ReferenceLinkResponse;

/**
 * Created by fmorais on 26/10/2016.
 */
@RequestMapping("api/v1/referenceLink")
@RestController
public class ReferenceLinkController {
    @Autowired
    private IReferenceLinkRepository referenceLinkRepository;

    @RequestMapping (method = RequestMethod.POST)
    public StatusResponse<ReferenceLinkResponse> createReferenceLink (
            @RequestBody ReferenceLink request){
        ReferenceLink referenceLink = new ReferenceLink();
        referenceLink.setReferenceLink(request.getReferenceLink());
        try{
            referenceLinkRepository.save(referenceLink);
            ReferenceLinkResponse referenceLinkResponse = ReferenceLinkResponse.of(referenceLink);
            return new StatusResponse<ReferenceLinkResponse>(Status.OK,"",referenceLinkResponse);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<ReferenceLinkResponse>(Status.NOK,null);
        }
    }

}
