package pt.vejasaude.web.services.session.request;

/**
 * Created by NB20301 on 25/05/2016.
 */
public class ChangePasswordRequest {

    private String currentPassword, newPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
