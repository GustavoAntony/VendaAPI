package com.api.vendas.Vendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendasController {
    @Autowired
    private VendasService vendasService;

    public List<Venda> getVendas() { // Is DTO required?
        return vendasService.getVendas();
    }
}
