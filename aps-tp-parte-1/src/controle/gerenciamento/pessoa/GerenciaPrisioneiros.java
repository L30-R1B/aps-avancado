package controle.gerenciamento.pessoa;

import controle.gerenciamento.local.GerenciaCelas;
import modelo.local.Bloco;
import modelo.local.Cela;
import modelo.local.Prisao;
import modelo.pessoa.Prisioneiro;

public class GerenciaPrisioneiros {
    public static Prisioneiro pesquisarPrisioneiro(int identificador){
        for(Bloco blocoAtual : Prisao.getInstancia().getBlocos()){
            for(Cela celaATual : blocoAtual.getCelas()){
                for(Prisioneiro prisioneiroAtual : celaATual.getPrisioneiros()){
                    if(prisioneiroAtual.getIdentificador() == identificador){
                        return prisioneiroAtual;
                    }
                }
            }
        }
        
        return null;
    }

    public static boolean cadastrarPrisioneiro(int identificadorPrisioneiro, int identificadorCela, String nome, String crime, float pena, String comportamento){
        if(pesquisarPrisioneiro(identificadorPrisioneiro) != null){
            return false;
        }

        Cela cela = GerenciaCelas.pesquisarCela(identificadorCela);

        if(cela == null){
            return false;
        }
        
        cela.inserePrisioneiro(new Prisioneiro(identificadorPrisioneiro, nome, crime, pena, comportamento));

        return true;
    }

    public static boolean desvincularPrisioneiro(int identificador){
        Prisioneiro prisioneiro = pesquisarPrisioneiro(identificador);
        if(prisioneiro == null){
            return false;
        }

        for(Bloco blocoAtual : Prisao.getInstancia().getBlocos()){
            for(Cela celaAtual : blocoAtual.getCelas()){
                if(celaAtual.removePrisioneiro(identificador)){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static boolean transferirPrisioneiro(int identificadorPrisioneiro, int identificadorNovaCela){
        Prisioneiro prisioneiro = pesquisarPrisioneiro(identificadorPrisioneiro);

        if(prisioneiro == null){
            return false;
        }

        Cela cela = GerenciaCelas.pesquisarCela(identificadorNovaCela);
        if(cela != null){
            Prisioneiro prisioAux = new Prisioneiro(prisioneiro.getIdentificador(), prisioneiro.getNome(), prisioneiro.getCrime(), prisioneiro.getPena(), prisioneiro.getComportamento());
            desvincularPrisioneiro(identificadorPrisioneiro);
            cadastrarPrisioneiro(prisioAux.getIdentificador(), identificadorNovaCela, prisioAux.getNome(), prisioAux.getCrime(), prisioAux.getPena(), prisioAux.getComportamento());
            return true;
        }

        return false;
    }
}
