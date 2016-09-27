package pt.vejasaude.web.services.attachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pt.vejasaude.facade.attachmentFacade.IAttachmentFacade;
import pt.vejasaude.unified.data.attachment.Attachment;
import pt.vejasaude.unified.data.attachment.IAttachmentRepository;
import pt.vejasaude.web.services.attachment.response.CreateNewAttachmentResponse;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fmorais on 25/08/2016.
 */
@RequestMapping("/api/v1/attachment")
@RestController
public class AttachmentController {
    @Autowired
    private IAttachmentRepository attachmentRepository;
    @Autowired
    private IAttachmentFacade attachmentFacade;

    @RequestMapping(method = RequestMethod.POST)
    public StatusResponse<CreateNewAttachmentResponse> createAttachment (@RequestParam MultipartFile [] files) throws IOException {
        List<Attachment> attachmentList = new ArrayList<Attachment>();
        if (files == null)
            return  new StatusResponse<>(Status.NOK,"Por favor anexe o ficheiro!");
        else{
            for (int i=0; i<files.length; i++){
                Attachment attachment = new Attachment();
                attachment.setAttach(files[i].getBytes());
                attachment.setFileName(files[i].getOriginalFilename());
                attachment.setContentType(files[i].getContentType());
                attachment.setSize(files[i].getSize());
                attachmentList.add(attachment);
            }
        }
    try {
        attachmentRepository.save(attachmentList);
        CreateNewAttachmentResponse createNewAttachmentResponse = CreateNewAttachmentResponse.of(attachmentList);
        return new StatusResponse <CreateNewAttachmentResponse> (Status.OK, null,createNewAttachmentResponse);
    }catch (Exception e){
        e.printStackTrace();
        return  new StatusResponse(Status.NOK, "Erro ao anexar o ficheiro");
    }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public StatusResponse findAttachment(@PathVariable String id){
        int idAttachment= Integer.parseInt(id);
        List<Attachment> attachmentList = null;
                attachmentList.add(attachmentRepository.findOne(idAttachment));
        if (attachmentList == null)
            return new StatusResponse(Status.NOK,"O anexo não existe");
        CreateNewAttachmentResponse attachmentResponse = CreateNewAttachmentResponse.of(attachmentList);
        return new StatusResponse<CreateNewAttachmentResponse> (Status.OK,null,attachmentResponse);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public StatusResponse removeAttachment(
            @PathVariable String id){
        int idAttachment= Integer.parseInt(id);
        Attachment attachment = attachmentRepository.findOne(idAttachment);

        if (attachment == null)
            return new StatusResponse(Status.NOK,"O anexo não existe");
        try
        {
            attachmentRepository.delete(attachment);
            return new StatusResponse(Status.OK,"Anexo eliminado");
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse(Status.NOK,e.getMessage());
        }
    }
}
