package historia;

import java.util.ArrayList;
import java.util.List;

public class RoteiroEpilogo {

    public static List<Cena> criar(int sucessos, int falhas) {

        List<Cena> cenas = new ArrayList<>();

        if (sucessos >= 7) {
            cenas.add(new Cena(
                    "Nova Era",
                    List.of(
                            "A cidade resistiu.",
                            "As ações dos heróis restauraram a confiança.",
                            "Um novo capítulo começa."
                    ),
                    "/epilogo/cidade_luz.png"
            ));
        } else if (sucessos >= 5) {
            cenas.add(new Cena(
                    "Equilíbrio Frágil",
                    List.of(
                            "A cidade sobreviveu.",
                            "Mas cicatrizes profundas permaneceram.",
                            "O futuro ainda é incerto."
                    ),
                    "/epilogo/cidade_neutra.png"
            ));
        } else {
            cenas.add(new Cena(
                    "Dias Sombrios",
                    List.of(
                            "As falhas cobraram seu preço.",
                            "A cidade entrou em declínio.",
                            "A esperança se enfraqueceu."
                    ),
                    "/epilogo/cidade_sombria.png"
            ));
        }
        return cenas;
    }
}