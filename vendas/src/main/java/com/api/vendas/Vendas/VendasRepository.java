package com.api.vendas.Vendas;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendasRepository extends MongoRepository<Venda, String> {
    List<Venda> findByLocatorCpf(String locatorCpf);
    List<Venda> findByUserCpf(String userCpf);
    List<Venda> findByPropertyIdentifier(String propertyIdentifier);
}
