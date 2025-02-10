package com.prisao.controle.strategy.estrategia;

import com.prisao.controle.strategy.PrisioneiroStrategy;
import com.prisao.modelo.pessoa.Prisioneiro;;

public class DiminuirPenaStrategy implements PrisioneiroStrategy {
    private float valorReducao;

    public DiminuirPenaStrategy(float valorReducao) {
        this.valorReducao = valorReducao;
    }

    @Override
    public boolean aplicarEstrategia(Prisioneiro prisioneiro) {
        float novaPena = prisioneiro.getPena() - valorReducao;
        if (novaPena < 0) {
            novaPena = 0;
        }
        prisioneiro.setPena(novaPena);
        return true;
    }
}

