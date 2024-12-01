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

import com.prisao.controle.gerenciamento.local.GerenciaBlocos;
import com.prisao.modelo.local.Bloco;
import com.prisao.modelo.local.Cela;
import com.prisao.modelo.pessoa.Guarda;

public class GerenciaBlocosUI extends JFrame {

    public GerenciaBlocosUI() {
        setTitle("Gerenciamento de Blocos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel principal
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        // Seção: Pesquisar Bloco
        JPanel pesquisarPanel = new JPanel(new BorderLayout());
        pesquisarPanel.setBorder(BorderFactory.createTitledBorder("Pesquisar Bloco"));

        JPanel inputPesquisarPanel = new JPanel(new FlowLayout());
        JTextField txtPesquisarId = new JTextField(10);
        JButton btnPesquisar = new JButton("Pesquisar");
        inputPesquisarPanel.add(new JLabel("ID do Bloco:"));
        inputPesquisarPanel.add(txtPesquisarId);
        inputPesquisarPanel.add(btnPesquisar);

        JTextArea resultadoPesquisa = new JTextArea(15, 50);
        resultadoPesquisa.setEditable(false);
        resultadoPesquisa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane scrollPane = new JScrollPane(resultadoPesquisa);

        pesquisarPanel.add(inputPesquisarPanel, BorderLayout.NORTH);
        pesquisarPanel.add(scrollPane, BorderLayout.CENTER);

        btnPesquisar.addActionListener(e -> {
            int id;
            try {
                id = Integer.parseInt(txtPesquisarId.getText());
                Bloco bloco = GerenciaBlocos.pesquisarBloco(id);
        
                if (bloco != null) {
                    resultadoPesquisa.setText(formatarDadosBloco(bloco));
                } else {
                    resultadoPesquisa.setText("Bloco não encontrado.");
                }
            } catch (NumberFormatException ex) {
                resultadoPesquisa.setText("ID inválido.");
            }
            txtPesquisarId.setText("");
        });

        panel.add(pesquisarPanel);

        JPanel cadastrarPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        cadastrarPanel.setBorder(BorderFactory.createTitledBorder("Cadastrar Bloco"));

        JTextField txtCadastrarId = new JTextField();
        JButton btnCadastrar = new JButton("Cadastrar");
        JLabel lblCadastrarResultado = new JLabel();

        cadastrarPanel.add(new JLabel("ID do Bloco:"));
        cadastrarPanel.add(txtCadastrarId);
        cadastrarPanel.add(btnCadastrar);
        cadastrarPanel.add(lblCadastrarResultado);

        btnCadastrar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtCadastrarId.getText());
                boolean sucesso = GerenciaBlocos.cadastrarBloco(id);
                if (sucesso) {
                    lblCadastrarResultado.setText("Bloco cadastrado com sucesso!");
                } else {
                    lblCadastrarResultado.setText("Falha ao cadastrar bloco. ID já existe.");
                }
            } catch (NumberFormatException ex) {
                lblCadastrarResultado.setText("ID inválido.");
            }
            txtCadastrarId.setText("");

            javax.swing.Timer timer = new javax.swing.Timer(3000, ev -> lblCadastrarResultado.setText(""));
            timer.setRepeats(false);
            timer.start();
        });

        panel.add(cadastrarPanel);

        JPanel removerPanel = new JPanel(new FlowLayout());
        removerPanel.setBorder(BorderFactory.createTitledBorder("Remover Bloco"));

        JTextField txtRemoverId = new JTextField(10);
        JButton btnRemover = new JButton("Remover");
        JLabel lblRemoverResultado = new JLabel();

        removerPanel.add(new JLabel("ID do Bloco:"));
        removerPanel.add(txtRemoverId);
        removerPanel.add(btnRemover);
        removerPanel.add(lblRemoverResultado);

        btnRemover.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtRemoverId.getText());
                boolean sucesso = GerenciaBlocos.removerBloco(id);
                if (sucesso) {
                    lblRemoverResultado.setText("Bloco removido com sucesso!");
                } else {
                    lblRemoverResultado.setText("Falha ao remover bloco. Bloco não encontrado.");
                }
            } catch (NumberFormatException ex) {
                lblRemoverResultado.setText("ID inválido.");
            }
            txtRemoverId.setText("");

            javax.swing.Timer timer = new javax.swing.Timer(3000, ev -> lblRemoverResultado.setText(""));
            timer.setRepeats(false);
            timer.start();
        });

        panel.add(removerPanel);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    private String formatarDadosBloco(Bloco bloco) {
        StringBuilder sb = new StringBuilder();
        sb.append("IDENTIFICADOR DO BLOCO: ").append(bloco.getIdentificador()).append("\n");

        sb.append("GUARDAS NO BLOCO:\n");
        List<Guarda> guardas = bloco.getGuardas();
        if (!guardas.isEmpty()) {
            for (Guarda guarda : guardas) {
                sb.append("\t- ").append(guarda.toString()).append("\n");
            }
        } else {
            sb.append("\tNenhum guarda no bloco.\n");
        }

        sb.append("CELAS EXISTENTES NO BLOCO:\n");
        List<Cela> celas = bloco.getCelas();
        if (!celas.isEmpty()) {
            for (Cela cela : celas) {
                sb.append("\t").append(formatarDadosCela(cela)).append("\n");
            }
        } else {
            sb.append("\tNenhuma cela no bloco.\n");
        }

        return sb.toString();
    }

    private String formatarDadosCela(Cela cela) {
        StringBuilder sb = new StringBuilder();
        sb.append("IDENTIFICADOR DA CELA: ").append(cela.getIdentificador()).append("; ");
        sb.append("CAPACIDADE MÁXIMA: (")
          .append(cela.getPrisioneiros().size())
          .append("/")
          .append(cela.getCapacidadeMaxima())
          .append("); ");
        sb.append("NÍVEL DE SEGURANÇA: ").append(cela.getNivelSeguranca());
        return sb.toString();
    }
}
