package projetos.kev.remio.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import projetos.kev.remio.components.LoginInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

//    @Override
//    public void addInterceptors (InterceptorRegistry registry){
//        registry.addInterceptor(loginInterceptor).excludePathPatterns(
//                "/login",
//                "/login-view",
//                "/error",
//                "/register-view"
//        );
//    }
//    public void addViewController (ViewControllerRegistry registry){
////        registry.addViewController("/login-view").setViewName("login");
//        registry.addViewController("/register-view").setViewName("register");
//        registry.addViewController("/home-view").setViewName("home");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }
}
