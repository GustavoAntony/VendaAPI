package com.api.User.user.exception;

public class TokenNotPassedException extends RuntimeException{
    public TokenNotPassedException(){super("O Token não foi passado corretamente!");}
}
