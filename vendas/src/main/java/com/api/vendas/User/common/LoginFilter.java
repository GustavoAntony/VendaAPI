package com.api.vendas.User.common;

import com.api.vendas.User.user.dto.ReturnUserDTO;
import com.api.vendas.User.user.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

        if (req.getMethod().equals("OPTIONS")) {
            chain.doFilter(request, response);
        } else {


            String token = req.getHeader("token");

            RestTemplate restTemplate = new RestTemplate();

            try {

                ResponseEntity<ReturnUserDTO> res = restTemplate.getForEntity("http://54.71.150.144:8082/token/" + token, ReturnUserDTO.class);
                if (!res.getStatusCode().is2xxSuccessful()) {
                    ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "O token informado é inválido");}
            } catch (Exception e) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "O token informado é inválido");
            }
            chain.doFilter(request, response);
        }
    }

}
