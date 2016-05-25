package pt.vejasaude.bo.services.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.vejasaude.Utils;
import pt.vejasaude.bo.services.generic.Status;
import pt.vejasaude.bo.services.generic.StatusResponse;
import pt.vejasaude.bo.services.user.request.CreateNewUserRequest;
import pt.vejasaude.unified.data.backofficeuser.IBackOfficeUserRepository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by NB20301 on 25/05/2016.
 */
@RestController
@RequestMapping("/api/v1/bouser")
public class BackOfficeUser {

    @Autowired
    IBackOfficeUserRepository userRepo;


    /**
     * Creates a new back office user
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public StatusResponse<pt.vejasaude.unified.data.backofficeuser.BackOfficeUser> createUser(
            @RequestBody CreateNewUserRequest user){

        if(user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty() || user.getPrettyName().isEmpty())
            return new StatusResponse<pt.vejasaude.unified.data.backofficeuser.BackOfficeUser>(Status.NOK,"Preencha todos os campos obrigatórios");

        if(!Utils.validateEmail(user.getEmail()))
            return new StatusResponse<pt.vejasaude.unified.data.backofficeuser.BackOfficeUser>(Status.NOK,"Email inválido");

        if(user.getPassword().length() < pt.vejasaude.unified.data.backofficeuser.BackOfficeUser.PASSWORD_MIN_CHARS)
            return new StatusResponse<pt.vejasaude.unified.data.backofficeuser.BackOfficeUser>(Status.NOK,"A password deve ter um mínimo de 8 caractéres");

        if(userRepo.findOne(user.getUsername()) != null || userRepo.findByEmail(user.getEmail()) != null)
            return new StatusResponse<pt.vejasaude.unified.data.backofficeuser.BackOfficeUser>(Status.NOK,"Já existe um utilizador com o mesmo username ou email");

        pt.vejasaude.unified.data.backofficeuser.BackOfficeUser boUser = null;
        try{
            boUser = new pt.vejasaude.unified.data.backofficeuser.BackOfficeUser(user);
            userRepo.save(boUser);

        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse<pt.vejasaude.unified.data.backofficeuser.BackOfficeUser>(Status.NOK,null);
        }

        return new StatusResponse<pt.vejasaude.unified.data.backofficeuser.BackOfficeUser>(Status.OK, null, boUser);
    }

    /**
     * Returns all the back office users
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public StatusResponse getAll(){

        Iterable<pt.vejasaude.unified.data.backofficeuser.BackOfficeUser> users = null;

        try{
            users = userRepo.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse(Status.NOK,null);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.

        return new StatusResponse<Iterable<pt.vejasaude.unified.data.backofficeuser.BackOfficeUser>>(Status.OK,null,users);
    }

}
