package com.prisao;

import java.util.List;
import java.util.Random;

import com.prisao.controle.gerenciamento.local.GerenciaBlocos;
import com.prisao.controle.gerenciamento.local.GerenciaCelas;
import com.prisao.controle.gerenciamento.pessoa.GerenciaGuardas;
import com.prisao.controle.gerenciamento.pessoa.GerenciaPrisioneiros;

public class GerenciaDadosAleatorios {

    private static final List<String> NOMES_GUARDAS = List.of(
        "João", "Maria", "Carlos", "Ana", "Pedro", "Fernanda", "Luiz", "Clara", "Luan", "Marcos",
        "Paulo", "Paula", "Junior", "Mauro", "Helena", "Lucas", "Gabriel", "Isabela", "Rafael", "Juliana",
        "Ricardo", "Beatriz", "Felipe", "Raquel", "Thiago", "Camila", "Gustavo", "Larissa", "Victor", "Sofia",
        "Renato", "Patrícia", "Leandro", "Tatiane", "Ricardo", "Carla", "André", "Luciana", "Vítor", "Mariana",
        "Bruna", "Adriana", "Jorge", "Franciele", "Eduardo", "Vanessa", "Fernando", "Roberta", "Otávio", "Letícia",
        "Felipe", "Tatiane", "William", "Larissa", "Marcelo", "Cláudia", "Vinícius", "Regiane", "Simone", "Davi",
        "Valéria", "Ricardo", "Karina", "Emilly", "Carlos", "Francisco", "Rogério", "Raul", "Sabrina", "Diana",
        "Aline", "César", "Márcia", "Érica", "Antonio", "Luciana", "Maurício", "Tânia", "Fábio", "Viviane",
        "Eduardo", "Adriana", "José", "Tânia", "Silvia", "Lúcio", "Elaine", "Danilo", "Juliana", "Caroline",
        "Reginaldo", "Gisele", "Irene", "Milton", "Ingrid", "Hugo", "Renata", "Leila", "Edson", "Daniella",
        "Robson", "Paulo", "Jaqueline", "Carlos", "Bárbara", "Cintia", "Fábio", "Daniela", "Eliane", "Fabiana",
        "Henrique", "Ricardo", "Aline", "Alexandre", "Marisa", "Sérgio", "Samara", "Arthur", "Michele", "Luciano"
    );
    private static final List<String> SOBRENOMES = List.of(
        "Silva", "Costa", "Pereira", "Santos", "Oliveira", "Almeida", "Mendes", "Lima", "Souza", "Rodrigues",
        "Martins", "Carvalho", "Ferreira", "Gomes", "Ribeiro", "Pinto", "Araújo", "Machado", "Fernandes", "Batista",
        "Dias", "Simões", "Nunes", "Figueiredo", "Barros", "Moura", "Cunha", "Azevedo", "Teixeira", "Nascimento",
        "Ramos", "Gonçalves", "Castro", "Vieira", "Araujo", "Brandão", "Borges", "Campos", "Cardoso", "Pimentel",
        "Moreira", "Lima", "Montez", "Nóbrega", "Chaves", "Queiroz", "Bastos", "Monteiro", "Siqueira", "Tavares",
        "Ribeiro", "Silveira", "Lima", "Serrano", "Brito", "Nogueira", "Andrade", "Leite", "Cavalcante", "Reis",
        "Luz", "Roldão", "Neves", "Carneiro", "César", "Sá", "Costa", "Gomes", "Freitas", "Siqueira", "Pereira",
        "Borges", "Oliveira", "Macedo", "Magalhães", "Pinho", "Tavares", "Silveira", "Lima", "Martins", "Amorim",
        "Freitas", "Barbosa", "Souza", "Maciel", "Martins", "Castro", "Cavalcanti", "Marques", "Lopes", "Lima"
    );
    private static final List<String> TURNOS = List.of("Diurno", "Noturno");
    private static final List<String> CARGOS = List.of("Supervisor", "Auxiliar", "Chefe de Segurança", "Vigia", "Carcereiro");
    private static final List<String> NOMES_PRISIONEIROS = List.of("Paulo", "Paula", "Junior", "Mauro", "Helena", "Lucas", "Gabriel", "Isabela", "Rafael", "Juliana",
        "Ricardo", "Beatriz", "Felipe", "Raquel", "Thiago", "Camila", "Gustavo", "Larissa", "Victor", "Sofia",
        "Renato", "Patrícia", "Leandro", "Tatiane", "Ricardo", "Carla", "André", "Luciana", "Vítor", "Mariana",
        "Bruna", "Adriana", "Jorge", "Franciele", "Eduardo", "Vanessa", "Fernando", "Roberta", "Otávio", "Letícia",
        "Felipe", "Tatiane", "William", "Larissa", "Marcelo", "Cláudia", "Vinícius", "Regiane", "Simone", "Davi",
        "Valéria", "Ricardo", "Karina", "Emilly", "Carlos", "Francisco", "Rogério", "Raul", "Sabrina", "Diana",
        "Aline", "César", "Márcia", "Érica", "Antonio", "Luciana", "Maurício", "Tânia", "Fábio", "Viviane",
        "Eduardo", "Adriana", "José", "Tânia", "Silvia", "Lúcio", "Elaine", "Danilo", "Juliana", "Caroline",
        "Reginaldo", "Gisele", "Irene", "Milton", "Ingrid", "Hugo", "Renata", "Leila", "Edson", "Daniella",
        "Robson", "Paulo", "Jaqueline", "Carlos", "Bárbara", "Cintia", "Fábio", "Daniela", "Eliane", "Fabiana",
        "Henrique", "Ricardo", "Aline", "Alexandre", "Marisa", "Sérgio", "Samara", "Arthur", "Michele", "Luciano"
    );
    private static final List<String> CRIMES = List.of(
        "Roubo", "Assalto", "Homicídio", "Tráfico", "Furto", "Estupro", "Agressão", "Sequestro", "Corrupção", 
        "Fraude", "Extorsão", "Lavagem de Dinheiro", "Peculato", "Calúnia", "Falsificação", "Desvio de Dinheiro", 
        "Associação Criminosa", "Danos", "Vandalismo", "Posse Irregular de Arma", "Espionagem", "Tráfico de Armas", 
        "Racismo", "Discriminação", "Homofobia", "Tentativa de Homicídio", "Envolvimento com Organizações Criminosas", 
        "Tráfico de Pessoas", "Estelionato", "Roubo de Veículos", "Contrabando", "Pirâmide Financeira", "Fraude Eleitoral", 
        "Violência Doméstica", "Invasão de Propriedade", "Violação de Correspondência", "Abuso de Poder", "Bulling", 
        "Falsidade Ideológica", "Corrupção Passiva", "Corrupção Ativa", "Assédio Sexual", "Perseguição", "Terrorismo", 
        "Porte de Drogas", "Desacato", "Desobediência", "Coação", "Perjúrio", "Apostasia", "Lesão Corporal", 
        "Indústria de Fake News", "Furto de Energia", "Desmatamento Ilegal", "Roubo de Identidade", "Furto Qualificado"
    );
    
