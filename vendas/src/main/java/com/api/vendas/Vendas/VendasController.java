package com.api.vendas.Vendas;

import com.api.vendas.Vendas.exception.VendaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendasController {
    @Autowired
    private VendasService vendasService;

    public List<Venda> getVendas() { // Is DTO required?
        return vendasService.getVendas();
    }
//    @RequestMapping("/{PropertyId}")
//    public List<Venda> getVendasByPropertyId(@RequestParam String propertyId) { // Is DTO required?
//        List<Venda> vendas = vendasService.getVendasByPropertyIdentifier(propertyId);
//        if (vendas.isEmpty()) {
//            throw new VendaNotFoundException();
//        }
//        return vendas;
//    }
    @RequestMapping("/corretor")
    public List<Venda> getVendasByCorretorCpf(@RequestParam String corretorCpf) { // Is DTO required?
        return vendasService.getVendasByCpfCorretor(corretorCpf);
    }
    @RequestMapping("/cliente")
    public List<Venda> getVendasByClienteCpf(@RequestParam String clienteCpf) { // Is DTO required?
        return vendasService.getVendasByCpfCliente(clienteCpf);
    }

    @RequestMapping("/status")
    public List<Venda> getVendasByStatus(@RequestParam String status) { // Is DTO required?
        return vendasService.getVendasByStatus(status);
    }


    @PostMapping
    public Venda criaVenda (@RequestBody VendaCreateDTO venda){
        return vendasService.postVenda(venda);
    }


}
