package com.api.vendas.Vendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VendasService {
    @Autowired
    private VendasRepository vendasRepository;

    RestTemplate restTemplate = new RestTemplate();
    public List<Venda> getVendas() {
        return vendasRepository.findAll();
    }

    public List<Venda> getVendasByPropertyIdentifier(String pid) {
        return vendasRepository.findByPropertyIdentifier(pid);
    }

    public List<Venda> getVendasByLocatorCpf(String locatorCpf) {
        return vendasRepository.findByLocatorCpf(locatorCpf);
    }
    public List<Venda> getVendasByUserCpf(String userCpf) {
        return vendasRepository.findByLocatorCpf(userCpf);
    }

}
