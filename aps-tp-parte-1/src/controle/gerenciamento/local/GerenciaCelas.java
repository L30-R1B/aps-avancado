package controle.gerenciamento.local;

import modelo.local.Bloco;
import modelo.local.Cela;
import modelo.local.Prisao;

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
}
