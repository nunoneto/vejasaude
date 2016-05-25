package pt.vejasaude.bo.services.generic;

/**
 * Created by NB20301 on 24/05/2016.
 */
public class StatusResponse<T> {

    private String statusMessage;
    private int statusCode;
    private T content;

    public StatusResponse(int statusCode, String statusMessage) {
        this.statusMessage = statusMessage;
        this.statusCode = statusCode;
    }

    public StatusResponse(int statusCode, String statusMessage, T content) {
        this.statusMessage = statusMessage;
        this.statusCode = statusCode;
        this.content = content;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
