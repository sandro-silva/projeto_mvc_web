package controller;

import dao.UsuarioDAO;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UsuarioDAO dao = new UsuarioDAO();

    // ======================
    // LISTAR
    // ======================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Charset
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        try {

            List<Usuario> lista = dao.listar();

            // Envia para o JSP
            req.setAttribute("listaUsuarios", lista);

            req.getRequestDispatcher("listar.jsp")
               .forward(req, resp);

        } catch (Exception e) {

            e.printStackTrace();

            resp.sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Erro ao listar usuários"
            );
        }
    }

    // ======================
    // CADASTRAR
    // ======================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");

        try {

            Usuario u = new Usuario();

            u.setNome(req.getParameter("nome"));

            u.setQuantidadeHoras(
                Integer.parseInt(req.getParameter("horas"))
            );

            u.setParticipou(
                req.getParameter("participou") != null
            );

            u.setObservacao(req.getParameter("obs"));

            dao.salvar(u);

            // Volta para listar
            resp.sendRedirect("usuario");

        } catch (Exception e) {

            e.printStackTrace();

            resp.sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Erro ao salvar usuário"
            );
        }
    }
}
