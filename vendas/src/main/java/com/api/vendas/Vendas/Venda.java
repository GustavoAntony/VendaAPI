package com.api.vendas.Vendas;

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
    private String locatorCpf;
    private String UserCpf;
    private String propertyIdentifier;
    private String status;
}