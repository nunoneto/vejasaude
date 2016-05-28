package pt.vejasaude.web;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pt.vejasaude.unified.data.backofficeuser.BackOfficeUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by NB20301 on 27/05/2016.
 */
@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpSession s = request.getSession();
        String url = request.getRequestURI();
        BackOfficeUser user =(BackOfficeUser)s.getAttribute(pt.vejasaude.web.services.session.Session.BO_SESSION);

        //validates if there is a backoffice user session
        if(user == null){
            System.out.println(url);
            System.out.println("Request with no session");
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpServletResponse.sendRedirect("/api/v1/session/denied");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
