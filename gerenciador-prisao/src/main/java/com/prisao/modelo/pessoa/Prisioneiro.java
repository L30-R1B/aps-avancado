package com.prisao.modelo.pessoa;

public class Prisioneiro extends Individuo{
    private final String CRIME;
    private float pena;
    private String comportamento;

    public Prisioneiro(int identificador, String nome, String crime, float pena, String comportamento){
        super(identificador, nome);
        this.CRIME = crime;
        this.pena = pena;
        this.comportamento = comportamento;
    }

    public String getCrime(){
        return this.CRIME;
    }

    public float getPena(){
        return this.pena;
    }

    public String getComportamento(){
        return this.comportamento;
    }

    public void setComportamento(String novoComportamento){
        this.comportamento = novoComportamento;
    }

    public void setPena(float novaPena){
        this.pena = novaPena;
    }

    @Override
    public void printaIndiviuo(){
        super.printaIndiviuo();
        System.out.println("CRIME COMETIDO : " + this.CRIME + "; PENA : " + this.pena + " anos; COMPORTAMENTO : " + this.comportamento);
    }

    @Override
    public String toString() {
        return super.toString() + 
            "; CRIME COMETIDO: " + this.CRIME + 
            "; PENA: " + this.pena + " anos" + 
            "; COMPORTAMENTO: " + this.comportamento;
    }

}
