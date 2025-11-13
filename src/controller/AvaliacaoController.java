package controller;

import dao.AvaliacaoDAO;
import view.AvaliacaoView;
import view.TelaPrincipalUsuarioView;

public class AvaliacaoController {
    private AvaliacaoView tela;

    public AvaliacaoController(AvaliacaoView tela){
        this.tela = tela;
    }

    public void vAvaliar(){
        int nota = tela.getjSlider1().getValue(); 
        int numeroPedido = tela.getNumeroPedido(); 

        AvaliacaoDAO dao = new AvaliacaoDAO();
        dao.salvarAvaliacao(nota); 

        // Mostra pop-up de agradecimento
        javax.swing.JOptionPane.showMessageDialog(
            tela, 
            "Obrigado pela sua avaliação!", 
            "Avaliação Registrada", 
            javax.swing.JOptionPane.INFORMATION_MESSAGE
        );

        // Abre a TelaPrincipalUsuarioView e fecha a AvaliacaoView
        new TelaPrincipalUsuarioView().setVisible(true);
        tela.dispose();
    }
}
