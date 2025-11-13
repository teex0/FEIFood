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

import javax.swing.JOptionPane;

public class UsuarioController {

    private LoginView telaLogin;

    public UsuarioController(LoginView telaLogin) {
        this.telaLogin = telaLogin;
    }

    public void loginUsuario() {
        // Cria o objeto Usuario com os dados digitados na tela
        Usuario usuario = new Usuario();
        usuario.setUsuario(telaLogin.getTxtUsuario().getText().trim()); // login por nome de usuário
        usuario.setSenha(new String(telaLogin.getTxtSenha().getPassword()));

        if (usuario.getUsuario().isEmpty() || usuario.getSenha().isEmpty()) {
            JOptionPane.showMessageDialog(
                telaLogin,
                "Preencha usuário e senha!",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Tenta validar login usando o DAO
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuarioLogado = dao.consultar(usuario);

        if (usuarioLogado != null) {
            JOptionPane.showMessageDialog(
                telaLogin,
                "Login realizado com sucesso!",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
            );

            // Abre a tela principal do usuário
            TelaPrincipalUsuarioView telaPrincipal = new TelaPrincipalUsuarioView();
            telaPrincipal.setUsuarioLogado(
                usuarioLogado.getNome(),
                usuarioLogado.getUsuario(),
                usuarioLogado.getSenha()
            );
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
    }
}
