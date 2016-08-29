package pt.vejasaude.web.services.attachment.request;

/**
 * Created by fmorais on 25/08/2016.
 */
public class CreateNewAttachmentRequest {

    private String ContentType;
    private String FileName;
    private long Size;
    private byte[] Attachment;

    public String getContentType() {return ContentType;}

    public void setContentType(String contentType) {ContentType = contentType;}

    public String getFileName() {return FileName;}

    public void setFileName(String fileName) {FileName = fileName;}

    public long getSize() {return Size;}

    public void setSize(long size) {Size = size;}

    public byte[] getAttachment() {return Attachment;}

    public void setAttachment(byte[] attachment) {Attachment = attachment;}
}
