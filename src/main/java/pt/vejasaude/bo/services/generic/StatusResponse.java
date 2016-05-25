package pt.vejasaude.bo.services.generic;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by NB20301 on 24/05/2016.
 */
public class StatusResponse<T> {

    @JsonProperty
    private String statusMessage;
    @JsonProperty
    private int statusCode;
    @JsonProperty
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
