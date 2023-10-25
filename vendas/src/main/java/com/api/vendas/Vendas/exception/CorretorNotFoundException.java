package com.api.vendas.Vendas.exception;

public class CorretorNotFoundException extends RuntimeException{
    public CorretorNotFoundException(){super("Não foi possível encontrar o corretor!");}

}
