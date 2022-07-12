package zadacha.services.api;

import zadacha.entities.User;
import zadacha.exceptions.UserAlreadyExistsException;
import zadacha.exceptions.UserNotFoundException;
import zadacha.exceptions.WrongPasswordException;

public interface UserService {

    int MAX_FAILED_ATTEMPTS = 10;
    long LOCK_TIME_DURATION = 1 * 60 * 60; // 1 hour
    long LOCK_TIME_RESET = 1; // 1 year

    boolean createUser(User newUser) throws UserAlreadyExistsException;
    boolean loginUser(User user) throws UserNotFoundException, WrongPasswordException;
    boolean checkName(String name) throws UserAlreadyExistsException;
    User getUserByName(String name);

    void increaseFailedAttempts(User user);
    void resetFailedAttempts(String name);
    void lock(User user);
    boolean unlockWhenTimeExpired(User user);

}
