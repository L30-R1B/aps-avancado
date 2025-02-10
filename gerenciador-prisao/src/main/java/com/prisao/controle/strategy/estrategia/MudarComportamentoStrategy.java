package com.prisao.controle.strategy.estrategia;

import com.prisao.controle.strategy.PrisioneiroStrategy;
import com.prisao.modelo.pessoa.Prisioneiro;

public class MudarComportamentoStrategy implements PrisioneiroStrategy {
    private String novoComportamento;

    public MudarComportamentoStrategy(String novoComportamento) {
        this.novoComportamento = novoComportamento;
    }

    @Override
    public boolean aplicarEstrategia(Prisioneiro prisioneiro) {
        prisioneiro.setComportamento(novoComportamento);
        return true;
    }
}
