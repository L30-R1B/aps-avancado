package com.prisao.visao.local;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.prisao.controle.gerenciamento.local.GerenciaCelas;
import com.prisao.modelo.local.Cela;
import com.prisao.modelo.pessoa.Prisioneiro;

public class GerenciaCelasUI extends JFrame {

    public GerenciaCelasUI() {
        setTitle("Gerenciamento de Celas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JPanel pesquisarPanel = new JPanel(new BorderLayout());
        pesquisarPanel.setBorder(BorderFactory.createTitledBorder("Pesquisar Cela"));

        JPanel inputPesquisarPanel = new JPanel(new FlowLayout());
        JTextField txtPesquisarId = new JTextField(10);
        JButton btnPesquisar = new JButton("Pesquisar");
        inputPesquisarPanel.add(new JLabel("ID da Cela:"));
        inputPesquisarPanel.add(txtPesquisarId);
        inputPesquisarPanel.add(btnPesquisar);

        JTextArea resultadoPesquisa = new JTextArea(10, 40);
        resultadoPesquisa.setEditable(false);
        resultadoPesquisa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane scrollPane = new JScrollPane(resultadoPesquisa);

        pesquisarPanel.add(inputPesquisarPanel, BorderLayout.NORTH);
        pesquisarPanel.add(scrollPane, BorderLayout.CENTER);

        btnPesquisar.addActionListener(e -> {
            int id;
            try {
                id = Integer.parseInt(txtPesquisarId.getText());
                Cela cela = GerenciaCelas.pesquisarCela(id);
        
                if (cela != null) {
                    resultadoPesquisa.setText(formatarDadosCela(cela));
                } else {
                    resultadoPesquisa.setText("Cela não encontrada.");
                }
            } catch (NumberFormatException ex) {
                resultadoPesquisa.setText("ID inválido.");
            } finally {
                txtPesquisarId.setText("");
            }
        });

        panel.add(pesquisarPanel);

        JPanel cadastrarPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        cadastrarPanel.setBorder(BorderFactory.createTitledBorder("Cadastrar Cela"));

        JTextField txtBlocoId = new JTextField();
        JTextField txtCelaId = new JTextField();
        JTextField txtCapacidade = new JTextField();
        JTextField txtNivelSeguranca = new JTextField();
        JButton btnCadastrar = new JButton("Cadastrar");
        JLabel lblCadastrarResultado = new JLabel();

        cadastrarPanel.add(new JLabel("ID do Bloco:"));
        cadastrarPanel.add(txtBlocoId);
        cadastrarPanel.add(new JLabel("ID da Cela:"));
        cadastrarPanel.add(txtCelaId);
        cadastrarPanel.add(new JLabel("Capacidade Máxima:"));
        cadastrarPanel.add(txtCapacidade);
        cadastrarPanel.add(new JLabel("Nível de Segurança:"));
        cadastrarPanel.add(txtNivelSeguranca);
        cadastrarPanel.add(btnCadastrar);
        cadastrarPanel.add(lblCadastrarResultado);

        btnCadastrar.addActionListener(e -> {
            try {
                int blocoId = Integer.parseInt(txtBlocoId.getText());
                int celaId = Integer.parseInt(txtCelaId.getText());
                int capacidade = Integer.parseInt(txtCapacidade.getText());
                String nivelSeguranca = txtNivelSeguranca.getText();
        
                boolean sucesso = GerenciaCelas.cadastrarCela(blocoId, celaId, capacidade, nivelSeguranca);
                if (sucesso) {
                    lblCadastrarResultado.setText("Cela cadastrada com sucesso!");
                } else {
                    lblCadastrarResultado.setText("Falha ao cadastrar cela.");
                }
            } catch (NumberFormatException ex) {
                lblCadastrarResultado.setText("Dados inválidos.");
            } finally {
                txtBlocoId.setText("");
                txtCelaId.setText("");
                txtCapacidade.setText("");
                txtNivelSeguranca.setText("");
            }

            javax.swing.Timer timer = new javax.swing.Timer(3000, ev -> lblCadastrarResultado.setText(""));
            timer.setRepeats(false);
            timer.start();
        });

        panel.add(cadastrarPanel);

        JPanel removerPanel = new JPanel(new FlowLayout());
        removerPanel.setBorder(BorderFactory.createTitledBorder("Remover Cela"));
        JTextField txtRemoverId = new JTextField(10);
        JButton btnRemover = new JButton("Remover");
        JLabel lblRemoverResultado = new JLabel();
        removerPanel.add(new JLabel("ID da Cela:"));
        removerPanel.add(txtRemoverId);
        removerPanel.add(btnRemover);
        removerPanel.add(lblRemoverResultado);

        btnRemover.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtRemoverId.getText());
                boolean sucesso = GerenciaCelas.removerCela(id);
                if (sucesso) {
                    lblRemoverResultado.setText("Cela removida com sucesso!");
                } else {
                    lblRemoverResultado.setText("Falha ao remover cela.");
                }
            } catch (NumberFormatException ex) {
                lblRemoverResultado.setText("ID inválido.");
            } finally {
                txtRemoverId.setText("");
            }

            javax.swing.Timer timer = new javax.swing.Timer(3000, ev -> lblRemoverResultado.setText(""));
            timer.setRepeats(false);
            timer.start();
        });

        panel.add(removerPanel);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    private String formatarDadosCela(Cela cela) {
        StringBuilder sb = new StringBuilder();
        sb.append("IDENTIFICADOR DA CELA: ").append(cela.getIdentificador()).append("\n");
        sb.append("CAPACIDADE MÁXIMA: (")
          .append(cela.getPrisioneiros().size())
          .append("/")
          .append(cela.getCapacidadeMaxima())
          .append(")\n");
        sb.append("NÍVEL DE SEGURANÇA: ").append(cela.getNivelSeguranca()).append("\n");
        sb.append("LISTA DE PRISIONEIROS:\n");

        List<Prisioneiro> prisioneiros = cela.getPrisioneiros();
        if (!prisioneiros.isEmpty()) {
            for (Prisioneiro prisioneiro : prisioneiros) {
                sb.append("\t- ").append(prisioneiro.toString()).append("\n");
            }
        } else {
            sb.append("\tNenhum prisioneiro.\n");
        }

        return sb.toString();
    }

}
