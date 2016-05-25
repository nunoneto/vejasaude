package pt.vejasaude.bo.services.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.vejasaude.bo.services.generic.Status;
import pt.vejasaude.bo.services.generic.StatusResponse;
import pt.vejasaude.unified.data.backofficeuser.BackOfficeUser;
import pt.vejasaude.unified.data.backofficeuser.IBackOfficeUserRepository;

import javax.servlet.http.HttpServletRequest;
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


    @RequestMapping(method = RequestMethod.GET)
    public StatusResponse getSession(HttpSession session){

        BackOfficeUser user = (BackOfficeUser) session.getAttribute(BO_SESSION);
        return new StatusResponse<BackOfficeUser>(user != null? Status.OK : Status.NOK,null,user);
    }


    @RequestMapping(value="", method = RequestMethod.POST)
    public StatusResponse startSession(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            HttpServletRequest request){

        BackOfficeUser user = null;
        try{

            if(session.getAttribute(BO_SESSION) == null){

                user = userRepo.findOne(username);
                if(user == null || user.getWrongPasswordTries() >= BackOfficeUser.MAX_PASSWORD_WRONG_TRIES)
                    return new StatusResponse<BackOfficeUser>(Status.NOK, "Conta bloqueada ou user inexistente",null);
                if(!user.getPassword().equals(password)) {
                    user.bumpWrongPasswordTries();
                    userRepo.save(user);
                    return new StatusResponse<BackOfficeUser>(Status.NOK, "Username ou Password errada", null);
                }
                //prevent session fixation
                session.invalidate();
                request.getSession();

                //reset wrong attempts
                user.resetWrongPasswordTries();
                user.setSessionID(session.getId());
                userRepo.save(user);

                request.getSession().setAttribute(BO_SESSION,user);
            }

        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<BackOfficeUser>(Status.NOK, null);
        }
        return new StatusResponse<BackOfficeUser>(Status.OK, "Login OK",user);
    }


    @RequestMapping(method = RequestMethod.DELETE)
    public StatusResponse endSession(
            HttpServletRequest request){

        BackOfficeUser user = (BackOfficeUser)request.getSession().getAttribute(BO_SESSION);
        if(user != null){
            user.clearSessionID();
            userRepo.save(user);
        }
        request.getSession().removeAttribute(BO_SESSION);

        //session fixation
        request.getSession().invalidate();
        request.getSession();

        return new StatusResponse<BackOfficeUser>(Status.OK, null);
    }


}
