package htw.berlin.budgetapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(Endpoints.INDEX).setViewName(ViewNames.INDEX);
        registry.addViewController(Endpoints.LOGIN).setViewName(ViewNames.LOGIN);
        registry.addViewController(Endpoints.ACCOUNTS).setViewName(ViewNames.ACCOUNTS);
        registry.addViewController(Endpoints.ENTRIES).setViewName(ViewNames.ENTRIES);
    }

}
