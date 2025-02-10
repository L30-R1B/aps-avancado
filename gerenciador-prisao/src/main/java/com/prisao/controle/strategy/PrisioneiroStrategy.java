package com.prisao.controle.strategy;

import com.prisao.modelo.pessoa.Prisioneiro;

public interface PrisioneiroStrategy {
    boolean aplicarEstrategia(Prisioneiro prisioneiro);
}