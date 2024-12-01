package com.prisao.modelo.pessoa;

public class Guarda extends Individuo{
    private String turno;
    private String cargo;

    public Guarda(int identificador, String nome, String turno, String cargo){
        super(identificador, nome);
        this.turno = turno;
        this.cargo = cargo;
    }

    public String getTurno(){
        return this.turno;
    }

    public String getCargo(){
        return this.cargo;
    }

    public void setTurno(String novoTurno){
        this.turno = novoTurno;
    }

    public void setCargo(String novoCargo){
        this.cargo = novoCargo;
    }

    @Override
    public void printaIndiviuo(){
        super.printaIndiviuo();
        System.out.println("TURNO : " + turno + "; CARGO : " + cargo);
    }
}
