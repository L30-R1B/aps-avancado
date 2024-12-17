package com.prisao;
import com.prisao.modelo.local.Prisao;
import com.prisao.visao.login.LoginUI;

public class App {
    public static void main(String[] args) {

        Prisao.getInstancia();
        new LoginUI().criarTelaLogin();
    }
}