    private static final List<String> COMPORTAMENTOS = List.of("Bom", "Ruim", "Neutro");

    public static void preencherPrisaoComPseudodados(int n) {
        Random random = new Random();

        int totalBlocos = n / 50; // Número arbitrário de blocos baseado em n

        // Criar Blocos
        for (int i = 1; i <= totalBlocos; i++) {
            GerenciaBlocos.cadastrarBloco(i);
        }

        // Criar Celas em cada Bloco
        int celaId = 1;
        for (int blocoId = 1; blocoId <= totalBlocos; blocoId++) {
            int numCelasPorBloco = 2 + random.nextInt(8); // Entre 2 e 4 celas por bloco

            for (int j = 0; j < numCelasPorBloco; j++) {
                int capacidadeMaxima = 2 + random.nextInt(25); // Capacidade entre 2 e 6
                String nivelSeguranca = random.nextBoolean() ? "ALTA" : "MEDIA";
                GerenciaCelas.cadastrarCela(blocoId, celaId++, capacidadeMaxima, nivelSeguranca);
            }
        }

        // Criar Guardas
        for (int i = 1; i <= n / 10; i++) { // Guardas serão aproximadamente n/5
            int blocoId = 1 + random.nextInt(totalBlocos);
            String nome = NOMES_GUARDAS.get(random.nextInt(NOMES_GUARDAS.size())) + " " + SOBRENOMES.get(random.nextInt(SOBRENOMES.size())) + " " + SOBRENOMES.get(random.nextInt(SOBRENOMES.size()));
            String turno = TURNOS.get(random.nextInt(TURNOS.size()));
            String cargo = CARGOS.get(random.nextInt(CARGOS.size()));
            GerenciaGuardas.cadastrarGuarda(i, blocoId, nome, turno, cargo);
        }

        // Criar Prisioneiros
        int prisioneiroId = 1;
        for (int i = 0; i < n; i++) {
            int celaIdAleatorio = 1 + random.nextInt(celaId - 1);
            String nome = NOMES_PRISIONEIROS.get(random.nextInt(NOMES_PRISIONEIROS.size())) + " " + SOBRENOMES.get(random.nextInt(SOBRENOMES.size())) + " " + SOBRENOMES.get(random.nextInt(SOBRENOMES.size()));
            String crime = CRIMES.get(random.nextInt(CRIMES.size()));
            float pena = 1 + random.nextFloat() * 10; // Pena entre 1 e 10 anos
            String comportamento = COMPORTAMENTOS.get(random.nextInt(COMPORTAMENTOS.size()));
            GerenciaPrisioneiros.cadastrarPrisioneiro(prisioneiroId++, celaIdAleatorio, nome, crime, pena, comportamento);
        }
    }
}
