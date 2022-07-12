package zadacha.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import zadacha.entities.User;
import zadacha.exceptions.UserLockedException;
import zadacha.services.api.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException, UserLockedException {
        String name = request.getParameter("username");
        User user = userService.getUserByName(name);

        if (user != null) {
            if (user.isAccountNonLocked()) {
                if (user.getFailedAttempt() < userService.MAX_FAILED_ATTEMPTS - 1) {
                    userService.increaseFailedAttempts(user);
                } else {
                    userService.lock(user);
                    exception = new UserLockedException(
                            "Your account has been locked due to 3 failed attempts." +
                                    " It will be unlocked after 24 hours.");

                }
            } else if (!user.isAccountNonLocked()) {
                if (userService.unlockWhenTimeExpired(user)) {
                    exception = new UserLockedException(
                            "Your account has been unlocked. " +
                                    "Please try to login again.");
                }
            }

        }


        super.setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);

    }

}
