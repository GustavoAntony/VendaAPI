package com.api.vendas.User.user;

import com.api.vendas.User.user.exception.TokenNotPassedException;
import com.api.vendas.User.user.exception.UserNotFoundException;
import com.api.vendas.User.common.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
@ControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO userNotFoundException(UserNotFoundException ex) {
        ErrorDTO error = new ErrorDTO();
        error.setMessage(ex.getMessage());
        error.setCode(401);
        error.setTime(LocalDateTime.now());
        return error;
    }

    @ExceptionHandler(TokenNotPassedException.class)
    @ResponseBody
    @ResponseStatus
    public ErrorDTO tokenNotPassedException(TokenNotPassedException ex){
        ErrorDTO error = new ErrorDTO();
        error.setMessage(ex.getMessage());
        error.setCode(400);
        error.setTime(LocalDateTime.now());
        return error;
    }

}
