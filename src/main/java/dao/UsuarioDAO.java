package dao;

import model.Usuario;
import util.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    /* ================= CREATE ================= */
    public void salvar(Usuario u) {

        String sql = "INSERT INTO usuarios " +
                     "(nome, quantidade_horas, participou, observacao) " +
                     "VALUES (?, ?, ?, ?)";

        try (
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, u.getNome());
            ps.setInt(2, u.getQuantidadeHoras());
            ps.setBoolean(3, u.isParticipou());
            ps.setString(4, u.getObservacao());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar usuário", e);
        }
    }


    /* ================= READ ================= */
    public List<Usuario> listar() {

        List<Usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuarios ORDER BY id";

        try (
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Usuario u = new Usuario();

                u.setId(rs.getLong("id"));
                u.setNome(rs.getString("nome"));
                u.setQuantidadeHoras(rs.getInt("quantidade_horas"));
                u.setParticipou(rs.getBoolean("participou"));
                u.setObservacao(rs.getString("observacao"));

                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar usuários", e);
        }

        return lista;
    }
    
    /* ================= BUSCAR POR NOME ================= */
    public List<Usuario> buscarPorNome(String nome) {

        List<Usuario> lista = new ArrayList<>();

        String sql =
            "SELECT * FROM usuarios " +
            "WHERE nome ILIKE ? " +
            "ORDER BY id";

        try (
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, "%" + nome + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Usuario u = new Usuario();

                u.setId(rs.getLong("id"));
                u.setNome(rs.getString("nome"));
                u.setQuantidadeHoras(rs.getInt("quantidade_horas"));
                u.setParticipou(rs.getBoolean("participou"));
                u.setObservacao(rs.getString("observacao"));

                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar usuários", e);
        }

        return lista;
    }
    
    /* ================= BUSCAR POR ID (EXTRA) ================= */
    public Usuario buscarPorId(Long id) {

        String sql = "SELECT * FROM usuarios WHERE id = ?";

        Usuario usuario = null;

        try (
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                usuario = new Usuario();

                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setQuantidadeHoras(rs.getInt("quantidade_horas"));
                usuario.setParticipou(rs.getBoolean("participou"));
                usuario.setObservacao(rs.getString("observacao"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar usuário", e);
        }

        return usuario;
    }


    /* ================= UPDATE ================= */
    public void atualizar(Usuario u) {

        String sql =
            "UPDATE usuarios SET " +
            "nome = ?, " +
            "quantidade_horas = ?, " +
            "participou = ?, " +
            "observacao = ? " +
            "WHERE id = ?";

        try (
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, u.getNome());
            ps.setInt(2, u.getQuantidadeHoras());
            ps.setBoolean(3, u.isParticipou());
            ps.setString(4, u.getObservacao());
            ps.setLong(5, u.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar usuário", e);
        }
    }


    /* ================= DELETE ================= */
    public void deletar(Long id) {

        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar usuário", e);
        }
    }
    
    /* ================= CONTAR USUÁRIOS ================= */
    public int contarUsuarios() {

        String sql = "SELECT COUNT(*) FROM usuarios";

        try (
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao contar usuários", e);
        }

        return 0;
    }

}

