import controle.gerenciamento.pessoa.GerenciaGuardas;
import modelo.local.Bloco;
import modelo.local.Cela;
import modelo.local.Prisao;

public class Programa {

    public static void main(String[] args) {
        Prisao prisao = Prisao.getInstancia();
        prisao.getBlocos().add(new Bloco(0));
        prisao.getBlocos().add(new Bloco(1));
        prisao.getBlocos().add(new Bloco(2));

        prisao.getBlocos().get(0).insereCela(new Cela(0, 10, "basico"));
        prisao.getBlocos().get(0).insereCela(new Cela(1, 10, "basico"));
        prisao.getBlocos().get(1).insereCela(new Cela(2, 10, "basico"));
        prisao.getBlocos().get(1).insereCela(new Cela(3, 10, "basico"));
        prisao.getBlocos().get(1).insereCela(new Cela(4, 10, "basico"));
        prisao.getBlocos().get(2).insereCela(new Cela(5, 10, "basico"));
        prisao.getBlocos().get(2).insereCela(new Cela(6, 10, "basico"));
        
        GerenciaGuardas.cadastrarGuarda(0, 0, "Guarda A", "noturno", "vigia");
        GerenciaGuardas.transferirGuarda(0, 1);
        GerenciaGuardas.desvincularGuarda(0);

        prisao.printaPrisao();
    }
}
