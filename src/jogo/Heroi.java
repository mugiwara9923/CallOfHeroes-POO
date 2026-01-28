package jogo;

import javax.swing.Timer;

public class Heroi {

    private String nome;
    private String imagem;
    private int nivel;
    private int xp;

    private int forca;
    private int inteligencia;
    private int velocidade;
    private int vigor;
    private int carisma;
    private int furtividade;

    private String especialidade;

    private boolean inMission;

    private long cooldownAte;

    private MissaoListener missaoListener;

    public Heroi(String nome, String imagem, String especialidade,
                 int forca, int inteligencia, int velocidade,
                 int vigor, int carisma, int furtividade) {

        this.nome = nome;
        this.imagem = imagem;
        this.especialidade = especialidade.toLowerCase();

        this.forca = forca;
        this.inteligencia = inteligencia;
        this.velocidade = velocidade;
        this.vigor = vigor;
        this.carisma = carisma;
        this.furtividade = furtividade;

        this.nivel = 1;
        this.xp = 0;

        this.inMission = false;
        this.cooldownAte = 0;
    }

    // ================= LISTENER =================

    public void setMissaoListener(MissaoListener listener) {
        this.missaoListener = listener;
    }

    // ================= GETTERS =================

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    // 🔒 DISPONIBILIDADE BASEADA EM TEMPO
    public boolean isDisponivel() {
        return !inMission && System.currentTimeMillis() >= cooldownAte;
    }

    // ================= MISSÃO =================

    public void enviarParaMissao() {
        this.inMission = true;
        System.out.println("🚀 " + nome + " enviado para missão.");

        Timer timer = new Timer(10000, e -> finalizarMissao());
        timer.setRepeats(false);
        timer.start();
    }
    public void finalizarMissao() {
        this.inMission = false;

        this.cooldownAte = System.currentTimeMillis() + 10000;

        System.out.println("✅ " + nome + " descansou e está disponível novamente.");

        if (missaoListener != null) {
            missaoListener.onMissaoFinalizada(this);
        }
    }

    // ================= ATRIBUTOS =================

    public int getAtributo(String nome) {
        return switch (nome.toLowerCase()) {
            case "forca" -> forca;
            case "inteligencia" -> inteligencia;
            case "velocidade" -> velocidade;
            case "vigor" -> vigor;
            case "carisma" -> carisma;
            case "furtividade" -> furtividade;
            default -> 0;
        };
    }

    public long getTempoRestanteCooldown() {
        long restante = cooldownAte - System.currentTimeMillis();
        return Math.max(restante, 0);
    }
}
