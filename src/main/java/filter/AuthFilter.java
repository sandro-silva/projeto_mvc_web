package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import java.io.IOException;

@WebFilter({"/dashboard", "/usuario"})
public class AuthFilter implements Filter {

    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        if (session == null ||
            session.getAttribute("usuarioLogado") == null) {

            resp.sendRedirect("login");
            return;
        }

        chain.doFilter(request, response);
    }
}

