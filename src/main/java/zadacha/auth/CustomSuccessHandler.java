package zadacha.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import zadacha.entities.User;
import zadacha.services.api.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        AppUserDetails userDetails =  (AppUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();

        if (user.getFailedAttempt() > 0) {
            userService.resetFailedAttempts(user.getName());
        }

        redirectStrategy.sendRedirect(request, response, "/animals");
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
