package pt.vejasaude.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nuno on 17/05/2016.
 */

@RestController("/api/v1")
public class MainController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String home(){
        return "Hello!!!";
    }



}
