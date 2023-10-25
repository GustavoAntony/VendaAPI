package com.api.vendas.Vendas;

import com.api.vendas.Vendas.exception.VendaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping("/{PropertyId}")
    public List<Venda> getVendasByPropertyId(@RequestParam String propertyId) { // Is DTO required?
        List<Venda> vendas = vendasService.getVendasByPropertyIdentifier(propertyId);
        if (vendas.isEmpty()) {
            throw new VendaNotFoundException();
        }
        return vendas;
    }
    @RequestMapping("/Locator")
    public List<Venda> getVendasByLocatorCpf(@RequestParam String locatorCpf) { // Is DTO required?
        return vendasService.getVendasByLocatorCpf(locatorCpf);
    }
    @RequestMapping("/User")
    public List<Venda> getVendasByUserCpf(@RequestParam String userCpf) { // Is DTO required?
        return vendasService.getVendasByLocatorCpf(userCpf);
    }
}
