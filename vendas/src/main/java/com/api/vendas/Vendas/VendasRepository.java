package com.api.vendas.Vendas;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendasRepository extends MongoRepository<Venda, String> {
    List<Venda> findByCpfCliente(String cilenteCpf);
    List<Venda> findByCpfCorretor(String corretorCpf);
    List<Venda> findByVendaStatus(String status);
}
