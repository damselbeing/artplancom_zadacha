package zadacha.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;
import zadacha.exceptions.ErrorMessage;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

//@RestController
//public class MyErrorController implements ErrorController {
//
//    @RequestMapping("/error")
//    public ErrorMessage defaultErrorHandler(HttpServletRequest request, Exception e) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        Integer statusCode = Integer.valueOf(status.toString());
//        e.printStackTrace();
//        return new ErrorMessage(statusCode, e.getMessage());
//    }
//}
