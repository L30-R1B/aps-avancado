package controle.gerenciamento.pessoa;

import controle.gerenciamento.local.GerenciaBlocos;
import modelo.local.Bloco;
import modelo.local.Prisao;
import modelo.pessoa.Guarda;

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

        Bloco bloco = GerenciaBlocos.pesquisaBloco(identificadorBloco);

        if(bloco == null){
            return false;
        }

        bloco.insereGuarda(new Guarda(identificadorGuarda, nome, turno, cargo));

        return true;
    }

    public static boolean desvincularGuarda(int identificador){
        Guarda guarda = pesquisarGuarda(identificador);
        if(guarda == null){
            return false;
        }

        for(Bloco blocoAtual : Prisao.getInstancia().getBlocos()){
            if(blocoAtual.removeGuarda(identificador)){
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

        Bloco bloco = GerenciaBlocos.pesquisaBloco(identificadorNovoBloco);
        if(bloco != null){
            Guarda guardaAux = new Guarda(guarda.getIdentificador(), guarda.getNome(), guarda.getTurno(), guarda.getCargo());
            desvincularGuarda(identificadorGuarda);
            cadastrarGuarda(guardaAux.getIdentificador(), identificadorNovoBloco, guardaAux.getNome(), guardaAux.getTurno(), guardaAux.getCargo());
            return true;
        }

        return false;
    }
}
