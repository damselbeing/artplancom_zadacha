package zadacha.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import zadacha.entities.User;
import zadacha.exceptions.UserLockedException;
import zadacha.services.api.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException {

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

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put("statusCode", HttpStatus.UNAUTHORIZED.value());
        data.put("message", exception.getMessage());
        response.getOutputStream().println(objectMapper.writeValueAsString(data));
    }

}
