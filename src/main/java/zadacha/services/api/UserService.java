package zadacha.services.api;

import zadacha.entities.User;
import zadacha.exceptions.UserAlreadyExistsException;
import zadacha.exceptions.UserNotFoundException;
import zadacha.exceptions.WrongPasswordException;

public interface UserService {

    boolean createUser(User newUser) throws UserAlreadyExistsException;
    boolean loginUser(User user) throws UserNotFoundException, WrongPasswordException;
    boolean checkName(String name) throws UserAlreadyExistsException;
}
