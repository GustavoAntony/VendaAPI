package com.api.vendas.Vendas;

import com.api.vendas.Vendas.exception.ImovelNotDFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VendasService {
    @Autowired
    private VendasRepository vendasRepository;

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


    public Venda postVenda(String imovelIdentifier, String corretorIdentifier, String clienteIdentifier){

        Venda venda = new Venda();

       RestTemplate restTemplate = new RestTemplate();

        venda.setVendaStatus("SUCESSO");

        ResponseEntity<ImoveisDTO> responseImoveis =
                restTemplate.getForEntity("http://localhost:8080/imevoeis/" + imovelIdentifier, ImoveisDTO.class);
        if (!responseImoveis.getStatusCode().is2xxSuccessful()) {
            venda.setVendaStatus("ERRO");
        }

        venda.setImovelIdentifier(responseImoveis.getBody().getIdentifier());

        ResponseEntity<CorretorDTO> responseCorretor =
                restTemplate.getForEntity("http://localhost:8080/corretor/" + corretorIdentifier, CorretorDTO.class);
        if (!responseCorretor.getStatusCode().is2xxSuccessful()) {
            venda.setVendaStatus("ERRO");
        }

        venda.setCpfCorretor(responseCorretor.getCpfCorretor());

        ResponseEntity<ClienteDTO> responseCliente =
                restTemplate.getForEntity("http://localhost:8080/cliente/" + clienteIdentifier, ClienteDTO.class);
        if(! responseCliente.getStatusCode().is2xxSuccessful()){
            venda.setVendaStatus("ERRO");
        }
        venda.setCpfCliente(responseCliente.getCpfCliente());


        return vendasRepository.save(venda);

    }

}