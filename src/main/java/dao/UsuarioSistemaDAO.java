package dao;

import model.UsuarioSistema;
import util.Conexao;
import model.Perfil;

import java.sql.*;
import java.util.Map;
import java.util.HashMap;

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
                u.setPerfil(Perfil.valueOf(rs.getString("perfil")));

                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 🔥 NOVO MÉTODO
    public Map<String, Integer> contarPorPerfil() {

        Map<String, Integer> mapa = new HashMap<>();

        String sql = """
            SELECT perfil, COUNT(*) 
            FROM usuarios_sistema
            GROUP BY perfil
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                mapa.put(
                    rs.getString(1),
                    rs.getInt(2)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mapa;
    }
}