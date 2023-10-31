package com.api.vendas.Vendas;

import com.api.vendas.Vendas.exception.ClienteNotFoundException;
import com.api.vendas.Vendas.exception.CorretorNotFoundException;
import com.api.vendas.Vendas.exception.VendaNotFoundException;
import com.api.vendas.common.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
@ControllerAdvice
public class VendaControllerAdvice {
    @ExceptionHandler(VendaNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO vendaNotFoundException(VendaNotFoundException ex) {
        ErrorDTO error = new ErrorDTO();
        error.setMessage(ex.getMessage());
        error.setCode(404);
        error.setTime(LocalDateTime.now());
        return error;
    }

    @ExceptionHandler(CorretorNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO corretorNotFoundException(CorretorNotFoundException ex){
        ErrorDTO error = new ErrorDTO();
        error.setMessage(ex.getMessage());
        error.setCode(400);
        error.setTime(LocalDateTime.now());
        return error;
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO clienteNotFoundException(ClienteNotFoundException ex){
        ErrorDTO error = new ErrorDTO();
        error.setMessage(ex.getMessage());
        error.setCode(400);
        error.setTime(LocalDateTime.now());
        return error;
    }

}
