package com.api.vendas.Vendas.exception;

public class ImovelNotDFoundException extends  RuntimeException{

    public ImovelNotDFoundException(){super("Não foi possível encontrar o imóvel!");}

}
