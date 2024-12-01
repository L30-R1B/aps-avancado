package com.prisao.controle.gerenciamento.local;

import com.prisao.modelo.local.Bloco;
import com.prisao.modelo.local.Cela;
import com.prisao.modelo.local.Prisao;

public class GerenciaCelas {
    public static Cela pesquisarCela(int identificador){
        for(Bloco blocoAtual : Prisao.getInstancia().getBlocos()){
            for(Cela celaAtual : blocoAtual.getCelas()){
                if(celaAtual.getIdentificador() == identificador){
                    return celaAtual;
                }
            }
        }
        return null;
    }
    public static boolean cadastrarCela(int identificadorBloco, int identificadorCela, int capacidadeMaxima, String nivelSeguranca){   
        Bloco bloco = GerenciaBlocos.pesquisarBloco(identificadorBloco);
        if(bloco == null){
            return false;
        } 

        Cela cela = pesquisarCela(identificadorCela);
        if(cela != null){
            return false;
        }

        cela = new Cela(identificadorCela, capacidadeMaxima, nivelSeguranca);

        return bloco.insereCela(cela);
    }

    public static boolean removerCela(int identificadorCela){
        for(Bloco blocoAtual : Prisao.getInstancia().getBlocos()){
            for(Cela celaAtual : blocoAtual.getCelas()){
                if(celaAtual.getIdentificador() == identificadorCela){
                    blocoAtual.getCelas().remove(celaAtual);
                    return true;
                }
            }
        }
        return false;
    }
}
