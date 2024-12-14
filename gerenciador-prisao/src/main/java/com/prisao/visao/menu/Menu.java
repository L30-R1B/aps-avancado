package com.prisao.visao.menu;

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
                "Sistema de Gerenciamento da Prisão\nVersão 1.0\nDesenvolvido por : Giulia Mota Apinagés dos Santos; Leonardo Ribeiro; Nátaly Braga Souza; Yuri Sillas Drumond Ribeiro", 
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
        
        // Minimizar a janela principal quando abrir o sistema de gerenciamento
        frame.setState(JFrame.ICONIFIED);
    
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Aba com botões para abrir novas janelas
        JPanel abaPrincipal = new JPanel(new GridLayout(2, 2, 10, 10));
        abaPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton btnCelas = new JButton("Abrir Gerenciamento de Celas");
        btnCelas.addActionListener(e -> {
            JFrame janelaCelas = new GerenciaCelasUI();
            janelaCelas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Apenas fecha a janela filha
            janelaCelas.setVisible(true);
        });
        
        JButton btnBlocos = new JButton("Abrir Gerenciamento de Blocos");
        btnBlocos.addActionListener(e -> {
            JFrame janelaBlocos = new GerenciaBlocosUI();
            janelaBlocos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Apenas fecha a janela filha
            janelaBlocos.setVisible(true);
        });
        
        JButton btnPrisioneiros = new JButton("Abrir Gerenciamento de Prisioneiros");
        btnPrisioneiros.addActionListener(e -> {
            JFrame janelaPrisioneiros = new GerenciaPrisioneirosUI();
            janelaPrisioneiros.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Apenas fecha a janela filha
            janelaPrisioneiros.setVisible(true);
        });
        
        JButton btnGuardas = new JButton("Abrir Gerenciamento de Guardas");
        btnGuardas.addActionListener(e -> {
            JFrame janelaGuardas = new GerenciaGuardasUI();
            janelaGuardas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Apenas fecha a janela filha
            janelaGuardas.setVisible(true);
        });
        
        abaPrincipal.add(btnCelas);
        abaPrincipal.add(btnBlocos);
        abaPrincipal.add(btnPrisioneiros);
        abaPrincipal.add(btnGuardas);
        
        tabbedPane.addTab("Principal", abaPrincipal);
        
        frame.add(tabbedPane);
        frame.setVisible(true);
    }
    
    
}
