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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.prisao.controle.gerenciamento.pessoa.GerenciaGuardas;
import com.prisao.modelo.pessoa.Guarda;

public class GerenciaGuardasUI extends JFrame {

    public GerenciaGuardasUI() {
        setTitle("Gerenciamento de Guardas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.6);
        int height = (int) (screenSize.height * 0.9);
        setSize(width, height);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

        // Pesquisar Guarda
        JPanel pesquisarPanel = new JPanel(new BorderLayout());
        pesquisarPanel.setBorder(BorderFactory.createTitledBorder("Pesquisar Guarda"));

        JPanel inputPesquisarPanel = new JPanel(new FlowLayout());
        JTextField txtPesquisarId = new JTextField(10);
        JButton btnPesquisar = new JButton("Pesquisar");
        inputPesquisarPanel.add(new JLabel("ID do Guarda:"));
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
                Guarda guarda = GerenciaGuardas.pesquisarGuarda(id);

                if (guarda != null) {
                    resultadoPesquisa.setText(formatarDadosGuarda(guarda));
                } else {
                    resultadoPesquisa.setText("Guarda não encontrado.");
                }
            } catch (NumberFormatException ex) {
                resultadoPesquisa.setText("ID inválido.");
            }
        });

        panel.add(pesquisarPanel);

        JPanel cadastrarPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        cadastrarPanel.setBorder(BorderFactory.createTitledBorder("Cadastrar Guarda"));

        JTextField txtGuardaId = new JTextField();
        JTextField txtBlocoId = new JTextField();
        JTextField txtNome = new JTextField();
        JTextField txtTurno = new JTextField();
        JTextField txtCargo = new JTextField();
        JButton btnCadastrar = new JButton("Cadastrar");
        JLabel lblCadastrarResultado = new JLabel();

        cadastrarPanel.add(new JLabel("ID do Guarda:"));
        cadastrarPanel.add(txtGuardaId);
        cadastrarPanel.add(new JLabel("ID do Bloco:"));
        cadastrarPanel.add(txtBlocoId);
        cadastrarPanel.add(new JLabel("Nome:"));
        cadastrarPanel.add(txtNome);
        cadastrarPanel.add(new JLabel("Turno:"));
        cadastrarPanel.add(txtTurno);
        cadastrarPanel.add(new JLabel("Cargo:"));
        cadastrarPanel.add(txtCargo);
        cadastrarPanel.add(btnCadastrar);
        cadastrarPanel.add(lblCadastrarResultado);

        btnCadastrar.addActionListener(e -> {
            try {
                int guardaId = Integer.parseInt(txtGuardaId.getText());
                int blocoId = Integer.parseInt(txtBlocoId.getText());
                String nome = txtNome.getText();
                String turno = txtTurno.getText();
                String cargo = txtCargo.getText();

                boolean sucesso = GerenciaGuardas.cadastrarGuarda(guardaId, blocoId, nome, turno, cargo);
                lblCadastrarResultado.setText(sucesso ? "Guarda cadastrado com sucesso!" : "Falha ao cadastrar guarda.");
            } catch (NumberFormatException ex) {
                lblCadastrarResultado.setText("Dados inválidos.");
            }finally{
                txtGuardaId.setText("");
                txtBlocoId.setText("");
                txtNome.setText("");
                txtTurno.setText("");
                txtCargo.setText("");
            }

            javax.swing.Timer timer = new javax.swing.Timer(3000, ev -> lblCadastrarResultado.setText(""));
            timer.setRepeats(false);
            timer.start();
        });

        panel.add(cadastrarPanel);

        // Desvincular Guarda
        JPanel desvincularPanel = new JPanel(new FlowLayout());
        desvincularPanel.setBorder(BorderFactory.createTitledBorder("Desvincular Guarda"));
        JTextField txtDesvincularId = new JTextField(10);
        JButton btnDesvincular = new JButton("Desvincular");
        JLabel lblDesvincularResultado = new JLabel();
        desvincularPanel.add(new JLabel("ID do Guarda:"));
        desvincularPanel.add(txtDesvincularId);
        desvincularPanel.add(btnDesvincular);
        desvincularPanel.add(lblDesvincularResultado);

        btnDesvincular.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtDesvincularId.getText());
                boolean sucesso = GerenciaGuardas.desvincularGuarda(id);
                lblDesvincularResultado.setText(sucesso ? "Guarda desvinculado com sucesso!" : "Falha ao desvincular guarda.");
            } catch (NumberFormatException ex) {
                lblDesvincularResultado.setText("ID inválido.");
            }
            javax.swing.Timer timer = new javax.swing.Timer(3000, ev -> lblDesvincularResultado.setText(""));
            timer.setRepeats(false);
            timer.start();
        });

        panel.add(desvincularPanel);

        JPanel transferirPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        transferirPanel.setBorder(BorderFactory.createTitledBorder("Transferir Guarda"));

        JTextField txtTransferirId = new JTextField();
        JTextField txtNovoBlocoId = new JTextField();
        JButton btnTransferir = new JButton("Transferir");
        JLabel lblTransferirResultado = new JLabel();

        transferirPanel.add(new JLabel("ID do Guarda:"));
        transferirPanel.add(txtTransferirId);
        transferirPanel.add(new JLabel("ID do Novo Bloco:"));
        transferirPanel.add(txtNovoBlocoId);
        transferirPanel.add(btnTransferir);
        transferirPanel.add(lblTransferirResultado);

        btnTransferir.addActionListener(e -> {
            try {
                int guardaId = Integer.parseInt(txtTransferirId.getText());
                int novoBlocoId = Integer.parseInt(txtNovoBlocoId.getText());

                boolean sucesso = GerenciaGuardas.transferirGuarda(guardaId, novoBlocoId);
                lblTransferirResultado.setText(sucesso ? "Guarda transferido com sucesso!" : "Falha ao transferir guarda.");
            } catch (NumberFormatException ex) {
                lblTransferirResultado.setText("Dados inválidos.");
            }
            javax.swing.Timer timer = new javax.swing.Timer(3000, ev -> lblTransferirResultado.setText(""));
            timer.setRepeats(false);
            timer.start();
        });

        panel.add(transferirPanel);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private String formatarDadosGuarda(Guarda guarda) {
        return String.format("ID: %d\nNome: %s\nTurno: %s\nCargo: %s",
                guarda.getIdentificador(),
                guarda.getNome(),
                guarda.getTurno(),
                guarda.getCargo());
    }
}
