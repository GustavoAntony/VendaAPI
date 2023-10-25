package com.api.vendas.Vendas;

import com.api.vendas.Vendas.exception.VendaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendasController {
    @Autowired
    private VendasService vendasService;
    @GetMapping()
    public List<Venda> getVendas() { // Is DTO required?
        return vendasService.getVendas();
    }
    @GetMapping("/{PropertyId}")
    public List<Venda> getVendasByPropertyId(@RequestParam String propertyId) {
        List<Venda> vendas = vendasService.getVendasByPropertyIdentifier(propertyId);
        if (vendas.isEmpty()) {
            throw new VendaNotFoundException();
        }
        return vendas;
    }
    @GetMapping("/Locator")
    public List<Venda> getVendasByLocatorCpf(@RequestParam String locatorCpf) {
        List<Venda> vendas = vendasService.getVendasByLocatorCpf(locatorCpf);
        if (vendas.isEmpty()) {
            throw new VendaNotFoundException();
        }
        return vendas;
    }
    @GetMapping("/User")
    public List<Venda> getVendasByUserCpf(@RequestParam String userCpf) {
        List<Venda> vendas = vendasService.getVendasByUserCpf(userCpf);
        if (vendas.isEmpty()) {
            throw new VendaNotFoundException();
        }
        return vendas;
    }
}
