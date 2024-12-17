package com.prisao.controle.gerenciamento.pessoa;

import com.prisao.controle.persistencia.BackupManager;
import com.prisao.modelo.local.Prisao;

public class GerenciaDelegado {

    public boolean verificaSenha(String senha){
        if(Prisao.getInstancia().getDelegado().getSenha().equals(senha)){
            return true;
        }
        return false;
    }

    public boolean verificaEmail(String email){
        if(Prisao.getInstancia().getDelegado().getEmail().equals(email)){
            return true;
        }
        return false;
    }

    public boolean aprovaLogin(String email, String senha){
        if(verificaSenha(senha) && verificaEmail(email)){
            return true;
        }

        return false;
    }

    public boolean mudarSenha(String email, String senhaNova){
        if(verificaEmail(email)){
            Prisao.getInstancia().getDelegado().setSenha(senhaNova);
            BackupManager.salvarBackup(Prisao.getInstancia());
            return true;
        }

        return false;
    }
}
