package jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeradorChamados {

    private final Random random = new Random();
    private final List<Chamado> chamadosDisponiveis = new ArrayList<>();

    public GeradorChamados() {

        // ================= CARISMA =================
        chamadosDisponiveis.add(new Chamado(
                "Leilão Beneficente",
                "Convencer uma socialite influente a apoiar a fundação.",
                "Salão Imperial Aurora",
                "carisma",
                520,
                290
        ));

        chamadosDisponiveis.add(new Chamado(
                "Crise Diplomática",
                "Acalmar tensões entre líderes de distritos rivais.",
                "Câmara Cívica Central",
                "carisma",
                480,
                260
        ));

        // ================= INTELIGENCIA =================
        chamadosDisponiveis.add(new Chamado(
                "Conspiração Política",
                "Descobrir informações confidenciais antes que vazem.",
                "Palácio do Conselho",
                "inteligencia",
                430,
                210
        ));

        chamadosDisponiveis.add(new Chamado(
                "Decodificação Urgente",
                "Interpretar dados criptografados sobre uma ameaça iminente.",
                "Centro de Análise Ômega",
                "inteligencia",
                610,
                240
        ));

        // ================= FURTIVIDADE =================
        chamadosDisponiveis.add(new Chamado(
                "Infiltração Corporativa",
                "Obter dados sigilosos sem levantar suspeitas.",
                "Torre Nexus",
                "furtividade",
                460,
                310
        ));

        chamadosDisponiveis.add(new Chamado(
                "Vigilância Secreta",
                "Seguir um suspeito sem ser detectado.",
                "Distrito das Sombras",
                "furtividade",
                540,
                380
        ));

        // ================= VELOCIDADE =================
        chamadosDisponiveis.add(new Chamado(
                "Perseguição Urbana",
                "Capturar um alvo antes que ele fuja da cidade.",
                "Anel Viário Leste",
                "velocidade",
                680,
                420
        ));

        chamadosDisponiveis.add(new Chamado(
                "Entrega de Emergência",
                "Levar suprimentos médicos sob tempo crítico.",
                "Zona Hospitalar",
                "velocidade",
                600,
                260
        ));

        // ================= FORÇA =================
        chamadosDisponiveis.add(new Chamado(
                "Briga de Gangues",
                "Conter um confronto violento antes que se espalhe.",
                "Zona Portuária",
                "forca",
                560,
                510
        ));

        chamadosDisponiveis.add(new Chamado(
                "Resgate em Colapso",
                "Remover escombros para salvar civis presos.",
                "Complexo Residencial Atlas",
                "forca",
                620,
                460
        ));

        // ================= RESISTÊNCIA =================
        chamadosDisponiveis.add(new Chamado(
                "Vazamento Químico",
                "Conter uma substância tóxica e evacuar a área.",
                "Laboratório Helix",
                "resistencia",
                470,
                350
        ));

        chamadosDisponiveis.add(new Chamado(
                "Motim na Prisão",
                "Retomar o controle em um ambiente hostil e prolongado.",
                "Complexo Blackstone",
                "resistencia",
                640,
                390
        ));
    }

    public Chamado gerarChamado() {
        Chamado escolhido = chamadosDisponiveis.get(
                random.nextInt(chamadosDisponiveis.size())
        );

        escolhido.ativar();
        return escolhido;
    }
}
