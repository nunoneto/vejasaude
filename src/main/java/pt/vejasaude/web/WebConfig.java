package pt.vejasaude.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by NB20301 on 27/05/2016.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/api/v1/**")
                .excludePathPatterns("/api/v1/session/**");
    }

}
