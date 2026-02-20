package dao;

import model.UsuarioSistema;
import util.Conexao;

import java.sql.*;

public class UsuarioSistemaDAO {

    public UsuarioSistema autenticar(String login, String senha) {

        String sql = "SELECT * FROM usuarios_sistema WHERE login = ? AND senha = ?";

        try (
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, login);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                UsuarioSistema u = new UsuarioSistema();

                u.setId(rs.getLong("id"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setPerfil(rs.getString("perfil"));

                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
