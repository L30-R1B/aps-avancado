package com.prisao.visao.gerenciamento.pessoa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.prisao.controle.gerenciamento.pessoa.GerenciaPrisioneiros;
import com.prisao.controle.strategy.estrategia.AumentarPenaStrategy;
import com.prisao.controle.strategy.estrategia.DiminuirPenaStrategy;
import com.prisao.controle.strategy.estrategia.MudarComportamentoStrategy;
import com.prisao.modelo.pessoa.Prisioneiro;

public class GerenciaPrisioneirosUI extends JFrame {

    public GerenciaPrisioneirosUI() {
        setTitle("Gerenciamento de Prisioneiros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int) (screenSize.width * 0.6);
        int height = (int) (screenSize.height * 0.9);

        setSize(width, height);

        setLocationRelativeTo(null);

        // Usando JTabbedPane para organizar as abas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Aba de Gerenciamento de Prisioneiros
        tabbedPane.addTab("Prisioneiros", createPrisioneirosPanel());

        // Aba de Operações de Pena e Comportamento (Strategy)
        tabbedPane.addTab("Operações de Pena e Comportamento", createOperacoesPanel());

        // Adiciona o JTabbedPane à janela principal
        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // Cria o painel de gerenciamento de prisioneiros
    private JPanel createPrisioneirosPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

        // Painel de Pesquisa (existente)
        JPanel pesquisarPanel = new JPanel(new BorderLayout());
        pesquisarPanel.setBorder(BorderFactory.createTitledBorder("Pesquisar Prisioneiro"));

        JPanel inputPesquisarPanel = new JPanel(new FlowLayout());
        JTextField txtPesquisarId = new JTextField(10);
        JButton btnPesquisar = new JButton("Pesquisar");
        inputPesquisarPanel.add(new JLabel("ID do Prisioneiro:"));
        inputPesquisarPanel.add(txtPesquisarId);
        inputPesquisarPanel.add(btnPesquisar);

        JTextArea resultadoPesquisa = new JTextArea(10, 40);
        resultadoPesquisa.setEditable(false);
        resultadoPesquisa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane scrollPane = new JScrollPane(resultadoPesquisa);

        pesquisarPanel.add(inputPesquisarPanel, BorderLayout.NORTH);
        pesquisarPanel.add(scrollPane, BorderLayout.CENTER);

        btnPesquisar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtPesquisarId.getText());
                Prisioneiro prisioneiro = GerenciaPrisioneiros.pesquisarPrisioneiro(id);

                if (prisioneiro != null) {
                    resultadoPesquisa.setText(formatarDadosPrisioneiro(prisioneiro));
                } else {
                    resultadoPesquisa.setText("Prisioneiro não encontrado.");
                }
            } catch (NumberFormatException ex) {
                resultadoPesquisa.setText("ID inválido.");
            } finally {
                txtPesquisarId.setText("");
            }
        });

        panel.add(pesquisarPanel);

        // Painel de Cadastro (existente)
        JPanel cadastrarPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        cadastrarPanel.setBorder(BorderFactory.createTitledBorder("Cadastrar Prisioneiro"));

        JTextField txtPrisioneiroId = new JTextField();
        JTextField txtCelaId = new JTextField();
        JTextField txtNome = new JTextField();
        JTextField txtCrime = new JTextField();
        JTextField txtPena = new JTextField();
        JTextField txtComportamento = new JTextField();
        JButton btnCadastrar = new JButton("Cadastrar");
        JLabel lblCadastrarResultado = new JLabel();

        cadastrarPanel.add(new JLabel("ID do Prisioneiro:"));
        cadastrarPanel.add(txtPrisioneiroId);
        cadastrarPanel.add(new JLabel("ID da Cela:"));
        cadastrarPanel.add(txtCelaId);
        cadastrarPanel.add(new JLabel("Nome:"));
        cadastrarPanel.add(txtNome);
        cadastrarPanel.add(new JLabel("Crime:"));
        cadastrarPanel.add(txtCrime);
        cadastrarPanel.add(new JLabel("Pena (em anos):"));
        cadastrarPanel.add(txtPena);
        cadastrarPanel.add(new JLabel("Comportamento:"));
        cadastrarPanel.add(txtComportamento);
        cadastrarPanel.add(btnCadastrar);
        cadastrarPanel.add(lblCadastrarResultado);

        btnCadastrar.addActionListener(e -> {
            try {
                int prisioneiroId = Integer.parseInt(txtPrisioneiroId.getText());
                int celaId = Integer.parseInt(txtCelaId.getText());
                String nome = txtNome.getText();
                String crime = txtCrime.getText();
                float pena = Float.parseFloat(txtPena.getText());
                String comportamento = txtComportamento.getText();

                boolean sucesso = GerenciaPrisioneiros.cadastrarPrisioneiro(prisioneiroId, celaId, nome, crime, pena, comportamento);
                lblCadastrarResultado.setText(sucesso ? "Prisioneiro cadastrado com sucesso!" : "Falha ao cadastrar prisioneiro.");
            } catch (NumberFormatException ex) {
                lblCadastrarResultado.setText("Dados inválidos.");
            } finally {
                txtPrisioneiroId.setText("");
                txtCelaId.setText("");
                txtNome.setText("");
                txtCrime.setText("");
                txtPena.setText("");
                txtComportamento.setText("");
            }

            javax.swing.Timer timer = new javax.swing.Timer(3000, ev -> lblCadastrarResultado.setText(""));
            timer.setRepeats(false);
            timer.start();
        });

        panel.add(cadastrarPanel);

        // Painel de Desvincular (existente)
        JPanel desvincularPanel = new JPanel(new FlowLayout());
        desvincularPanel.setBorder(BorderFactory.createTitledBorder("Desvincular Prisioneiro"));
        JTextField txtDesvincularId = new JTextField(10);
        JButton btnDesvincular = new JButton("Desvincular");
        JLabel lblDesvincularResultado = new JLabel();
        desvincularPanel.add(new JLabel("ID do Prisioneiro:"));
        desvincularPanel.add(txtDesvincularId);
        desvincularPanel.add(btnDesvincular);
        desvincularPanel.add(lblDesvincularResultado);

        btnDesvincular.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtDesvincularId.getText());
                boolean sucesso = GerenciaPrisioneiros.desvincularPrisioneiro(id);
                lblDesvincularResultado.setText(sucesso ? "Prisioneiro desvinculado com sucesso!" : "Falha ao desvincular prisioneiro.");
            } catch (NumberFormatException ex) {
                lblDesvincularResultado.setText("ID inválido.");
            } finally {
                txtDesvincularId.setText("");
            }

            javax.swing.Timer timer = new javax.swing.Timer(3000, ev -> lblDesvincularResultado.setText(""));
            timer.setRepeats(false);
            timer.start();
        });

        panel.add(desvincularPanel);

        // Painel de Transferir (existente)
        JPanel transferirPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        transferirPanel.setBorder(BorderFactory.createTitledBorder("Transferir Prisioneiro"));

        JTextField txtTransferirId = new JTextField();
        JTextField txtNovaCelaId = new JTextField();
        JButton btnTransferir = new JButton("Transferir");
        JLabel lblTransferirResultado = new JLabel();

        transferirPanel.add(new JLabel("ID do Prisioneiro:"));
        transferirPanel.add(txtTransferirId);
        transferirPanel.add(new JLabel("ID da Nova Cela:"));
        transferirPanel.add(txtNovaCelaId);
        transferirPanel.add(btnTransferir);
        transferirPanel.add(lblTransferirResultado);

        btnTransferir.addActionListener(e -> {
            try {
                int prisioneiroId = Integer.parseInt(txtTransferirId.getText());
                int novaCelaId = Integer.parseInt(txtNovaCelaId.getText());

                boolean sucesso = GerenciaPrisioneiros.transferirPrisioneiro(prisioneiroId, novaCelaId);
                lblTransferirResultado.setText(sucesso ? "Prisioneiro transferido com sucesso!" : "Falha ao transferir prisioneiro.");
            } catch (NumberFormatException ex) {
                lblTransferirResultado.setText("Dados inválidos.");
            } finally {
                txtTransferirId.setText("");
                txtNovaCelaId.setText("");
            }

            javax.swing.Timer timer = new javax.swing.Timer(3000, ev -> lblTransferirResultado.setText(""));
            timer.setRepeats(false);
            timer.start();
        });

        panel.add(transferirPanel);

        return panel;
    }

    private JPanel createOperacoesPanel() {
        JPanel operacoesPanel = new JPanel(new BorderLayout(10, 10)); // Layout principal
        operacoesPanel.setBorder(BorderFactory.createTitledBorder("Operações de Pena e Comportamento"));
    
        // Painel para os campos de entrada
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margens internas
    
        JTextField txtOperacaoId = new JTextField();
        JTextField txtValorPena = new JTextField();
        JTextField txtNovoComportamento = new JTextField();
    
        inputPanel.add(new JLabel("ID do Prisioneiro:"));
        inputPanel.add(txtOperacaoId);
        inputPanel.add(new JLabel("Valor da Pena:"));
        inputPanel.add(txtValorPena);
        inputPanel.add(new JLabel("Novo Comportamento:"));
        inputPanel.add(txtNovoComportamento);
    
        // Painel para os botões
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margens internas
    
        JButton btnAumentarPena = new JButton("Aumentar Pena");
        JButton btnDiminuirPena = new JButton("Diminuir Pena");
        JButton btnMudarComportamento = new JButton("Mudar Comportamento");
    
        buttonPanel.add(btnAumentarPena);
        buttonPanel.add(btnDiminuirPena);
        buttonPanel.add(btnMudarComportamento);
    
        // Painel para o resultado da operação
        JLabel lblOperacaoResultado = new JLabel(" ");
        lblOperacaoResultado.setHorizontalAlignment(JLabel.CENTER); // Centraliza o texto
        lblOperacaoResultado.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margens internas
    
        // Adiciona os painéis ao painel principal
        operacoesPanel.add(inputPanel, BorderLayout.NORTH);
        operacoesPanel.add(buttonPanel, BorderLayout.CENTER);
        operacoesPanel.add(lblOperacaoResultado, BorderLayout.SOUTH);
    
        // Lógica dos botões
        btnAumentarPena.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtOperacaoId.getText());
                float valor = Float.parseFloat(txtValorPena.getText());
                boolean sucesso = GerenciaPrisioneiros.aplicarEstrategiaPrisioneiro(id, new AumentarPenaStrategy(valor));
                lblOperacaoResultado.setText(sucesso ? "Pena aumentada com sucesso!" : "Falha ao aumentar pena.");
            } catch (NumberFormatException ex) {
                lblOperacaoResultado.setText("Dados inválidos.");
            } finally {
                txtOperacaoId.setText("");
                txtValorPena.setText("");
            }
    
            javax.swing.Timer timer = new javax.swing.Timer(3000, ev -> lblOperacaoResultado.setText(" "));
            timer.setRepeats(false);
            timer.start();
        });
    
        btnDiminuirPena.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtOperacaoId.getText());
                float valor = Float.parseFloat(txtValorPena.getText());
                boolean sucesso = GerenciaPrisioneiros.aplicarEstrategiaPrisioneiro(id, new DiminuirPenaStrategy(valor));
                lblOperacaoResultado.setText(sucesso ? "Pena diminuída com sucesso!" : "Falha ao diminuir pena.");
            } catch (NumberFormatException ex) {
                lblOperacaoResultado.setText("Dados inválidos.");
            } finally {
                txtOperacaoId.setText("");
                txtValorPena.setText("");
            }
    
            javax.swing.Timer timer = new javax.swing.Timer(3000, ev -> lblOperacaoResultado.setText(" "));
            timer.setRepeats(false);
            timer.start();
        });
    
        btnMudarComportamento.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtOperacaoId.getText());
                String comportamento = txtNovoComportamento.getText();
                boolean sucesso = GerenciaPrisioneiros.aplicarEstrategiaPrisioneiro(id, new MudarComportamentoStrategy(comportamento));
                lblOperacaoResultado.setText(sucesso ? "Comportamento alterado com sucesso!" : "Falha ao alterar comportamento.");
            } catch (NumberFormatException ex) {
                lblOperacaoResultado.setText("Dados inválidos.");
            } finally {
                txtOperacaoId.setText("");
                txtNovoComportamento.setText("");
            }
    
            javax.swing.Timer timer = new javax.swing.Timer(3000, ev -> lblOperacaoResultado.setText(" "));
            timer.setRepeats(false);
            timer.start();
        });
    
        return operacoesPanel;
    }

    private String formatarDadosPrisioneiro(Prisioneiro prisioneiro) {
        return String.format("ID PRISIONEIRO: %d\nNome: %s\nCrime: %s\nPena: %.2f anos\nComportamento: %s",
                prisioneiro.getIdentificador(),
                prisioneiro.getNome(),
                prisioneiro.getCrime(),
                prisioneiro.getPena(),
                prisioneiro.getComportamento());
    }
}