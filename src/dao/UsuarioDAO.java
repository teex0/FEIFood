/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;


public class UsuarioDAO {
    
    private Connection conn;

    // Consulta usuário pelo login e senha, retornando o objeto Usuario completo
    public Usuario consultar(Usuario usuario) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";

        try (Connection conn = ConnectionDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getSenha());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setNome(rs.getString("nome"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setSenha(rs.getString("senha"));
                    return u;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // retorna null se usuário não encontrado
    }
    
        public boolean cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, usuario, senha) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getUsuario());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
