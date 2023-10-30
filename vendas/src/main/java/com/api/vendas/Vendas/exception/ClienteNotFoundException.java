package com.api.vendas.Vendas.exception;

public class ClienteNotFoundException extends RuntimeException{
    public ClienteNotFoundException() { super("Não foi possível encontrar o cliente!");}
}
