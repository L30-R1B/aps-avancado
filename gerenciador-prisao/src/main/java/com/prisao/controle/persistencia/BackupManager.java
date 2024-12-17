package com.prisao.controle.persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prisao.modelo.local.Prisao;

import java.io.*;

public class BackupManager {
    private static final String BACKUP_FILE = "backup_prisao.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Salva os dados da prisão em um arquivo JSON
    public static void salvarBackup(Prisao prisao) {
        try (Writer writer = new FileWriter(BACKUP_FILE)) {
            gson.toJson(prisao, writer);
            System.out.println("Backup salvo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o backup: " + e.getMessage());
        }
    }

    // Recupera os dados da prisão do arquivo JSON
    public static Prisao carregarBackup() {
        try (Reader reader = new FileReader(BACKUP_FILE)) {
            Prisao prisao = gson.fromJson(reader, Prisao.class);
            System.out.println("Backup carregado com sucesso!");
            return prisao;
        } catch (IOException e) {
            System.err.println("Erro ao carregar o backup: " + e.getMessage());
            return null;
        }
    }
}
