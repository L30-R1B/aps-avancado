package com.prisao.controle.strategy.estrategia;

import com.prisao.controle.strategy.PrisioneiroStrategy;
import com.prisao.modelo.pessoa.Prisioneiro;

public class AumentarPenaStrategy implements PrisioneiroStrategy {
    private float valorAumento;

    public AumentarPenaStrategy(float valorAumento) {
        this.valorAumento = valorAumento;
    }

    @Override
    public boolean aplicarEstrategia(Prisioneiro prisioneiro) {
        prisioneiro.setPena(prisioneiro.getPena() + valorAumento);
        return true;
    }
}

