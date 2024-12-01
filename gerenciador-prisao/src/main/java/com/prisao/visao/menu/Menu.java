package com.prisao.visao.menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import com.prisao.visao.local.GerenciaBlocosUI;
import com.prisao.visao.local.GerenciaCelasUI;
import com.prisao.visao.pessoa.GerenciaGuardasUI;
import com.prisao.visao.pessoa.GerenciaPrisioneirosUI;

public class Menu {

    public static void inicializaMenu() {
        SwingUtilities.invokeLater(() -> {
            JFrame menuFrame = new JFrame("Menu Principal - Sistema de Gerenciamento da Prisão");
            menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menuFrame.setSize(400, 300);
            menuFrame.setLocationRelativeTo(null);
            
            JPanel menuPanel = new JPanel(new GridLayout(4, 1, 10, 10));
            menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            JButton btnAbrirSistema = new JButton("Abrir Sistema de Gerenciamento");
            JButton btnSobre = new JButton("Sobre");
            JButton btnAjuda = new JButton("Ajuda");
            JButton btnSair = new JButton("Sair");
            
            btnAbrirSistema.addActionListener(e -> {
                menuFrame.dispose();
                abrirSistemaGerenciamento();
            });
            
            btnSobre.addActionListener(e -> JOptionPane.showMessageDialog(menuFrame, 
                "Sistema de Gerenciamento da Prisão\nVersão 1.0\nDesenvolvido por : Giulia Mota Apinagés dos Santos; Leonardo ribeiro; Nátaly Braga Souza; Yuri Sillas Drumond Ribeiro", 
                "Sobre", JOptionPane.INFORMATION_MESSAGE));
            
            btnAjuda.addActionListener(e -> JOptionPane.showMessageDialog(menuFrame, 
                "Para usar o sistema, selecione 'Abrir Sistema de Gerenciamento' no menu principal.", 
                "Ajuda", JOptionPane.INFORMATION_MESSAGE));
            
            btnSair.addActionListener(e -> System.exit(0));
            
            menuPanel.add(btnAbrirSistema);
            menuPanel.add(btnSobre);
            menuPanel.add(btnAjuda);
            menuPanel.add(btnSair);
            
            menuFrame.add(menuPanel);
            menuFrame.setVisible(true);
        });
    }

    private static void abrirSistemaGerenciamento() {
        JFrame frame = new JFrame("Sistema de Gerenciamento da Prisão");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Gerenciar Blocos", criarPainelDeAba(new GerenciaBlocosUI()));
        tabbedPane.addTab("Gerenciar Celas", criarPainelDeAba(new GerenciaCelasUI()));
        tabbedPane.addTab("Gerenciar Prisioneiros", criarPainelDeAba(new GerenciaPrisioneirosUI()));
        tabbedPane.addTab("Gerenciar Guardas", criarPainelDeAba(new GerenciaGuardasUI()));

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private static JPanel criarPainelDeAba(JFrame janela) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.add(janela.getContentPane(), BorderLayout.CENTER);
        janela.dispose();
        return painel;
    }
}
