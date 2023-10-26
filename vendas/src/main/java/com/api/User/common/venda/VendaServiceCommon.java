package com.api.User.common.venda;

import com.api.User.user.dto.ReturnUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class VendaServiceCommon {

    public void validarUsuario(String token){

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ReturnUserDTO> response = restTemplate.getForEntity("54.71.150.144:8082/token/" + token, ReturnUserDTO.class);

        if (!response.getStatusCode().is2xxSuccessful()){
            throw new RuntimeException("User not found");
        }
    }
}
