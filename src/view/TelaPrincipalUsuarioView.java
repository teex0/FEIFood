/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.JLabel;

/**
 *
 * @author tex
 */
public class TelaPrincipalUsuarioView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPrincipalUsuarioView.class.getName());

    // 🟢 Atributos para guardar os dados do usuário logado
    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;

    // 🟢 Label para exibir o nome do usuário
    private JLabel lblBemVindo;

    /**
     * Creates new form TelaPrincipalUsuario
     */
    public TelaPrincipalUsuarioView() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.jpg"))); // NOI18N
        jMenuBar1.add(jMenu1);

        jMenu2.setText("(lupa)");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("(pedidos)");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("(avaliação)");
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public void setUsuarioLogado(String nome, String email, String senha) {
        this.nomeUsuario = nome;
        this.emailUsuario = email;
        this.senhaUsuario = senha;

        // Atualiza o título da janela e o texto do label
        setTitle("FEIFood - Usuário: " + nomeUsuario);
        if (lblBemVindo != null) {
            lblBemVindo.setText("Bem-vindo(a), " + nomeUsuario + "!");
        }
    }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
