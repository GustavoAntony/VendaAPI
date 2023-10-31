package com.api.User.common;

import com.api.User.common.venda.VendaServiceCommon;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;

import java.io.IOException;

@Component
@Order(1)
public class LoginFilter implements Filter {

    @Autowired
    private VendaServiceCommon vendaServiceCommon;

    @Override
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
            String token = ((HttpServletRequest) request).getHeader("token");

            vendaServiceCommon.validarUsuario(token);

            chain.doFilter(request, response);
    }
}
