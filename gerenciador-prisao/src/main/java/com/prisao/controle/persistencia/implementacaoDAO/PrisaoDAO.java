package com.prisao.controle.persistencia.implementacaoDAO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prisao.controle.persistencia.interfaceDAO.PrisaoDAOInterface;
import com.prisao.modelo.local.Prisao;

public class PrisaoDAO implements PrisaoDAOInterface {
    private static final String BACKUP_FILE = "backup_prisao.json";
    private final Gson gson;

    private static PrisaoDAO instancia;

    private PrisaoDAO() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public static synchronized PrisaoDAO getInstance() {
        if (instancia == null) {
            instancia = new PrisaoDAO();
        }
        return instancia;
    }

    @Override
    public void salvar(Prisao prisao) {
        try (Writer writer = new FileWriter(BACKUP_FILE)) {
            gson.toJson(prisao, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o backup: " + e.getMessage());
        }
    }

    @Override
    public Prisao carregar() {
        try (Reader reader = new FileReader(BACKUP_FILE)) {
            Prisao prisao = gson.fromJson(reader, Prisao.class);
            return prisao;
        } catch (IOException e) {
            System.err.println("Erro ao carregar o backup: " + e.getMessage());
            return null;
        }
    }
}
