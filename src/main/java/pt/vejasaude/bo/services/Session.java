package pt.vejasaude.bo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.vejasaude.bo.services.responses.SessionResponse;
import pt.vejasaude.bo.services.responses.generic.Status;
import pt.vejasaude.unified.data.backofficeuser.BackOfficeUser;
import pt.vejasaude.unified.data.backofficeuser.IBackOfficeUserRepository;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by NB20301 on 24/05/2016.
 */
@RestController
@RequestMapping("/api/v1/session")
public class Session {

    @Autowired
    IBackOfficeUserRepository userRepo;

    private final static String BO_SESSION = "BO_SESSION";

    @RequestMapping(value="", method = RequestMethod.POST)
    public SessionResponse startSession(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session){

        BackOfficeUser user = null;
        try{

            if(session.getAttribute(BO_SESSION) == null){

                user = userRepo.findOne(username);
                if(user == null || user.getWrongPasswordTries() >= BackOfficeUser.maxWrongPasswordTries)
                    return new SessionResponse("Conta bloqueada ou user inexistente",Status.NOK,null);
                if(!user.getPassword().equals(password))
                    return new SessionResponse("Username ou Password errada",Status.NOK,null);

                //prevent session fixation
                session.invalidate();

                //reset wrong attempts
                user.resetWrongPasswordTries();
                user.setSessionID(session.getId());
                userRepo.save(user);

                session.setAttribute(BO_SESSION,user);
            }

        }catch (Exception e){
            return new SessionResponse(null,Status.NOK,null);
        }
        return new SessionResponse("Login OK",Status.OK,user);
    }



}
