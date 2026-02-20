package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        // Páginas públicas
        if (uri.contains("login") || uri.contains("css") || uri.contains("js")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);

        if (session == null ||
            session.getAttribute("usuarioLogado") == null) {

            resp.sendRedirect("login");
            return;
        }

        String perfil = (String) session.getAttribute("perfil");

        // 🔒 Apenas ADMIN pode acessar /usuario
        if (uri.contains("/usuario") && !"ADMIN".equals(perfil)) {
            resp.sendRedirect("dashboard");
            return;
        }

        chain.doFilter(request, response);
    }
}

