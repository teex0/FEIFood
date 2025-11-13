package dao;

import java.sql.*;
import java.util.*;
import model.Alimento;

public class AlimentoDAO {

    // Buscar alimento por nome
    public List<Alimento> buscarPorNome(String nome) {
        List<Alimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM alimentos WHERE alimento ILIKE ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Alimento a = new Alimento();
                a.setCodigoAlimento(rs.getInt("codigo_alimento"));
                a.setEstabelecimento(rs.getString("estabelecimento"));
                a.setAlimento(rs.getString("alimento"));
                a.setTipo(rs.getString("tipo"));
                a.setInformacoes(rs.getString("informacoes"));
                a.setPreco(rs.getDouble("preco"));
                a.setImposto(rs.getBoolean("imposto"));
                lista.add(a);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar alimento: " + e.getMessage());
        }

        return lista;
    }

    public List<Alimento> buscarPorRestaurante(String restaurante) {
    List<Alimento> lista = new ArrayList<>();
    String sql = "SELECT * FROM alimentos WHERE estabelecimento = ?";

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, restaurante);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Alimento a = new Alimento();
            a.setCodigoAlimento(rs.getInt("codigo_alimento"));
            a.setEstabelecimento(rs.getString("estabelecimento"));
            a.setAlimento(rs.getString("alimento"));
            a.setTipo(rs.getString("tipo"));
            a.setInformacoes(rs.getString("informacoes"));
            a.setPreco(rs.getDouble("preco"));
            a.setImposto(rs.getBoolean("imposto"));
            lista.add(a);
        }

    } catch (SQLException e) {
        System.out.println("Erro ao buscar alimentos do restaurante: " + e.getMessage());
    }

    return lista;
}
   public Alimento buscarPorId(int id) {
    String sql = "SELECT * FROM alimentos WHERE codigo_alimento = ?";
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Alimento a = new Alimento();
            a.setCodigoAlimento(rs.getInt("codigo_alimento"));
            a.setEstabelecimento(rs.getString("estabelecimento"));
            a.setAlimento(rs.getString("alimento"));
            a.setTipo(rs.getString("tipo"));
            a.setInformacoes(rs.getString("informacoes"));
            a.setPreco(rs.getDouble("preco"));
            a.setImposto(rs.getBoolean("imposto"));
            return a;
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar alimento por ID: " + e.getMessage());
    }
    return null;
}
}