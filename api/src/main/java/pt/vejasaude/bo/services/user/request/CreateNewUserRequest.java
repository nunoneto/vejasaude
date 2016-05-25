package pt.vejasaude.bo.services.user.request;

/**
 * Created by NB20301 on 25/05/2016.
 */
public class CreateNewUserRequest {

    private String username, password, email, prettyName;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPrettyName() {
        return prettyName;
    }
}
