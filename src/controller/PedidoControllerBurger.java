package controller;

import dao.AlimentoDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Alimento;
import model.Bebida;
import model.Comida;
import view.Burger;
import view.Trattoria;

public class PedidoControllerBurger {
    private Burger telaElcacto;

    public PedidoControllerBurger(Burger tela) {
        this.telaElcacto = tela;
    }

    public void vListarAlimento() {
        AlimentoDAO dao = new AlimentoDAO();
        List<Alimento> itens = dao.buscarPorRestaurante("Route 66 Burguer");

        if (itens.isEmpty()) {
            telaElcacto.getjTextArea3().setText("Nenhum alimento encontrado neste restaurante.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("ID  -  Nome (Tipo) - Preço\n");
            sb.append("-------------------------------\n");
            for (Alimento a : itens) {
                sb.append(a.getCodigoAlimento())
                  .append(" - ")
                  .append(a.getNome())
                  .append(" (").append(a.getTipo()).append(")")
                  .append(" - R$ ").append(a.getPreco())
                  .append("\n");
            }
            telaElcacto.getjTextArea3().setText(sb.toString());
        }
    }

    public void vAdicionarCarrinho() {
        JTextField id = telaElcacto.getjTextField2();
        String textoId = id.getText().trim();
        if (textoId.isEmpty()) {
            JOptionPane.showMessageDialog(telaElcacto, "Digite o ID do produto para adicionar!");
            return;
        }

        int idProduto;
        try {
            idProduto = Integer.parseInt(textoId);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(telaElcacto, "ID inválido! Digite um número.");
            return;
        }

        AlimentoDAO dao = new AlimentoDAO();
        Alimento produto = dao.buscarPorId(idProduto);

        if (produto == null) {
            JOptionPane.showMessageDialog(telaElcacto, "Produto não encontrado!");
            return;
        }

        // Calcula preço final sem alterar o objeto
        double precoFinal = produto.getPreco();
        if (produto instanceof Bebida bebida && bebida.isAlcoolica()) {
            precoFinal += bebida.calcImposto();
        }

        // Adiciona no carrinho usando o preço final
        String itemCarrinho = produto.getNome() + " - R$ " + precoFinal + "\n";
        telaElcacto.getjTextArea1().append(itemCarrinho);

        atualizarTotal();
        id.setText("");
    }

    public void vRemoverCarrinho() {
        JTextField id = telaElcacto.getjTextField2();
        String textoId = id.getText().trim();
        if (textoId.isEmpty()) {
            JOptionPane.showMessageDialog(telaElcacto, "Digite o ID do produto para remover!");
            return;
        }

        int idProduto;
        try {
            idProduto = Integer.parseInt(textoId);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(telaElcacto, "ID inválido! Digite um número.");
            return;
        }

        AlimentoDAO dao = new AlimentoDAO();
        Alimento produto = dao.buscarPorId(idProduto);

        if (produto == null) {
            JOptionPane.showMessageDialog(telaElcacto, "Produto não encontrado!");
            return;
        }

        String[] linhas = telaElcacto.getjTextArea1().getText().split("\n");
        StringBuilder novoTexto = new StringBuilder();
        boolean encontrado = false;
        double total = 0.0;

        for (String linha : linhas) {
            if (linha.isBlank()) continue;

            if (linha.startsWith(produto.getNome() + " - R$")) {
                encontrado = true;
                continue; // pula linha
            }

            novoTexto.append(linha).append("\n");

            if (linha.contains("R$")) {
                String precoStr = linha.substring(linha.indexOf("R$") + 2).trim();
                total += Double.parseDouble(precoStr);
            }
        }

        if (encontrado) {
            telaElcacto.getjTextArea1().setText(novoTexto.toString());
            telaElcacto.getjLabel4().setText("Total: R$ " + total);
            JOptionPane.showMessageDialog(telaElcacto, "Produto removido do carrinho!");
        } else {
            JOptionPane.showMessageDialog(telaElcacto, "Produto não estava no carrinho!");
        }

        id.setText("");
    }

    public void vDeletar() {
        JTextField id = telaElcacto.getjTextField2();
        String textoId = id.getText().trim();
        if (textoId.isEmpty()) {
            JOptionPane.showMessageDialog(telaElcacto, "Digite o ID do produto para adicionar!");
            return;
        }

        int idProduto;
        try {
            idProduto = Integer.parseInt(textoId);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(telaElcacto, "ID inválido! Digite um número.");
            return;
        }

        AlimentoDAO dao = new AlimentoDAO();
        Alimento produto = dao.buscarPorId(idProduto);

        if (produto == null) {
            JOptionPane.showMessageDialog(telaElcacto, "Produto não encontrado!");
        } else {
            // Se for bebida alcoólica, aplica imposto
            if (produto instanceof Bebida bebida && bebida.isAlcoolica()) {
                produto.setPreco(produto.getPreco() + bebida.calcImposto());
            }

            String itemCarrinho = produto.getNome() + " - R$ " + produto.getPreco() + "\n";
            telaElcacto.getjTextArea1().append(itemCarrinho);

            atualizarTotal();
        }

        id.setText("");
    }

    private void atualizarTotal() {
        double total = 0.0;
        String[] linhas = telaElcacto.getjTextArea1().getText().split("\n");

        for (String linha : linhas) {
            if (!linha.isBlank() && linha.contains("R$")) {
                String precoStr = linha.substring(linha.indexOf("R$") + 2).trim();
                total += Double.parseDouble(precoStr);
            }
        }

        telaElcacto.getjLabel4().setText("Total: R$ " + total);
    }
}
