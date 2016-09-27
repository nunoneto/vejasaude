package pt.vejasaude.web.services.attachment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.vejasaude.unified.data.attachment.Attachment;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by fmorais on 25/08/2016.
 */
public class CreateNewAttachmentResponse implements Serializable{
    @JsonProperty
    private List<AttachmentResponse> attachmentList;

    public CreateNewAttachmentResponse(List<AttachmentResponse> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public static CreateNewAttachmentResponse of(List<Attachment> attachmentList)
     {

         return new CreateNewAttachmentResponse(
                 attachmentList.stream().map(new Function<Attachment, AttachmentResponse>() {
                     @Override
                     public AttachmentResponse apply(Attachment attachment) {
                         return new AttachmentResponse(attachment.getId(),attachment.getContentType(),attachment.getFileName(),attachment.getSize());
                     }
                 }).collect(Collectors.<AttachmentResponse> toList())
         );
     }
}