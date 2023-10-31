package com.api.vendas.Vendas;

import com.api.vendas.Vendas.exception.ImovelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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



    public List<Venda> getVendasByCpfCorretor(String corretorCpf) {
        return vendasRepository.findByCpfCorretor(corretorCpf);
    }
    public List<Venda> getVendasByCpfCliente(String clienteCpf) {
        return vendasRepository.findByCpfCliente(clienteCpf);
    }

    public List<Venda> getVendasByStatus(String status) {
        return vendasRepository.findByVendaStatus(status);
    }


    public Venda postVenda(VendaCreateDTO vendaCreateDTO){

        Venda venda = new Venda();

       RestTemplate restTemplate = new RestTemplate();

        venda.setVendaStatus("SUCESSO");

        ResponseEntity<ImoveisDTO> responseImoveis =
                restTemplate.getForEntity("http://localhost:8081/imoveis/" + vendaCreateDTO.getIdenifierImovel(), ImoveisDTO.class);
        if (!responseImoveis.getStatusCode().is2xxSuccessful()) {
            venda.setVendaStatus("ERRO");
            throw new ImovelNotFoundException();
        }

        venda.setImovelIdentifier(responseImoveis.getBody().getIdentifier());

        ResponseEntity<CorretorDTO> responseCorretor =
                restTemplate.getForEntity("http://localhost:8081/corretor/cpf/" + vendaCreateDTO.getCpfCorretor(), CorretorDTO.class);
        if (!responseCorretor.getStatusCode().is2xxSuccessful()) {
            venda.setVendaStatus("ERRO");
        }

        venda.setCpfCorretor(responseCorretor.getBody().getCpf());

        ResponseEntity<ClienteDTO> responseCliente =
                restTemplate.getForEntity("http://localhost:8081/cliente/" + vendaCreateDTO.getCpfCliente(), ClienteDTO.class);
        if(! responseCliente.getStatusCode().is2xxSuccessful()){
            venda.setVendaStatus("ERRO");
        }
        venda.setCpfCliente(responseCliente.getBody().getCpf());


        return vendasRepository.save(venda);

    }

}