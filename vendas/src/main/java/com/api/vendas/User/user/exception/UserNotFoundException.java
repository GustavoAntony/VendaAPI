package com.api.vendas.User.user.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() { super("User não encontrado!");}
}
