package controle.gerenciamento.local;

import modelo.local.Bloco;
import modelo.local.Prisao;

public class GerenciaBlocos {
    public static Bloco pesquisaBloco(int identificador){
        for(Bloco blocoAtual : Prisao.getInstancia().getBlocos()){
            if(blocoAtual.getIdentificador() == identificador){
                return blocoAtual;
            }
        }

        return null;
    }
}
