package com.prisao.visao.entidades;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.prisao.modelo.local.Bloco;
import com.prisao.modelo.local.Cela;
import com.prisao.modelo.local.Prisao;
import com.prisao.modelo.pessoa.Guarda;
import com.prisao.modelo.pessoa.Prisioneiro;

public class InterfacePrisaoPaineis extends JFrame {

    private Prisao prisao;

    public InterfacePrisaoPaineis() {
        this.prisao = Prisao.getInstancia(); // Garantindo que a instância única seja carregada
        this.setTitle("Sistema de Prisão");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        JTabbedPane tabbedPane = new JTabbedPane(); // Usando abas para exibir diferentes entidades
        tabbedPane.addTab("Prisioneiros", createTabelaPrisioneiros());
        tabbedPane.addTab("Guardas", createTabelaGuardas());
        tabbedPane.addTab("Celas", createTabelaCelas());
        tabbedPane.addTab("Blocos", createTabelaBlocos());
        
        this.add(tabbedPane);
    }

    // Método para criar a tabela de Prisioneiros
    private JScrollPane createTabelaPrisioneiros() {
        String[] columnNames = {"ID", "Nome", "Crime", "Pena", "Comportamento"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (Bloco bloco : prisao.getBlocos()) {
            for (Cela cela : bloco.getCelas()) {
                for (Prisioneiro prisioneiro : cela.getPrisioneiros()) {
                    Object[] row = {prisioneiro.getIdentificador(), prisioneiro.getNome(), 
                                    prisioneiro.getCrime(), prisioneiro.getPena(), prisioneiro.getComportamento()};
                    model.addRow(row);
                }
            }
        }
        
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    // Método para criar a tabela de Guardas
    private JScrollPane createTabelaGuardas() {
        String[] columnNames = {"ID", "Nome", "Turno", "Cargo"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (Bloco bloco : prisao.getBlocos()) {
            for (Guarda guarda : bloco.getGuardas()) {
                Object[] row = {guarda.getIdentificador(), guarda.getNome(), guarda.getTurno(), guarda.getCargo()};
                model.addRow(row);
            }
        }
        
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    // Método para criar a tabela de Celas
    private JScrollPane createTabelaCelas() {
        String[] columnNames = {"ID", "Capacidade Maxima", "Nivel de Segurança"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (Bloco bloco : prisao.getBlocos()) {
            for (Cela cela : bloco.getCelas()) {
                Object[] row = {cela.getIdentificador(), cela.getCapacidadeMaxima(), cela.getNivelSeguranca()};
                model.addRow(row);
            }
        }
        
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    // Método para criar a tabela de Blocos
    private JScrollPane createTabelaBlocos() {
        String[] columnNames = {"ID", "Guardas", "Celas"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (Bloco bloco : prisao.getBlocos()) {
            String guardas = "";
            for (Guarda guarda : bloco.getGuardas()) {
                guardas += guarda.getNome() + " (" + guarda.getCargo() + "), ";
            }
            String celas = "";
            for (Cela cela : bloco.getCelas()) {
                celas += "Cela ID: " + cela.getIdentificador() + ", ";
            }
            
            Object[] row = {bloco.getIdentificador(), guardas, celas};
            model.addRow(row);
        }
        
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }
}
