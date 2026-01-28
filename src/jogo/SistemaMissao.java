package jogo;

import jogo.Chamado;
import java.util.ArrayList;
import java.util.List;

public class SistemaMissao {

    private List<Chamado> chamadosAtivos;

    public SistemaMissao() {
        this.chamadosAtivos = new ArrayList<>();
    }

    // Adiciona um novo chamado ao sistema
    public void adicionarChamado(Chamado chamado) {
        chamadosAtivos.add(chamado);
    }

    // Remove um chamado (quando herói é enviado)
    public void removerChamado(Chamado chamado) {
        chamadosAtivos.remove(chamado);
    }

    // Retorna lista de chamados ativos
    public List<Chamado> getChamadosAtivos() {
        return chamadosAtivos;
    }
}
