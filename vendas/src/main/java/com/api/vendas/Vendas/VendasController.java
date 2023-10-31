package com.api.vendas.Vendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendasController {

    @Autowired
    private VendasService vendasService;

    @GetMapping
    public List<Venda> getVendas(@RequestHeader String token) { // Is DTO required?
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

    @GetMapping
    @RequestMapping("/corretor")
    public List<Venda> getVendasByCorretorCpf(@RequestParam String corretorCpf, @RequestHeader String token) { // Is DTO required?
        return vendasService.getVendasByCpfCorretor(corretorCpf);
    }

    @GetMapping
    @RequestMapping("/cliente")
    public List<Venda> getVendasByClienteCpf(@RequestParam String clienteCpf, @RequestHeader String token) { // Is DTO required?
        return vendasService.getVendasByCpfCliente(clienteCpf);
    }

    @GetMapping
    @RequestMapping("/status")
    public List<Venda> getVendasByStatus(@RequestParam String status, @RequestHeader String token) { // Is DTO required?

            return vendasService.getVendasByStatus(status);


    }


    @PostMapping
    public Venda criaVenda (@RequestBody VendaCreateDTO venda, @RequestHeader (name = "token") String token){
        return vendasService.postVenda(venda);
    }


}
