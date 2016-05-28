package pt.vejasaude.web.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.vejasaude.Utils;
import pt.vejasaude.web.services.generic.Status;
import pt.vejasaude.web.services.generic.StatusResponse;
import pt.vejasaude.web.services.user.request.CreateNewUserRequest;
import pt.vejasaude.web.services.user.request.UpdateUserRequest;
import pt.vejasaude.unified.data.backofficeuser.IBackOfficeUserRepository;

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
    public StatusResponse getAll() {

        Iterable<pt.vejasaude.unified.data.backofficeuser.BackOfficeUser> users = null;

        try{
            users = userRepo.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse(Status.NOK,null);
        }

        return new StatusResponse<Iterable<pt.vejasaude.unified.data.backofficeuser.BackOfficeUser>>(Status.OK,null,users);
    }

    /**
     * Updates the properties of a user
     *  Only possible to update the prettyName and email
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public StatusResponse updateUser(
            @PathVariable String id,
            @RequestBody UpdateUserRequest changes){

        pt.vejasaude.unified.data.backofficeuser.BackOfficeUser user = userRepo.findOne(id);
        if(user == null)
            return new StatusResponse(Status.NOK,"O utilizador escolhido não existe");

        if(changes.getPrettyName().isEmpty() && changes.getEmail().isEmpty())
            return new StatusResponse(Status.NOK,"Sem alterações");

        if(!Utils.validateEmail(changes.getEmail()))
            return new StatusResponse(Status.NOK,"Email inválido");

        if(!changes.getEmail().isEmpty())
            user.setEmail(changes.getEmail());

        if(!changes.getPrettyName().isEmpty())
            user.setPrettyName(changes.getPrettyName());

        userRepo.save(user);

        return new StatusResponse<pt.vejasaude.unified.data.backofficeuser.BackOfficeUser>(Status.OK,null,user);
    }

    /**
     * Deletes a given user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public StatusResponse removeUser(@PathVariable String id){

        pt.vejasaude.unified.data.backofficeuser.BackOfficeUser user = userRepo.findOne(id);
        if(user == null)
            return new StatusResponse(Status.NOK,"O utilizador escolhido não existe");

        try{
            userRepo.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            return new StatusResponse(Status.NOK,null);
        }
        return new StatusResponse(Status.OK,null);
    }

}
