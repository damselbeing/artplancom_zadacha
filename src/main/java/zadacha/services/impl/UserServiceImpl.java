package zadacha.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zadacha.entities.User;
import zadacha.exceptions.RequestException;
import zadacha.exceptions.UserAlreadyExistsException;
import zadacha.exceptions.UserNotFoundException;
import zadacha.exceptions.WrongPasswordException;
import zadacha.repositories.UserRepository;
import zadacha.services.api.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public boolean createUser(User newUser)
            throws UserAlreadyExistsException {

        if(userRepository.existsUserByName(newUser.getName().toUpperCase())) {
            throw new UserAlreadyExistsException();
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setName(newUser.getName().toUpperCase());
        newUser.setAccountNonLocked(true);
        newUser.setFailedAttempt(0);
        newUser.setLockTime(LocalDateTime.now().minusYears(LOCK_TIME_RESET));

        userRepository.save(newUser);
        return true;
    }

    @Transactional
    public void authWithHttpServletRequest(HttpServletRequest request,
                                            String username,
                                            String password)
            throws RequestException {

            try {
                request.login(username, password);
            } catch (ServletException e) {
                throw new RequestException();
            }

    }

    @Transactional
    public boolean loginUser(User user)
            throws UserNotFoundException, WrongPasswordException {

        User userFromDB = userRepository
                .findUserByName(user.getName().toUpperCase())
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(user.getPassword(), userFromDB.getPassword())) {
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

    @Transactional
    public User getUserByName(String name) {
        return userRepository.findUserByName(name.toUpperCase()).orElse(null);
    }

    @Transactional
    public void increaseFailedAttempts(User user) {
        int newFailAttempts = user.getFailedAttempt() + 1;
        userRepository.updateFailedAttempts(newFailAttempts, user.getName());
    }

    @Transactional
    public void resetFailedAttempts(String name) {
        userRepository.updateFailedAttempts(0, name);
    }

    @Transactional
    public void lock(User user) {
        user.setAccountNonLocked(false);
        user.setLockTime(LocalDateTime.now());

        userRepository.save(user);
    }

    @Transactional
    public boolean unlockWhenTimeExpired(User user) {
        LocalDateTime lockTime = user.getLockTime();
        LocalDateTime currentTime = LocalDateTime.now();
        long diff = ChronoUnit.SECONDS.between(lockTime, currentTime);

        if (diff > LOCK_TIME_DURATION) {
            user.setAccountNonLocked(true);
            user.setLockTime(LocalDateTime.now().minusYears(LOCK_TIME_RESET));
            user.setFailedAttempt(0);

            userRepository.save(user);

            return true;
        }

        return false;
    }
}
