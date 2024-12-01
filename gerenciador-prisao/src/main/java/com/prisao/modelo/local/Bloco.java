package com.prisao.modelo.local;

import java.util.ArrayList;
import java.util.List;

import com.prisao.modelo.pessoa.Guarda;

public class Bloco {
    private final int IDENTIFICADOR;
    private final List<Guarda> GUARDAS;
    private final List<Cela> CELAS;

    public Bloco(int identificador){
        this.IDENTIFICADOR = identificador;
        this.GUARDAS = new ArrayList<>();
        this.CELAS = new ArrayList<>();
    }

    public int getIdentificador(){
        return this.IDENTIFICADOR;
    }

    public List<Guarda> getGuardas(){
        return this.GUARDAS;
    }

    public List<Cela> getCelas(){
        return this.CELAS;
    }

    public boolean insereGuarda(Guarda guarda){
        return this.GUARDAS.add(guarda);
    }

    public boolean removeGuarda(int identificador){
        for(Guarda guardaAtual : this.GUARDAS){
            if(guardaAtual.getIdentificador() == identificador){
                return this.GUARDAS.remove(guardaAtual);
            }
        }
        return false;
    }

    public boolean insereCela(Cela cela){
        return this.CELAS.add(cela);
    }

    public void printaBloco(){
        System.out.println("\t====================================");
        System.out.println("\tIDENTIFICADOR DO BLOCO    : " + this.IDENTIFICADOR);
        System.out.println("\tGUARDAS NO BLOCO  : {");
        if(!this.GUARDAS.isEmpty()){
            for(Guarda guardaAtual : this.GUARDAS){
                System.out.print("\t");
                guardaAtual.printaIndiviuo();
            }
        }else{
            System.out.println("\t\tNENHUM");
        }
        System.out.println("\t}");
        System.out.println("\tCELAS EXISTENTES NO BLOCO : {");
        if(!this.CELAS.isEmpty()){
            for(Cela celaAtual : this.CELAS){
                System.out.println("\t\t----------------------------------------------");
                celaAtual.printCela();
                System.out.println("\t\t----------------------------------------------");
            }
        }else{
            System.out.println("\t\tNENHUMA");
        }
        System.out.println("\t}");
    }
}
