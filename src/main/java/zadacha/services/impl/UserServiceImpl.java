package zadacha.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zadacha.entities.User;
import zadacha.exceptions.UserAlreadyExistsException;
import zadacha.exceptions.UserNotFoundException;
import zadacha.exceptions.WrongPasswordException;
import zadacha.repositories.UserRepository;
import zadacha.services.api.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
