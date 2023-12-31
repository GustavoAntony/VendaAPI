package com.api.vendas.Vendas;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("venda")
public class Venda {
    @Id
    private String id;
    private String imovelIdentifier;
    private String cpfCliente;
    private String cpfCorretor;
    private String vendaStatus;
    private LocalDateTime data;


}
