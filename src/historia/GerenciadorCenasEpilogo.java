package historia;

import java.util.List;

public class GerenciadorCenasEpilogo {

    private List<Cena> cenas;

    public GerenciadorCenasEpilogo(int sucessos, int falhas) {
        this.cenas = RoteiroEpilogo.criar(sucessos, falhas);
    }

    public List<Cena> getCenas() {
        return cenas;
    }
}
