package com.api.User.common;

import com.api.User.common.venda.VendaServiceCommon;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
@Order(1)
public class LoginFilter implements Filter {

    @Autowired
    private VendaServiceCommon vendaServiceCommon;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletRequest res = (HttpServletRequest) response;

        String token = req.getHeader("token");

        try {
            vendaServiceCommon.validarUsuario(token);
            chain.doFilter(request, response);
        } catch (RuntimeException e) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }

}
