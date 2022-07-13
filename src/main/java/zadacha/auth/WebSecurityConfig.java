package zadacha.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomSuccessHandler loginSuccessHandler() {
        return new CustomSuccessHandler();
    }

    @Bean
    public CustomFailureHandler loginFailureHandler() {
        return new CustomFailureHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/animals/**").authenticated()
                    .antMatchers("/validation/**").not().authenticated()
                    .antMatchers("/registration").not().authenticated()
//                    .antMatchers("/login").permitAll()
                .and()
                .formLogin()
                    .successHandler(loginSuccessHandler())
                    .failureHandler(loginFailureHandler())
                .permitAll()
                .and()
                .logout()
                .permitAll();
        return httpSecurity.build();

    }

}
