package com.prisao.controle.gerenciamento.pessoa;

import com.prisao.controle.gerenciamento.local.GerenciaBlocos;
import com.prisao.controle.persistencia.BackupManager;
import com.prisao.modelo.local.Bloco;
import com.prisao.modelo.local.Prisao;
import com.prisao.modelo.pessoa.Guarda;

public class GerenciaGuardas {
    public static Guarda pesquisarGuarda(int identificador){
        for(Bloco blocoAtual : Prisao.getInstancia().getBlocos()){
            for(Guarda guardaAtual : blocoAtual.getGuardas()){
                if(guardaAtual.getIdentificador() == identificador){
                    return guardaAtual;
                }
            }
        }
        return null;
    }

    public static boolean cadastrarGuarda(int identificadorGuarda, int identificadorBloco, String nome, String turno, String cargo){
        Guarda guarda = pesquisarGuarda(identificadorGuarda);
        
        if(guarda != null){
            return false;
        }

        Bloco bloco = GerenciaBlocos.pesquisarBloco(identificadorBloco);

        if(bloco == null){
            return false;
        }

        bloco.insereGuarda(new Guarda(identificadorGuarda, nome, turno, cargo));
        BackupManager.salvarBackup(Prisao.getInstancia());

        return true;
    }

    public static boolean desvincularGuarda(int identificador){
        Guarda guarda = pesquisarGuarda(identificador);
        if(guarda == null){
            return false;
        }

        for(Bloco blocoAtual : Prisao.getInstancia().getBlocos()){
            if(blocoAtual.removeGuarda(identificador)){
                BackupManager.salvarBackup(Prisao.getInstancia());
                return true;
            }
        }

        return false;
    }

    public static boolean transferirGuarda(int identificadorGuarda, int identificadorNovoBloco){
        Guarda guarda = pesquisarGuarda(identificadorGuarda);

        if(guarda == null){
            return false;
        }

        Bloco bloco = GerenciaBlocos.pesquisarBloco(identificadorNovoBloco);
        if(bloco != null){
            Guarda guardaAux = new Guarda(guarda.getIdentificador(), guarda.getNome(), guarda.getTurno(), guarda.getCargo());
            desvincularGuarda(identificadorGuarda);
            cadastrarGuarda(guardaAux.getIdentificador(), identificadorNovoBloco, guardaAux.getNome(), guardaAux.getTurno(), guardaAux.getCargo());
            BackupManager.salvarBackup(Prisao.getInstancia());
            return true;
        }

        return false;
    }
}
