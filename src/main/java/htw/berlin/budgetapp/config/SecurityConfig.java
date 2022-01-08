// Template for possible Spring-Security implementation
//
//package htw.berlin.budgetapp.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//@Profile("prod")
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                // public pages
//                .antMatchers(
//                HttpMethod.GET,
//                Endpoints.INDEX,
//                Endpoints.SLASH_INDEX
//                ).permitAll()
//
//                // static Resources
//                .antMatchers(
//                  "/css/**",
//                  "/images/**",
//                  "/js/**"
//                ).permitAll()
//                .anyRequest().authenticated()
//
//                // send the user back to the root page when logout
//                .and()
//                .logout().logoutSuccessUrl("/")
//
//                .and()
//                .oauth2Client()
//                .and()
//                .oauth2Login();
//    }
//}
