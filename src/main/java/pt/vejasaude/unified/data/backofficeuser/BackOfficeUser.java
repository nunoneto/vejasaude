package pt.vejasaude.unified.data.backofficeuser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pt.vejasaude.web.services.user.request.CreateNewUserRequest;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by NB20301 on 24/05/2016.
 */
@Entity
@Table
public class BackOfficeUser implements Serializable{


    /**
     * Constants
     **/
    public final static int MAX_PASSWORD_WRONG_TRIES = 3;
    public final static int PASSWORD_MIN_CHARS = 8;

    //TODO: map json props to hide password, etc...

    @Id
    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String email, sessionID, prettyName;

    @Column
    @JsonIgnore
    private int wrongPasswordTries;



    public BackOfficeUser() {
        this.wrongPasswordTries = 0;
    }

    public BackOfficeUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.wrongPasswordTries = 0;
    }

    public BackOfficeUser(CreateNewUserRequest user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.prettyName = user.getPrettyName();
        this.wrongPasswordTries = 0;
    }

    public void bumpWrongPasswordTries(){
        this.wrongPasswordTries++;
    }

    public int getWrongPasswordTries() {
        return wrongPasswordTries;
    }

    public void resetWrongPasswordTries(){
        this.wrongPasswordTries = 0;
    }

    public String getPassword() {
        return password;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public void clearSessionID(){
        this.sessionID = null;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPrettyName() {
        return prettyName;
    }

    public static int getMaxPasswordWrongTries() {
        return MAX_PASSWORD_WRONG_TRIES;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrettyName(String prettyName) {
        this.prettyName = prettyName;
    }

    public void setWrongPasswordTries(int wrongPasswordTries) {
        this.wrongPasswordTries = wrongPasswordTries;
    }
}
