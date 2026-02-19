package controller;

import dao.UsuarioSistemaDAO;
import model.UsuarioSistema;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UsuarioSistemaDAO dao = new UsuarioSistemaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("login.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String login = req.getParameter("login");
        String senha = req.getParameter("senha");

        UsuarioSistema usuario = dao.autenticar(login, senha);

        if (usuario != null) {

            HttpSession session = req.getSession();
            session.setAttribute("usuarioLogado", usuario);

            resp.sendRedirect("dashboard");

        } else {

            resp.sendRedirect("login?erro=true");
        }
    }
}

