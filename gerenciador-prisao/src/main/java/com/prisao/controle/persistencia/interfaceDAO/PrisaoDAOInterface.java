package com.prisao.controle.persistencia.interfaceDAO;

import com.prisao.modelo.local.Prisao;

public interface PrisaoDAOInterface {
    void salvar(Prisao prisao);
    Prisao carregar();
}
