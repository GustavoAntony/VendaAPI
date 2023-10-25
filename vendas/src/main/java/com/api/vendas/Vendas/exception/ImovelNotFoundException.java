package com.api.vendas.Vendas.exception;

public class ImovelNotFoundException extends  RuntimeException{
    public ImovelNotFoundException(){super("Não foi possível encontrar o imóvel!");}

}
