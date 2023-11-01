package com.api.vendas.Vendas;

import com.api.vendas.Vendas.exception.ImovelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VendasService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private VendasRepository vendasRepository;

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


    public Venda postVenda(VendaCreateDTO vendaCreateDTO, String token){

        Venda venda = new Venda();


        venda.setVendaStatus("SUCESSO");

        HttpHeaders headers = new HttpHeaders();

        headers.add("token", token);


        HttpEntity httpEntity = new HttpEntity<>(headers);


        ResponseEntity<ImoveisDTO> responseImoveis =  restTemplate.exchange("http://3.134.139.76:8080/imovel/" + vendaCreateDTO.getIdenifierImovel(),HttpMethod.GET,httpEntity, ImoveisDTO.class);
        if (!responseImoveis.getStatusCode().is2xxSuccessful()) {
            venda.setVendaStatus("ERRO");
        }

        venda.setImovelIdentifier(responseImoveis.getBody().getIdentifier());

        ResponseEntity<CorretorDTO> responseCorretor =
                restTemplate.exchange("http://35.87.155.27:8080/corretor/cpf/" + vendaCreateDTO.getCpfCorretor(),HttpMethod.GET, httpEntity, CorretorDTO.class);
        if (!responseCorretor.getStatusCode().is2xxSuccessful()) {
            venda.setVendaStatus("ERRO");
        }

        venda.setCpfCorretor(responseCorretor.getBody().getCpf());


        ResponseEntity<ClienteDTO> responseCliente =
                restTemplate.exchange("http://34.210.87.17:8080/clientes/" + vendaCreateDTO.getCpfCliente(),HttpMethod.GET, httpEntity, ClienteDTO.class);
        if(! responseCliente.getStatusCode().is2xxSuccessful()){
            venda.setVendaStatus("ERRO");
        }
        venda.setCpfCliente(responseCliente.getBody().getCpf());


        return vendasRepository.save(venda);

    }

}