package com.prisao.controle.gerenciamento.local;

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

        Prisao.getInstancia().getBlocos().add(new Bloco(identificador));

        return true;
    }

    public static boolean removerBloco(int identificador){
        Bloco bloco = pesquisarBloco(identificador);
        if(bloco == null){
            return false;
        }

        Prisao.getInstancia().getBlocos().remove(bloco);

        return true;
    }
}
