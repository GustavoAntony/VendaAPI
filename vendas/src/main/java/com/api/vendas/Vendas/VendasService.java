package com.api.vendas.Vendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendasService {
    @Autowired
    private VendasRepository vendasRepository;

    public List<Venda> getVendas() {
        return vendasRepository.findAll();
    }
}
