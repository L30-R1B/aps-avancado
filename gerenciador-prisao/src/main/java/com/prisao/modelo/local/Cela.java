package com.prisao.modelo.local;

import java.util.ArrayList;
import java.util.List;

import com.prisao.modelo.pessoa.Prisioneiro;

public class Cela {
    private final int IDENTIFICADOR;
    private final int CAPACIDADE_MAXIMA;
    private final String NIVEL_SEGURANCA;
    private final List<Prisioneiro> PRISIONEIROS;

    public Cela(int identificador, int capacidade, String nivelSeguranca){
        this.IDENTIFICADOR = identificador;
        this.CAPACIDADE_MAXIMA = capacidade;
        this.NIVEL_SEGURANCA = nivelSeguranca;
        this.PRISIONEIROS = new ArrayList<>();
    }

    public int getIdentificador(){
        return this.IDENTIFICADOR;
    }

    public int getCapacidadeMaxima(){
        return this.CAPACIDADE_MAXIMA;
    }

    public String getNivelSeguranca(){
        return this.NIVEL_SEGURANCA;
    }

    public List<Prisioneiro> getPrisioneiros(){
        return this.PRISIONEIROS;
    }

    public boolean inserePrisioneiro(Prisioneiro prisioneiro){
        if(this.PRISIONEIROS.size() < this.CAPACIDADE_MAXIMA){
            return this.PRISIONEIROS.add(prisioneiro);
        }
        return false;
    }

    public boolean removePrisioneiro(int identificador){
        for(Prisioneiro prisioneiroAtual : this.PRISIONEIROS){
            if(prisioneiroAtual.getIdentificador() == identificador){
                return this.PRISIONEIROS.remove(prisioneiroAtual);
            }
        }
        return false;
    }

    public void printCela(){
        System.out.println("\t\tIDENTIFICADOR DA CELA : " + this.IDENTIFICADOR);
        System.out.println("\t\tCAPACIDADE MÁXIMA     : (" + this.PRISIONEIROS.size() + "/" + this.CAPACIDADE_MAXIMA + ")");
        System.out.println("\t\tNÍVEL DE SEGURANÇA    : " + this.NIVEL_SEGURANCA);
        System.out.println("\t\tLISTA DE PRISIONEIROS : {");
        if(!this.PRISIONEIROS.isEmpty()){
            for(Prisioneiro prisioneiroAtual : this.PRISIONEIROS){
                System.out.print("\t\t");
                prisioneiroAtual.printaIndiviuo();
            }
        }else{
            System.out.println("\t\t\tNENHUM");
        }
        System.out.println("\t\t}");
    }
}
