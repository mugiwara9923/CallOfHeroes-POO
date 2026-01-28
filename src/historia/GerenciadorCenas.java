package historia;

import java.util.List;

public class GerenciadorCenas {

    private List<Cena> cenas;

    public GerenciadorCenas() {
        this.cenas = RoteiroIntro.criar();

        if (cenas == null || cenas.isEmpty()) {
            throw new IllegalStateException("❌ ERRO: RoteiroIntro não criou cenas.");
        }
    }

    public List<Cena> getCenas() {
        return cenas;
    }
}

