package com.example.marcosproyecto.config;

import com.example.marcosproyecto.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

// Portero de /admin/**: solo pasa quien tenga un ADMIN en sesión.
@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        Object usuario = (session == null) ? null : session.getAttribute("usuario");
        if (usuario instanceof Usuario u && "ADMIN".equals(u.getRol())) {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }
}
