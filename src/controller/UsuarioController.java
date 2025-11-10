/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ConnectionFactory;
import dao.UsuarioDAO;
import model.Usuario;
import view.LoginView;
import view.TelaPrincipalUsuarioView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UsuarioController {

    private LoginView telaLogin;

    public UsuarioController(LoginView telaLogin) {
        this.telaLogin = telaLogin;
    }

    public void loginUsuario() {
        // Cria o objeto Usuario com os dados digitados na tela
        Usuario usuario = new Usuario();
        usuario.setEmail(telaLogin.getTxtUsuario().getText()); // login por e-mail
        usuario.setSenha(telaLogin.getTxtSenha().getText());

        // Tenta conectar ao banco e validar login
        try (Connection conn = ConnectionFactory.getConnection()) {
            UsuarioDAO dao = new UsuarioDAO(conn);
            ResultSet res = dao.consultar(usuario);

            if (res.next()) {
                JOptionPane.showMessageDialog(
                    telaLogin,
                    "Login realizado com sucesso!",
                    "Aviso",
                    JOptionPane.INFORMATION_MESSAGE
                );

                // Pega os dados do usuário logado
                String nome = res.getString("nome");
                String email = res.getString("email");
                String senha = res.getString("senha");

                // Abre a tela principal do usuário
                TelaPrincipalUsuarioView telaPrincipal = new TelaPrincipalUsuarioView();
                telaPrincipal.setUsuarioLogado(nome, email, senha);
                telaPrincipal.setVisible(true);
                telaLogin.dispose();

                telaPrincipal.setVisible(true);
                telaLogin.dispose(); // fecha a tela de login

            } else {
                JOptionPane.showMessageDialog(
                    telaLogin,
                    "Usuário ou senha incorretos.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                telaLogin,
                "Erro ao conectar com o banco de dados:\n" + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}