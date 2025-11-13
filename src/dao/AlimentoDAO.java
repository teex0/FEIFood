package dao;

import java.sql.*;
import java.util.*;
import model.Alimento;
import model.Comida;
import model.Bebida;

public class AlimentoDAO {

    // Método auxiliar para criar Alimento correto
    private Alimento criarAlimentoDoResultSet(ResultSet rs) throws SQLException {
    String tipo = rs.getString("tipo");
    if (tipo != null && tipo.toLowerCase().contains("bebida")) {
        Bebida bebida = new Bebida();

        // Define se é alcoólica usando a coluna "imposto"
        boolean alcool = rs.getBoolean("imposto");
        bebida.setAlcoolica(alcool);

        bebida.setCodigoAlimento(rs.getInt("codigo_alimento"));
        bebida.setEstabelecimento(rs.getString("estabelecimento"));
        bebida.setNome(rs.getString("alimento"));
        bebida.setTipo(tipo);
        bebida.setInformacoes(rs.getString("informacoes"));
        bebida.setPreco(rs.getDouble("preco"));
        return bebida;
    } else {
        Comida comida = new Comida();
        comida.setCodigoAlimento(rs.getInt("codigo_alimento"));
        comida.setEstabelecimento(rs.getString("estabelecimento"));
        comida.setNome(rs.getString("alimento"));
        comida.setTipo(tipo);
        comida.setInformacoes(rs.getString("informacoes"));
        comida.setPreco(rs.getDouble("preco"));
        return comida;
    }
}

    public List<Alimento> buscarPorNome(String nome) {
        List<Alimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM alimentos WHERE alimento ILIKE ?";
        try (Connection conn = ConnectionDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(criarAlimentoDoResultSet(rs));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar alimento: " + e.getMessage());
        }
        return lista;
    }

    public List<Alimento> buscarPorRestaurante(String restaurante) {
        List<Alimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM alimentos WHERE estabelecimento = ?";
        try (Connection conn = ConnectionDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, restaurante);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(criarAlimentoDoResultSet(rs));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar alimentos do restaurante: " + e.getMessage());
        }
        return lista;
    }

    public Alimento buscarPorId(int id) {
        String sql = "SELECT * FROM alimentos WHERE codigo_alimento = ?";
        try (Connection conn = ConnectionDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return criarAlimentoDoResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar alimento por ID: " + e.getMessage());
        }
        return null;
    }
}
