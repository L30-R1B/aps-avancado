package com.prisao.controle.gerenciamento.pessoa;

import com.prisao.controle.gerenciamento.local.GerenciaCelas;
import com.prisao.controle.persistencia.implementacaoDAO.PrisaoDAO;
import com.prisao.controle.strategy.PrisioneiroStrategy;
import com.prisao.modelo.local.Bloco;
import com.prisao.modelo.local.Cela;
import com.prisao.modelo.local.Prisao;
import com.prisao.modelo.pessoa.Prisioneiro;

public class GerenciaPrisioneiros {

    public static boolean aplicarEstrategiaPrisioneiro(int identificador, PrisioneiroStrategy estrategia) {
        Prisioneiro prisioneiro = pesquisarPrisioneiro(identificador);
        if (prisioneiro == null) {
            return false;
        }
        return estrategia.aplicarEstrategia(prisioneiro);
    }

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
        PrisaoDAO.getInstance().salvar(Prisao.getInstancia());

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
                    PrisaoDAO.getInstance().salvar(Prisao.getInstancia());
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
            PrisaoDAO.getInstance().salvar(Prisao.getInstancia());
            return true;
        }

        return false;
    }

    public static boolean mudarComportamentoPrisioneiro(int identificadorPrisioneiro, String novoComportamento){
        Prisioneiro prisioneiro = pesquisarPrisioneiro(identificadorPrisioneiro);
        if(prisioneiro == null){
            return false;
        }

        prisioneiro.setComportamento(novoComportamento);
        PrisaoDAO.getInstance().salvar(Prisao.getInstancia());
        return true;
    }

    public static boolean aumentarPenaPrisioneiro(int identificadorPrisioneiro, float valorAumentoPena){
        Prisioneiro prisioneiro = pesquisarPrisioneiro(identificadorPrisioneiro);
        if(prisioneiro == null){
            return false;
        }

        prisioneiro.setPena(prisioneiro.getPena() + valorAumentoPena);
        PrisaoDAO.getInstance().salvar(Prisao.getInstancia());
        return true;
    }

    public static boolean diminuirPenaPrisioneiro(int identificadorPrisioneiro, float valorReducaoPena){
        Prisioneiro prisioneiro = pesquisarPrisioneiro(identificadorPrisioneiro);
        if(prisioneiro == null){
            return false;
        }

        prisioneiro.setPena(prisioneiro.getPena() - valorReducaoPena);

        if(prisioneiro.getPena() < 0.0){
            System.out.println("Prisioneiro " + prisioneiro.getNome() + " já cumpriu sua pena, portanto será removido do sistema.");
            desvincularPrisioneiro(identificadorPrisioneiro);
            PrisaoDAO.getInstance().salvar(Prisao.getInstancia());
        }

        return true;
    }
}
