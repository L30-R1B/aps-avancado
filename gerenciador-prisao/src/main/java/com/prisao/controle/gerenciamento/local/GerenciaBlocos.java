package com.prisao.controle.gerenciamento.local;

import com.prisao.controle.persistencia.BackupManager;
import com.prisao.modelo.local.Bloco;
import com.prisao.modelo.local.Prisao;

public class GerenciaBlocos {
    public static Bloco pesquisarBloco(int identificador){
        for(Bloco blocoAtual : Prisao.getInstancia().getBlocos()){
            if(blocoAtual.getIdentificador() == identificador){
                return blocoAtual;
            }
        }

        return null;
    }

    public static boolean cadastrarBloco(int identificador){
        Bloco bloco = pesquisarBloco(identificador);
        if(bloco != null){
            return false;
        }

        if(Prisao.getInstancia().getBlocos().add(new Bloco(identificador)))
            BackupManager.salvarBackup(Prisao.getInstancia());

        return true;
    }

    public static boolean removerBloco(int identificador){
        Bloco bloco = pesquisarBloco(identificador);
        if(bloco == null){
            return false;
        }

        if(Prisao.getInstancia().getBlocos().remove(bloco))
            BackupManager.salvarBackup(Prisao.getInstancia());

        return true;
    }
}
