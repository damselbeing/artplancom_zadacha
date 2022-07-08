package zadacha.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zadacha.entities.User;
import zadacha.exceptions.UserAlreadyExistsException;
import zadacha.exceptions.UserNotFoundException;
import zadacha.exceptions.WrongPasswordException;
import zadacha.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userFromDB = userRepository
                .findUserByName(username.toUpperCase())
                .orElseThrow(() -> new UsernameNotFoundException("Error: the username is not found!"));

        UserDetails user = org.springframework.security.core.userdetails.User.builder()
                .username(userFromDB.getName())
                .password(userFromDB.getPassword())
                .build();

        return user;
    }

    @Transactional
    public boolean createUser(User newUser) throws UserAlreadyExistsException {

        if(userRepository.existsUserByName(newUser.getName().toUpperCase())) {
            throw new UserAlreadyExistsException();
        }

        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser.setName(newUser.getName().toUpperCase());
        userRepository.save(newUser);
        return true;
    }

    @Transactional
    public boolean loginUser(User user)
            throws UserNotFoundException, WrongPasswordException {

        User userFromDB = userRepository
                .findUserByName(user.getName().toUpperCase())
                .orElseThrow(UserNotFoundException::new);

        if (!bCryptPasswordEncoder.matches(user.getPassword(), userFromDB.getPassword())) {
            throw new WrongPasswordException();
        }

        return true;
    }

    @Transactional
    public boolean checkName(String name) throws UserAlreadyExistsException {

        if(userRepository.existsUserByName(name.toUpperCase())) {
            throw new UserAlreadyExistsException();
        }

        return true;
    }



}
