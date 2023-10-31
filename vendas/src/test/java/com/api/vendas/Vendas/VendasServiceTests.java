package com.api.vendas.Vendas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class VendasServiceTests {

    @InjectMocks
    VendasService vendasService;

    @Mock
    VendasRepository vendasRepository;
    @Autowired
    @Mock
    RestTemplate restTemplate;

    @Test
    void test_getVendas(){
        //return list<Vendas>
        Mockito.when(vendasRepository.findAll()).thenReturn(new ArrayList<>());
        List<Venda> resp_empty = vendasService.getVendas();
        Assertions.assertEquals(0,resp_empty.size());

        List<Venda> vendas = new ArrayList<>();
        Venda venda = create_venda();
        vendas.add(venda);
        Mockito.when(vendasRepository.findAll()).thenReturn(vendas);
        List<Venda> resp = vendasService.getVendas();
        Assertions.assertEquals(resp.get(0).getCpfCliente(),"321");
    }

    @Test
    void test_getVendasByCpfCorretor(){
        List<Venda> vendas = new ArrayList<>();
        vendas.add(create_venda());
        Mockito.when(vendasRepository.findByCpfCorretor("456")).thenReturn(new ArrayList<>());
        Mockito.when(vendasRepository.findByCpfCorretor("123")).thenReturn(vendas);
        List<Venda> resp_correct = vendasService.getVendasByCpfCorretor("123");
        List<Venda> resp_wrong = vendasService.getVendasByCpfCorretor("456");
        Assertions.assertEquals(resp_correct.get(0).getCpfCorretor(), "123");
        Assertions.assertEquals(resp_wrong.size(),0);
    }
//
    @Test
    void test_getVendasByCpfClient(){
        List<Venda> vendas = new ArrayList<>();
        vendas.add(create_venda());
        Mockito.when(vendasRepository.findByCpfCliente("456")).thenReturn(new ArrayList<>());
        Mockito.when(vendasRepository.findByCpfCliente("321")).thenReturn(vendas);
        List<Venda> resp_correct = vendasService.getVendasByCpfCliente("321");
        List<Venda> resp_wrong = vendasService.getVendasByCpfCliente("456");
        Assertions.assertEquals(resp_correct.get(0).getVendaStatus(), "SUCESS");
        Assertions.assertEquals(resp_wrong.size(),0);
    }
//
    @Test
    void test_getVendasByStatus(){
        List<Venda> vendas = new ArrayList<>();
        vendas.add(create_venda());
        Mockito.when(vendasRepository.findByVendaStatus("ERROR")).thenReturn(new ArrayList<>());
        Mockito.when(vendasRepository.findByVendaStatus("SUCESS")).thenReturn(vendas);
        List<Venda> resp_correct = vendasService.getVendasByStatus("SUCESS");
        List<Venda> resp_wrong = vendasService.getVendasByStatus("ERROR");
        Assertions.assertEquals(resp_correct.get(0).getId(), "1");
        Assertions.assertEquals(resp_wrong.size(),0);
    }
    @Test
    void test_postVenda(){
        VendaCreateDTO vendaCreateDTO = new VendaCreateDTO();
        vendaCreateDTO.setCpfCliente("321");
        vendaCreateDTO.setCpfCorretor("123");
        vendaCreateDTO.setIdenifierImovel("1");
        ImoveisDTO imoveisDTO = new ImoveisDTO();
        imoveisDTO.setIdentifier("1");
        CorretorDTO corretorDTO = new CorretorDTO();
        corretorDTO.setCpf("123");
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf("321");
        Venda venda = create_venda();

        HttpHeaders headers = new HttpHeaders();

        headers.add("token", "100");

        HttpEntity httpEntity = new HttpEntity<>(headers);

        Mockito.lenient().when(restTemplate.exchange("http://localhost:8081/imoveis/1", HttpMethod.GET,httpEntity, ImoveisDTO.class)).thenReturn(new ResponseEntity<>(imoveisDTO, HttpStatus.OK));
        Mockito.lenient().when(restTemplate.exchange("http://localhost:8081/corretor/cpf/123",HttpMethod.GET,httpEntity, CorretorDTO.class)).thenReturn(new ResponseEntity<>(corretorDTO, HttpStatus.OK));
        Mockito.lenient().when(restTemplate.exchange("http://localhost:8081/cliente/321", HttpMethod.GET,httpEntity,ClienteDTO.class)).thenReturn(new ResponseEntity<>(clienteDTO,HttpStatus.OK));
        Mockito.lenient().when(vendasRepository.save(Mockito.any(Venda.class))).thenReturn(venda);

        Venda resp1 = vendasService.postVenda(vendaCreateDTO, "100");
        Assertions.assertEquals(resp1.getImovelIdentifier(),"1");
    }

    private Venda create_venda(){
        Venda venda = new Venda();
        venda.setId("1");
        venda.setImovelIdentifier("1");
        venda.setCpfCorretor("123");
        venda.setCpfCliente("321");
        venda.setVendaStatus("SUCESS");
        return venda;
    }
}