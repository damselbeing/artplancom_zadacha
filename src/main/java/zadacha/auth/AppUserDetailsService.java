package zadacha.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zadacha.entities.User;
import zadacha.repositories.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User userFromDB = userRepository
                    .findUserByName(username.toUpperCase())
                    .orElseThrow(() -> new UsernameNotFoundException(
                            "Error: the username is not found!"));

        UserDetails user = new AppUserDetails(userFromDB);

        return user;

    }
}
