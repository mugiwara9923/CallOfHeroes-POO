package historia;

import java.util.ArrayList;
import java.util.List;

public class RoteiroIntro {

    public static List<Cena> criar() {

        List<Cena> cenas = new ArrayList<>();

        // 🌍 COLAPSO
        cenas.add(new Cena("O COLAPSO", List.of(
                "O mundo não acabou de uma vez.",
                "Ele ruiu aos poucos.",
                "Crises se acumularam. Sistemas falharam.",
                "E quando as decisões erradas se tornaram regra…",
                "o colapso foi inevitável."
        ), "/intro/01_colapso.png"));

        // 🏙 MEGACIDADES
        cenas.add(new Cena("O NASCIMENTO DAS MEGACIDADES", List.of(
                "Governos nacionais perderam o controle.",
                "A população se concentrou em gigantes urbanos.",
                "Cidades que funcionavam como estados.",
                "Fortalezas de aço, vidro… e conflitos.",
                "Assim nasceram as megacidades."
        ), "/intro/02_megacidades.png"));

        // ⚙ PROGRESSO
        cenas.add(new Cena("O PREÇO DO PROGRESSO", List.of(
                "A tecnologia avançou décadas em poucos anos.",
                "Arranha-céus tocaram o céu.",
                "Drones patrulhavam ruas.",
                "Mas o crime, o caos e as crises cresceram junto.",
                "Alguém precisava decidir rápido."
        ), "/intro/03_progresso.png"));

        // 👤 MASON
        cenas.add(new Cena("MASON MERCER", List.of(
                "Meu nome é Mason Mercer.",
                "28 anos.",
                "Passei a vida analisando cenários de risco.",
                "Decisões sob pressão sempre foram meu trabalho.",
                "Mas nada me preparou para o que viria a seguir."
        ), "/intro/04_mason.png"));

        // 👩 HELENA
        cenas.add(new Cena("UMA VOZ DO PASSADO", List.of(
                "— Você ainda sabe tomar decisões difíceis, Mason?",
                "Aquela voz… eu conhecia desde a infância.",
                "Minha irmã.",
                "Se ela estava ali, algo estava muito errado."
        ), "/intro/05_helena.png"));

        // 💬 CONVITE
        cenas.add(new Cena("O CONVITE", List.of(
                "— Eu não vim te pedir ajuda.",
                "— Vim te oferecer responsabilidade.",
                "Ela falou sem rodeios.",
                "As megacidades estavam à beira do colapso.",
                "E alguém precisava coordenar quem iria para o campo."
        ), "/intro/06_convite.png"));

        // 🛰 ESTAÇÃO
        cenas.add(new Cena("A ESTAÇÃO DE COMANDO", List.of(
                "Ela me levou até a estação.",
                "Um centro de comando oculto no coração da cidade.",
                "Dali, decisões salvavam… ou condenavam setores inteiros.",
                "— Aqui você não luta, Mason.",
                "— Aqui você decide quem vai lutar."
        ), "/intro/07_estacao.png"));

        // 🦸 EQUIPE (imagem base)
        cenas.add(new Cena("A EQUIPE", List.of(
                "Ela deslizou os dados na tela.",
                "Seis nomes surgiram.",
                "— Eles são tudo o que temos.",
                "— E tudo o que precisamos, se você souber usá-los."
        ), "/intro/intro_base.png"));

        // 🦸 HERÓIS (usando pacote /herois)
        cenas.add(new Cena("DANTE RAVELLI", List.of(
                "— Dante Ravelli.",
                "Especialista em inteligência.",
                "Análise fria, leitura de cenários e decisões lógicas.",
                "— Use Dante quando a missão exigir cálculo e controle."
        ), "/herois/dante.png"));

        cenas.add(new Cena("LYSANDRA VEGA", List.of(
                "— Lysandra Vega.",
                "Furtividade é sua arma.",
                "Infiltração, sabotagem e operações silenciosas.",
                "— Missões discretas exigem alguém que nunca seja visto."
        ), "/herois/lysandra.png"));

        cenas.add(new Cena("KAEL MORROW", List.of(
                "— Kael Morrow.",
                "Força bruta.",
                "Quando não há negociação.",
                "— Kael resolve do jeito mais direto possível."
        ), "/herois/kael.png"));

        cenas.add(new Cena("NAELI", List.of(
                "— Naeli.",
                "Carisma.",
                "Influência, negociação e controle social.",
                "— Algumas crises se vencem com palavras."
        ), "/herois/naeli.png"));

        cenas.add(new Cena("RONIN ASHER", List.of(
                "— Ronin Asher.",
                "Velocidade acima de tudo.",
                "Resposta rápida e resgates urgentes.",
                "— Quando o tempo é o inimigo, Ronin é a escolha."
        ), "/herois/ronin.png"));

        cenas.add(new Cena("CORA", List.of(
                "— Cora.",
                "Resistência.",
                "Missões longas e ambientes hostis.",
                "— Ela aguenta quando ninguém mais aguenta."
        ), "/herois/cora.png"));

        // 🎯 FUNÇÃO DO JOGADOR
        cenas.add(new Cena("SUA FUNÇÃO", List.of(
                "— Eles vão ao campo.",
                "— Você decide quem vai.",
                "Cada missão exigirá a escolha certa.",
                "Um erro pode custar vidas.",
                "Essa responsabilidade agora é sua."
        ), "/intro/08_decisao.png"));

        // 🏁 FINAL
        cenas.add(new Cena("INÍCIO DAS OPERAÇÕES", List.of(
                "As telas se acenderam.",
                "O primeiro chamado chegou.",
                "Não havia mais tempo para dúvidas.",
                "Era hora de começar.",
                "Bem-vindo ao Call of Heroes."
        ), "/intro/09_inicio.png"));

        return cenas;
    }
}
