package htw.berlin.budgetapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedMethods("*")
                .allowedOrigins(
                        "http://localhost:3000",
                        "https://budget-app-yan-frontend.herokuapp.com"
                );
    }

//    registry.addViewController(Endpoints.INDEX).setViewName(ViewNames.INDEX);
//    registry.addViewController(Endpoints.SLASH_INDEX).setViewName(ViewNames.INDEX);
//    registry.addViewController(Endpoints.ACCOUNTS).setViewName(ViewNames.ACCOUNTS);
//    registry.addViewController(Endpoints.CREATEACCOUNT).setViewName(ViewNames.CREATEACCOUNT);
//    registry.addViewController(Endpoints.ENTRIES).setViewName(ViewNames.ENTRIES);

}
