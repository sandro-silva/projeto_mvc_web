package controller;

import dao.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UsuarioDAO dao = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            int totalUsuarios = dao.contarUsuarios();

            // Envia para o JSP
            req.setAttribute("totalUsuarios", totalUsuarios);

            req.getRequestDispatcher("index.jsp")
               .forward(req, resp);

        } catch (Exception e) {

            e.printStackTrace();

            resp.sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Erro no dashboard"
            );
        }
    }
}

