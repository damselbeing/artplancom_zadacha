package zadacha.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zadacha.entities.User;
import zadacha.exceptions.UserAlreadyExistsException;
import zadacha.exceptions.UserNotFoundException;
import zadacha.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {

        User userFromDB = userRepository
                .findUserByName(username)
                .orElseThrow(UserNotFoundException::new);

        UserDetails user = org.springframework.security.core.userdetails.User.builder()
                .username(userFromDB.getName())
                .password(userFromDB.getPassword())
                .build();

        return user;
    }

    @Transactional
    public boolean createUser(User newUser) throws UserAlreadyExistsException {

        if(userRepository.existsUserByName(newUser.getName())) {
            throw new UserAlreadyExistsException();
        }

        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        return true;
    }

    @Transactional
    public boolean loginUser(User user) throws UserNotFoundException {

        User userFromDB = userRepository
                .findUserByName(user.getName())
                .orElseThrow(UserNotFoundException::new);

        if (bCryptPasswordEncoder.matches(user.getPassword(), userFromDB.getPassword())) {
            return true;
        }

        return false;
    }




}
