package zadacha.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zadacha.entities.User;
import zadacha.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDB = userRepository.getUserByName(username);

        if (userFromDB == null) {
            throw new UsernameNotFoundException("User not found");
        }

        UserDetails user = org.springframework.security.core.userdetails.User.builder()
                .username(userFromDB.getName())
                .password(userFromDB.getPassword())
                .build();

        return user;
    }

    @Transactional
    public boolean saveUser(User user) {
        User userFromDB = userRepository.getUserByName(user.getName());

        if (userFromDB != null) {
            return false;
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }



}
