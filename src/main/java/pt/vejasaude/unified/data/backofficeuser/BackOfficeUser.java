package pt.vejasaude.unified.data.backofficeuser;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by NB20301 on 24/05/2016.
 */
@Entity
@Table
public class BackOfficeUser implements Serializable{

    //TODO: map json props to hide password, etc...

    @Id
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email, sessionID;
    @Column
    private int wrongPasswordTries;

    /**
     * Define the max number of times a user can fail the login
     **/
    public final static int maxWrongPasswordTries = 3;

    public BackOfficeUser() {
    }

    public BackOfficeUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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
}
