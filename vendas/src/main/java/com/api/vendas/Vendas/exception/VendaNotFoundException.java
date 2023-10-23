package com.api.vendas.Vendas.exception;

public class VendaNotFoundException extends RuntimeException {
    public VendaNotFoundException() {
        super("Não existem Vendas deste imovél/pessoa!");
    }
}
