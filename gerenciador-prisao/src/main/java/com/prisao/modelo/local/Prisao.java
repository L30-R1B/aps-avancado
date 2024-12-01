package com.prisao.modelo.local;

import java.util.ArrayList;
import java.util.List;

import com.prisao.modelo.pessoa.Delegado;


public class Prisao {
    private static Prisao instanciaUnica;

    private final Delegado DELEGADO;
    private final List<Bloco> BLOCOS;
    private int quantidadePresos;
    private int quantidadeGuardas;

    private Prisao() {
        this.DELEGADO = new Delegado(1, "Delegado Padrão", "0000-0000", "delegado@padrao.com", "12345");
        this.BLOCOS = new ArrayList<>();
        this.quantidadeGuardas = 0;
        this.quantidadePresos = 0;
    }

    public static Prisao getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Prisao();
        }
        return instanciaUnica;
    }

    public Delegado getDelegado(){
        return this.DELEGADO;
    }

    public List<Bloco> getBlocos(){
        return this.BLOCOS;
    }

    public int getQuantidadePresos(){
        return this.quantidadePresos;
    }

    public int getQuantidadeGuardas(){
        return this.quantidadeGuardas;
    }

    public void setQuantidadePresos(int novaQuantidadePresos){
        this.quantidadePresos = novaQuantidadePresos;
    }

    public void setQuantidadeGuardas(int novaQuantidadeGuardas){
        this.quantidadeGuardas = novaQuantidadeGuardas;
    }

    public void printaPrisao(){
        System.out.println("__________________________________________________________");
        System.out.println("DELEGADO :");
        this.DELEGADO.printaIndiviuo();
        System.out.println("\nQUANTIDADE DE GUARDAS : " + this.quantidadeGuardas + "   QUANTIDADE DE PRESOS : " + this.quantidadePresos);
        System.out.println("\nBLOCOS DA PRISÃO : {");
        if(!this.BLOCOS.isEmpty()){
            for(Bloco blocoAtual : this.BLOCOS){
                blocoAtual.printaBloco();
                System.out.println();
            }
        }else{
            System.out.println("\tNENHUM");
        }
        System.out.println("}");
        System.out.println("__________________________________________________________");
    }
}
