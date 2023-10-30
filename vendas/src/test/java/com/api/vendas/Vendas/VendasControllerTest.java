package com.api.vendas.Vendas;

import com.api.vendas.Vendas.Venda;
import com.api.vendas.Vendas.VendasController;
import com.api.vendas.Vendas.VendasService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

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
    void test_listVendas_UmaVenda() throws Exception {

        Venda venda = new Venda();

        List<Venda> vendas = new ArrayList<>();
        vendas.add(venda);

        Mockito.when(vendasService.getVendas()).thenReturn(vendas);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/vendas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(vendas), resp);

    }

    @Test
    void test_listVendasByPropertyIdentifier_UmaVenda() throws Exception {

        Venda venda = new Venda();
        venda.setPropertyIdentifier("123");

        List<Venda> vendas = new ArrayList<>();
        vendas.add(venda);

        Mockito.when(vendasService.getVendasByPropertyIdentifier("123")).thenReturn(vendas);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/vendas/123")
                .param("propertyIdentifier", "123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(vendas), resp);

    }

    @Test
    void test_listVendasByLocatorCpf_UmaVenda() throws Exception {

        Venda venda = new Venda();
        String locatorCpf = venda.getLocatorCpf();

        List<Venda> vendas = new ArrayList<>();
        vendas.add(venda);

        Mockito.when(vendasService.getVendasByLocatorCpf(locatorCpf)).thenReturn(vendas);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/Locator/"+locatorCpf))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(vendas), resp);
    }

}
