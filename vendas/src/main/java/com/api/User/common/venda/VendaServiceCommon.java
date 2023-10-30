package com.api.User.common.venda;

import com.api.User.user.dto.ReturnUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class VendaServiceCommon {

    public void validarUsuario(String token){

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<ReturnUserDTO> response = restTemplate.getForEntity("http://54.71.150.144:8082/token/" + token, ReturnUserDTO.class);

            // Se a resposta não for bem-sucedida, lance uma exceção indicando token inválido
            if (!response.getStatusCode().is2xxSuccessful()){
                throw new RuntimeException("Invalid token or user not found");
            }
        } catch (Exception e) {
            // Trate quaisquer outras exceções (como erros de rede) lançando uma exceção em tempo de execução
            throw new RuntimeException("Error validating token: " + e.getMessage());
        }
    }

}
