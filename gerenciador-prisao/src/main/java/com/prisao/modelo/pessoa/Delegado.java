package com.prisao.modelo.pessoa;

public class Delegado extends Individuo{
    private String telefone;
    private String email;
    private String senha;

    public Delegado(int identificador, String nome, String telefone, String email, String senha){
        super(identificador, nome);
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    public String getTelefone(){
        return this.telefone;
    }

    public String getEmail(){
        return this.email;
    }

    public String getSenha(){
        return this.senha;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    @Override
    public void printaIndiviuo(){
        super.printaIndiviuo();
        System.out.println("TELEFONE : " + this.telefone + "; EMAIL : " + this.email);
    }

}
