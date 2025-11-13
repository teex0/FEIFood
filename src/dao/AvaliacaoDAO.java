/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author tex
 */


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;



public class AvaliacaoDAO {

    public int salvarAvaliacao(int nota) {
        String sql = "INSERT INTO avaliacoes (nota) VALUES (?) RETURNING numero_pedido";
        
        try (Connection conn = ConnectionDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, nota);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1); // pega o primeiro campo retornado pelo RETURNING
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // retorna -1 se deu erro
    }
}