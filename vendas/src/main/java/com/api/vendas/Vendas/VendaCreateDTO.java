package com.api.vendas.Vendas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaCreateDTO {

    private String cpfCliente;
    private String cpfCorretor;
    private String imovelIdentifier;

}
