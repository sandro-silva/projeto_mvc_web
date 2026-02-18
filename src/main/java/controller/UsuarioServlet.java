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
    // GET → LISTAR / BUSCAR / EDITAR / DELETAR
    // ======================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String acao = req.getParameter("acao");

        try {

            /* ======================
               DELETAR
            ====================== */
            if ("deletar".equals(acao)) {

                Long id = Long.parseLong(
                    req.getParameter("id")
                );

                dao.deletar(id);

                resp.sendRedirect("usuario");
                return;
            }

            /* ======================
               EDITAR
            ====================== */
            if ("editar".equals(acao)) {

                Long id = Long.parseLong(
                    req.getParameter("id")
                );

                Usuario u = dao.buscarPorId(id);

                req.setAttribute("usuario", u);

                req.getRequestDispatcher("form.jsp")
                   .forward(req, resp);

                return;
            }

            /* ======================
               LISTAR / BUSCAR
            ====================== */

            String busca = req.getParameter("busca");

            List<Usuario> lista;

            if (busca != null && !busca.trim().isEmpty()) {

                // BUSCA POR NOME
                lista = dao.buscarPorNome(busca);

                req.setAttribute("busca", busca);

            } else {

                // LISTA TODOS
                lista = dao.listar();
            }

            req.setAttribute("listaUsuarios", lista);

            req.getRequestDispatcher("listar.jsp")
               .forward(req, resp);

        } catch (Exception e) {

            e.printStackTrace();

            resp.sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Erro no sistema"
            );
        }
    }

    // ======================
    // POST → SALVAR / ATUALIZAR
    // ======================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");

        try {

            Usuario u = new Usuario();

            String id = req.getParameter("id");

            if (id != null && !id.isEmpty()) {
                u.setId(Long.parseLong(id));
            }

            u.setNome(req.getParameter("nome"));

            u.setQuantidadeHoras(
                Integer.parseInt(req.getParameter("horas"))
            );

            u.setParticipou(
                req.getParameter("participou") != null
            );

            u.setObservacao(req.getParameter("obs"));

            // ATUALIZA OU SALVA
            if (u.getId() != null) {

                dao.atualizar(u);

            } else {

                dao.salvar(u);
            }

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
