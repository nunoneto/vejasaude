package pt.vejasaude.web.services.attachment.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by fmorais on 08/09/2016.
 */
public class AttachmentResponse{
    @JsonProperty
    private int id;
    @JsonProperty
    private String contentType;
    @JsonProperty
    private String fileName;
    @JsonProperty
    private long size;

    public AttachmentResponse(int id, String contentType, String fileName, long size) {
        this.id = id;
        this.contentType = contentType;
        this.fileName = fileName;
        this.size = size;
    }
}