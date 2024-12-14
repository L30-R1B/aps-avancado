package com.prisao.visao.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.prisao.controle.gerenciamento.pessoa.GerenciaDelegado;

public class LoginUI {
    private GerenciaDelegado gerenciaDelegado = new GerenciaDelegado();

    public void criarTelaLogin() {
        // Frame principal
        JFrame frame = new JFrame("Login do Delegado");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel para organizar componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        frame.add(panel);

        // Campo de email
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        panel.add(emailLabel);
        panel.add(emailField);

        // Campo de senha
        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaField = new JPasswordField();
        panel.add(senhaLabel);
        panel.add(senhaField);

        // Botão de Login
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());

                if (gerenciaDelegado.aprovaLogin(email, senha)) {
                    JOptionPane.showMessageDialog(frame, "Login realizado com sucesso!");
                    frame.dispose(); // Fecha a tela de login
                    com.prisao.visao.menu.Menu.inicializaMenu(); // Inicia o menu
                } else {
                    JOptionPane.showMessageDialog(frame, "Email ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Botão "Esqueci minha senha"
        JButton forgotPasswordButton = new JButton("Esqueci minha senha");
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = JOptionPane.showInputDialog(frame, "Digite seu email:");
                if (email != null && gerenciaDelegado.verificaEmail(email)) {
                    String novaSenha = JOptionPane.showInputDialog(frame, "Digite sua nova senha:");
                    if (novaSenha != null && !novaSenha.isEmpty()) {
                        gerenciaDelegado.mudarSenha(email, novaSenha);
                        JOptionPane.showMessageDialog(frame, "Senha redefinida com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "A senha não pode estar vazia.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Email não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adicionando os botões ao painel
        panel.add(loginButton);
        panel.add(forgotPasswordButton);

        // Exibindo a tela
        frame.setVisible(true);
    }
}
