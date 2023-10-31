package com.api.vendas.User.common;

import com.api.vendas.User.user.dto.ReturnUserDTO;
import com.api.vendas.User.user.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.api.vendas.User.user.exception.TokenNotPassedException;

import jakarta.servlet.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
@Order(1)
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ReturnUserDTO> res = null;
        try {
            res = restTemplate.getForEntity("http://54.71.150.144:8082/token/" + token, ReturnUserDTO.class);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }

        if (res.getStatusCode().is2xxSuccessful()) {
            chain.doFilter(request, response);
        } else {
            throw new UserNotFoundException();
        }

    }

}
