package zadacha.exceptions;

import javax.servlet.ServletException;

public class RequestException extends ServletException {

    public RequestException() {

        super("Error while login");
    }
}
