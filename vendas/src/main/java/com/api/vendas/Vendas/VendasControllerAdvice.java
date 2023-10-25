package com.api.vendas.Vendas;

import com.api.vendas.Vendas.exception.ImovelNotDFoundException;
import com.api.vendas.common.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class VendasControllerAdvice {

    @ExceptionHandler(ImovelNotDFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO imovelNotFoundException (ImovelNotDFoundException ex){

        ErrorDTO errorDTO =  new ErrorDTO();

        errorDTO.setMessage(ex.getMessage());
        errorDTO.setCode(400);
        errorDTO.setTime(LocalDateTime.now());
        return errorDTO;

    }
}
