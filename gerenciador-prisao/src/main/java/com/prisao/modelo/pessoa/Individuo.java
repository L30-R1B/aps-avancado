package com.prisao.modelo.pessoa;

public class Individuo{
    private final int IDENTIFICADOR;
    private final String NOME;

    public Individuo(int identificador, String nome){
        this.IDENTIFICADOR = identificador;
        this.NOME = nome;
    }

    public int getIdentificador(){
        return this.IDENTIFICADOR;
    }

    public String getNome(){
        return this.NOME;
    }

    public void printaIndiviuo(){
        System.out.print("\t" + "ID : " + this.IDENTIFICADOR + "; NOME : " + this.NOME + "; ");
    }

    public String toString(){
        return "ID: " + this.IDENTIFICADOR + 
        "; NOME: " + this.NOME;
    }
}
