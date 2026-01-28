package jogo;

public class Chamado {

    // ================= IDENTIDADE =================
    private static int contador = 1;
    private final int id;

    // ================= DADOS =================
    private String nome;
    private String descricao;
    private String localizacao;
    private String atributoChave;
    private int x;
    private int y;
    private boolean ativo;

    // dificuldade do chamado
    private boolean dificil;

    private Heroi heroiDesignado;

    // RESULTADO DA MISSÃO
    private Boolean sucesso;

    public Chamado(String nome,
                   String descricao,
                   String localizacao,
                   String atributoChave,
                   int x,
                   int y) {

        this.id = contador++;

        this.nome = nome;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.atributoChave = atributoChave.toLowerCase();
        this.x = x;
        this.y = y;
        this.ativo = false;

        this.dificil = false;

        this.heroiDesignado = null;
        this.sucesso = null;
    }

    // =================================================
    // CICLO DO CHAMADO
    // =================================================

    public void ativar() {
        this.ativo = true;
        this.heroiDesignado = null;
        this.sucesso = null;
    }

    //  cálculo
    public void ativar(Heroi heroi) {
        this.ativo = true;
        this.heroiDesignado = heroi;

        // REGRA SIMPLES
        this.sucesso = heroi.getEspecialidade()
                .equalsIgnoreCase(atributoChave);
    }

    public void finalizar() {
        this.ativo = false;
        this.heroiDesignado = null;
    }

    // =================================================
    // DIFICULDADE
    // =================================================

    public boolean isDificil() {
        return dificil;
    }

    public void setDificil(boolean dificil) {
        this.dificil = dificil;
    }

    // =================================================
    // RESULTADO
    // =================================================

    public boolean isResolvido() {
        return sucesso != null;
    }

    public boolean isSucesso() {
        return Boolean.TRUE.equals(sucesso);
    }

    // =================================================
    // GETTERS
    // =================================================

    public int getId() {
        return id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Heroi getHeroiDesignado() {
        return heroiDesignado;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getAtributoChave() {
        return atributoChave;
    }

    public String getAtributoPrincipal() {
        return atributoChave;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // =================================================
    // IDENTIDADE
    // =================================================

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Chamado chamado = (Chamado) obj;
        return id == chamado.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
