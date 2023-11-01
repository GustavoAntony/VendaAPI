package com.api.vendas.Vendas;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class VendasControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    VendasController vendasController;

    @Mock
    VendasService vendasService;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(vendasController)
                .build();
    }

    @Test
    void test_listVendas() throws Exception{

        Venda venda = new Venda();

        List<Venda> vendas = new ArrayList<>();
        vendas.add(venda);

        HttpHeaders headers = new HttpHeaders();
        headers.add("token","KXtyXGSk1mjD3VGst9ia");
        Mockito.when(vendasService.getVendas()).thenReturn(vendas);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/vendas")
                        .headers(headers))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(vendas), resp);

    }

    @Test
    void test_listVendasByPropertyIdentifier() throws Exception {

        Venda venda = new Venda();
        venda.setVendaStatus("VENDIDO");

        List<Venda> vendas = new ArrayList<>();
        vendas.add(venda);
        HttpHeaders headers = new HttpHeaders();
        headers.add("token","KXtyXGSk1mjD3VGst9ia");
        Mockito.when(vendasService.getVendasByStatus("VENDIDO")).thenReturn(vendas);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/vendas/status").headers(headers)
                .param("status", "VENDIDO"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(vendas), resp);

    }

    @Test
    void test_listVendasByCorretorCpf() throws Exception {

        Venda venda = new Venda();
        venda.setCpfCorretor("1234");

        List<Venda> vendas = new ArrayList<>();
        vendas.add(venda);
        HttpHeaders headers = new HttpHeaders();
        headers.add("token","KXtyXGSk1mjD3VGst9ia");
        Mockito.when(vendasService.getVendasByCpfCorretor("1234")).thenReturn(vendas);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/vendas/corretor").headers(headers)
                        .param("corretorCpf", "1234"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(vendas), resp);
    }

    @Test
    void test_listVendasByClienteCpf() throws Exception {

        Venda venda = new Venda();
        venda.setCpfCliente("1234");

        List<Venda> vendas = new ArrayList<>();
        vendas.add(venda);
        HttpHeaders headers = new HttpHeaders();
        headers.add("token","KXtyXGSk1mjD3VGst9ia");
        Mockito.when(vendasService.getVendasByCpfCliente("1234")).thenReturn(vendas);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/vendas/cliente").headers(headers)
                        .param("clienteCpf", "1234"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(vendas), resp);
    }
    @Test
    void test_criaVendaValida() throws Exception {
        VendaCreateDTO venda = new VendaCreateDTO(); // something wrong here
        venda.setCpfCliente("1245");
        venda.setCpfCorretor("123");
        venda.setIdenifierImovel("3");

        Venda venda2 = new Venda();
        venda2.setCpfCliente("1245");
        venda2.setCpfCorretor("123");
        venda2.setImovelIdentifier("3");
        venda2.setVendaStatus("SUCESSO");

        HttpHeaders headers = new HttpHeaders();
        headers.add("token","KXtyXGSk1mjD3VGst9ia");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(venda);

        Mockito.when(vendasService.postVenda(ArgumentMatchers.any(VendaCreateDTO.class))).thenReturn(venda2);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/vendas").headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        //.characterEncoding("utf-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();
        String resp = result.getResponse().getContentAsString();

        Assertions.assertEquals(om.writeValueAsString(venda2), resp);
    }
}
