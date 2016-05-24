package pt.vejasaude.bo.services.responses.generic;

/**
 * Created by NB20301 on 24/05/2016.
 */
public class StatusResponse {

    private String statusMessage;
    private int statusCode;

    public StatusResponse(String statusMessage, int statusCode) {
        this.statusMessage = statusMessage;
        this.statusCode = statusCode;
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
