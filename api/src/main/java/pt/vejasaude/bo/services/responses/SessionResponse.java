package pt.vejasaude.bo.services.responses;

import pt.vejasaude.bo.services.responses.generic.StatusResponse;
import pt.vejasaude.unified.data.backofficeuser.BackOfficeUser;

/**
 * Created by NB20301 on 24/05/2016.
 */
public class SessionResponse extends StatusResponse {

    private BackOfficeUser session;

    public SessionResponse(String statusMessage, int statusCode) {
        super(statusMessage, statusCode);
    }

    public SessionResponse(String statusMessage, int statusCode, BackOfficeUser session) {
        super(statusMessage, statusCode);
        this.session = session;
    }

    public static SessionResponse of(int statusCode, String statusMessage){

        SessionResponse resp = new SessionResponse(statusMessage, statusCode);

        return resp;
    }

}
