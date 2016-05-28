package pt.vejasaude.web.services.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;
import pt.vejasaude.web.services.session.request.ChangePasswordRequest;
import pt.vejasaude.web.services.session.request.SessionRequest;
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

    public final static String BO_SESSION = "BO_SESSION";


    @RequestMapping(method = RequestMethod.GET)
    public StatusResponse getSession(HttpSession session){

        BackOfficeUser user = (BackOfficeUser) session.getAttribute(BO_SESSION);
        return new StatusResponse<BackOfficeUser>(user != null? Status.OK : Status.NOK,null,user);
    }


    @RequestMapping(value="", method = RequestMethod.POST)
    public StatusResponse startSession(
            @RequestBody SessionRequest login,
            HttpSession session,
            HttpServletRequest request){

        String username = login.getUsername(),
                password = login.getPassword();

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

    /**
     * End the present session, if any
     * @param request
     * @return
     */
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

    @RequestMapping(value="/password", method = RequestMethod.POST)
    public StatusResponse changePassword(
            HttpServletRequest request,
            @RequestBody ChangePasswordRequest req){

        if(req.getCurrentPassword().isEmpty() || req.getNewPassword().isEmpty())
            return new StatusResponse<BackOfficeUser>(Status.NOK, "Preencha todos os campos");

        if(req.getCurrentPassword().equals(req.getNewPassword()))
            return new StatusResponse<BackOfficeUser>(Status.NOK, "A atual e nova password são idênticas");

        BackOfficeUser user = (BackOfficeUser) request.getSession().getAttribute(BO_SESSION);
        if(user == null || user.getUsername().isEmpty())
            return new StatusResponse<BackOfficeUser>(Status.NOK, "Não foi possível alterar a sua password");

        if(!user.getPassword().equals(req.getCurrentPassword()))
            return new StatusResponse<BackOfficeUser>(Status.NOK, "A password atual inserida não coincide com a password atual do utilizador");

        user.setPassword(req.getNewPassword());
        userRepo.save(user);

        return new StatusResponse<BackOfficeUser>(Status.OK, null, user);
    }

    @RequestMapping(value="/denied",method = RequestMethod.GET)
    public StatusResponse unallowed(){
        return new StatusResponse(Status.NOT_AUTHENTICATED,"No session found");
    }


}
