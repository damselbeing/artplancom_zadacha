package zadacha.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                    .disable()                                                                                                                                                                                                          //NOSONAR not used in secure contexts
                .authorizeRequests()
                    .antMatchers("/animals/**").fullyAuthenticated()
                    .antMatchers("/validation/**").not().fullyAuthenticated()
                    .antMatchers("/registration").not().fullyAuthenticated()
                    .antMatchers("/login").permitAll()
                .and()
                .formLogin()
                    .defaultSuccessUrl("/animals")
                .and()
                .logout();
        return httpSecurity.build();

    }





}
