package controller;

import dao.AlimentoDAO;
import java.util.List;
import javax.swing.JOptionPane;
import model.Alimento;
import model.Bebida;
import view.BuscarAlimentoView;

public class AlimentoController {
    private BuscarAlimentoView tela;

    public AlimentoController(BuscarAlimentoView tela) {
        this.tela = tela;
    }

    public void vBuscarAlimento() {
        String nomeBusca = tela.getjTextField1().getText().trim();
        if (nomeBusca.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Digite o nome do alimento para buscar!");
            return;
        }

        AlimentoDAO dao = new AlimentoDAO();
        List<Alimento> resultados = dao.buscarPorNome(nomeBusca);

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Nenhum alimento encontrado.");
        } else {
            StringBuilder sb = new StringBuilder("🍽️ Alimentos encontrados:\n\n");
            for (Alimento a : resultados) {
                double precoFinal = a.getPreco();
                // Aplica imposto se for bebida alcoólica
                if (a instanceof Bebida bebida && bebida.isAlcoolica()) {
                    precoFinal += bebida.calcImposto();
                }

                sb.append(" - ")
                  .append(a.getNome())
                  .append(" (").append(a.getEstabelecimento()).append(")")
                  .append(" R$ ").append(precoFinal)
                  .append("\n");
            }

            JOptionPane.showMessageDialog(tela, sb.toString());
        }
    }

    public void vFieldText() {
        String nomeBusca = tela.getjTextField1().getText().trim();
        if (nomeBusca.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Digite o nome do alimento para buscar!");
            return;
        }

        AlimentoDAO dao = new AlimentoDAO();
        List<Alimento> resultados = dao.buscarPorNome(nomeBusca);

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Nenhum alimento encontrado.");
        } else {
            StringBuilder sb = new StringBuilder("🍽️ Alimentos encontrados:\n\n");
            for (Alimento a : resultados) {
                double precoFinal = a.getPreco();
                if (a instanceof Bebida bebida && bebida.isAlcoolica()) {
                    precoFinal += bebida.calcImposto();
                }

                sb.append(" - ")
                  .append(a.getNome())
                  .append(" (").append(a.getEstabelecimento()).append(")")
                  .append(" R$ ").append(precoFinal)
                  .append("\n");
            }

            JOptionPane.showMessageDialog(tela, sb.toString());
        }
    }
}
