/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package feifood;

/**
 *
 * @author tex
 */


import dao.ConnectionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import view.BuscarAlimentoView;
import view.LoginView;


public class FEIFood {
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("⚠️ Não foi possível aplicar o tema Nimbus.");
        }

        // Abre a tela de login
        java.awt.EventQueue.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }
}