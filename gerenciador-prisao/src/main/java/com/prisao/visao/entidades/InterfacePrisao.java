package com.prisao.visao.entidades;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.prisao.modelo.local.Bloco;
import com.prisao.modelo.local.Cela;
import com.prisao.modelo.local.Prisao;
import com.prisao.modelo.pessoa.Guarda;
import com.prisao.modelo.pessoa.Prisioneiro;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class InterfacePrisao extends JFrame {

    private Prisao prisao;

    public InterfacePrisao() {
        this.prisao = Prisao.getInstancia(); // Garantindo que a instância única seja carregada
        this.setTitle("Sistema de Prisão");
        this.setSize(1000, 600); // Aumentando o tamanho da janela para acomodar mais colunas
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Criando a tabela principal com informações dos blocos
        JScrollPane scrollPane = createTabelaComAgrupamento();

        this.add(scrollPane);
    }

    // Método para criar a tabela com as informações agrupadas por bloco
    private JScrollPane createTabelaComAgrupamento() {
        String[] columnNames = {
            "Bloco ID", "Guardas do Bloco", "Celas do Bloco", "Prisioneiros das Celas"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Preenchendo as linhas com dados dos blocos
        for (Bloco bloco : prisao.getBlocos()) {
            StringBuilder guardasInfo = new StringBuilder();
            for (Guarda guarda : bloco.getGuardas()) {
                guardasInfo.append("ID: ").append(guarda.getIdentificador())
                           .append(", Nome: ").append(guarda.getNome())
                           .append(", Cargo: ").append(guarda.getCargo())
                           .append(", Turno: ").append(guarda.getTurno())
                           .append(" | ");
            }

            StringBuilder celasInfo = new StringBuilder();
            for (Cela cela : bloco.getCelas()) {
                celasInfo.append("ID: ").append(cela.getIdentificador())
                          .append(", Capacidade: ").append(cela.getCapacidadeMaxima())
                          .append(", Nível de Segurança: ").append(cela.getNivelSeguranca())
                          .append(" | ");
            }

            StringBuilder prisioneirosInfo = new StringBuilder();
            for (Cela cela : bloco.getCelas()) {
                for (Prisioneiro prisioneiro : cela.getPrisioneiros()) {
                    prisioneirosInfo.append("ID: ").append(prisioneiro.getIdentificador())
                                     .append(", Nome: ").append(prisioneiro.getNome())
                                     .append(", Crime: ").append(prisioneiro.getCrime())
                                     .append(", Pena: ").append(prisioneiro.getPena())
                                     .append(", Comportamento: ").append(prisioneiro.getComportamento())
                                     .append(" | ");
                }
            }

            // Adicionando uma linha para o bloco
            Object[] row = {
                bloco.getIdentificador(), guardasInfo.toString(), celasInfo.toString(), prisioneirosInfo.toString()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Seleção de apenas uma linha

        // Adicionando um ouvinte de seleção de linha
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Ignora seleções em andamento
                    int row = table.getSelectedRow(); // Obter a linha selecionada
                    if (row != -1) {
                        // Aqui você pode adicionar a lógica para exibir a subtabela
                        mostrarInformacoesDetalhadas(row, table);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    // Método para exibir as informações detalhadas
    private void mostrarInformacoesDetalhadas(int row, JTable table) {
        String colunaSelecionada = table.getColumnName(table.getSelectedColumn());
        if (colunaSelecionada.equals("Guardas do Bloco")) {
            // Exibir informações dos guardas
            Bloco blocoSelecionado = prisao.getBlocos().get(row);
            mostrarInformacoesGuardas(blocoSelecionado);
        } else if (colunaSelecionada.equals("Celas do Bloco")) {
            // Exibir informações das celas
            Bloco blocoSelecionado = prisao.getBlocos().get(row);
            mostrarInformacoesCelas(blocoSelecionado);
        } else if (colunaSelecionada.equals("Prisioneiros das Celas")) {
            // Exibir informações dos prisioneiros
            Bloco blocoSelecionado = prisao.getBlocos().get(row);
            mostrarInformacoesPrisioneiros(blocoSelecionado);
        }
    }

    // Método para mostrar informações detalhadas das celas
    private void mostrarInformacoesCelas(Bloco bloco) {
        String[] columnNames = {
            "ID", "Capacidade Máxima", "Nível de Segurança"
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Cela cela : bloco.getCelas()) {
            Object[] rowData = {
                cela.getIdentificador(), "("+ cela.getPrisioneiros().size() + "/" + cela.getCapacidadeMaxima() + ")", cela.getNivelSeguranca()
            };
            model.addRow(rowData);
        }

        JTable subtabela = new JTable(model);
        JScrollPane subtabelaScrollPane = new JScrollPane(subtabela);

        // Adicionando um ouvinte para detectar cliques nas linhas da tabela de celas
        subtabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = subtabela.getSelectedRow();
                    if (row != -1) {
                        Cela celaSelecionada = bloco.getCelas().get(row);
                        mostrarPrisioneirosDaCela(celaSelecionada);
                    }
                }
            }
        });

        JFrame detalhamentoFrame = new JFrame("Detalhes das Celas");
        detalhamentoFrame.setSize(600, 400);
        detalhamentoFrame.add(subtabelaScrollPane, BorderLayout.CENTER);
        detalhamentoFrame.setVisible(true);
    }

    // Método para mostrar prisioneiros da cela selecionada
    private void mostrarPrisioneirosDaCela(Cela cela) {
        String[] columnNames = {
            "ID", "Nome", "Crime", "Pena", "Comportamento"
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Prisioneiro prisioneiro : cela.getPrisioneiros()) {
            Object[] rowData = {
                prisioneiro.getIdentificador(), prisioneiro.getNome(), prisioneiro.getCrime(),
                prisioneiro.getPena(), prisioneiro.getComportamento()
            };
            model.addRow(rowData);
        }

        JTable subtabela = new JTable(model);
        JScrollPane subtabelaScrollPane = new JScrollPane(subtabela);

        JFrame detalhamentoFrame = new JFrame("Prisioneiros da Cela");
        detalhamentoFrame.setSize(600, 400);
        detalhamentoFrame.add(subtabelaScrollPane, BorderLayout.CENTER);
        detalhamentoFrame.setVisible(true);
    }

    // Métodos para mostrar informações de guardas e prisioneiros...
    private void mostrarInformacoesGuardas(Bloco bloco) {
        String[] columnNames = {
            "ID", "Nome", "Cargo", "Turno"
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Guarda guarda : bloco.getGuardas()) {
            Object[] rowData = {
                guarda.getIdentificador(), guarda.getNome(), guarda.getCargo(), guarda.getTurno()
            };
            model.addRow(rowData);
        }

        JTable subtabela = new JTable(model);
        JScrollPane subtabelaScrollPane = new JScrollPane(subtabela);

        JFrame detalhamentoFrame = new JFrame("Guardas do Bloco");
        detalhamentoFrame.setSize(600, 400);
        detalhamentoFrame.add(subtabelaScrollPane, BorderLayout.CENTER);
        detalhamentoFrame.setVisible(true);
    }

    private void mostrarInformacoesPrisioneiros(Bloco bloco) {
        String[] columnNames = {
            "ID", "Nome", "Crime", "Pena", "Comportamento"
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Cela cela : bloco.getCelas()) {
            for (Prisioneiro prisioneiro : cela.getPrisioneiros()) {
                Object[] rowData = {
                    prisioneiro.getIdentificador(), prisioneiro.getNome(), prisioneiro.getCrime(),
                    prisioneiro.getPena(), prisioneiro.getComportamento()
                };
                model.addRow(rowData);
            }
        }

        JTable subtabela = new JTable(model);
        JScrollPane subtabelaScrollPane = new JScrollPane(subtabela);

        JFrame detalhamentoFrame = new JFrame("Prisioneiros do Bloco");
        detalhamentoFrame.setSize(600, 400);
        detalhamentoFrame.add(subtabelaScrollPane, BorderLayout.CENTER);
        detalhamentoFrame.setVisible(true);
    }
}