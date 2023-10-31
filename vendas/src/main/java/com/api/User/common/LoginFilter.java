package com.api.User.common;

import com.api.User.user.dto.ReturnUserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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

        ResponseEntity<ReturnUserDTO> res = restTemplate.getForEntity("http://54.71.150.144:8082/token/" + token, ReturnUserDTO.class);

        if (res.getStatusCode().is2xxSuccessful()){
            chain.doFilter(request, response);
        } else {
            throw new RuntimeException("User not found");
        }

    }

}
