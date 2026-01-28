package ui.telas;

import jogo.Chamado;
import jogo.GeradorChamados;
import jogo.Heroi;
import jogo.MissaoListener;
import ui.components.HeroiCardPanel;
import ui.panels.JanelaChamado;
import ui.panels.MapaCidadePanel;
import ui.util.MusicPlayer;
import ui.TelaFinalSwing;

import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class TelaJogo extends JFrame implements MissaoListener {

    private Heroi heroiSelecionado;

    // ================= TURNOS =================
    private int turnoAtual = 1;
    private static final int MAX_TURNOS = 4;
    private int chamadosResolvidosNoTurno = 0;

    // ================= RESULTADOS =================
    private int totalSucessos = 0;
    private int totalFalhas = 0;

    // ================= CHAMADO ATIVO =================
    private Chamado chamadoAtual;
    private final GeradorChamados geradorChamados = new GeradorChamados();

    // 🎲 RANDOM
    private final Random random = new Random();

    //  TIMER CHAMADO CRÍTICO
    private Timer timerChamadoCritico;
    private int tempoRestante;

    // ================= UI =================
    private JPanel painelHerois;
    private MapaCidadePanel mapaCidadePanel;
    private JanelaChamado janelaChamado;
    private JDesktopPane desktop;
    private JLabel lblTurno;
    private JLabel lblAviso;

    // =========================================================
    // CONSTRUTOR
    // =========================================================
    public TelaJogo() {
        setTitle("Call of Heroes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1400, 800);
        setLocationRelativeTo(null);

        desktop = new JDesktopPane();
        setContentPane(desktop);

        JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelTopo.setBackground(new Color(15, 15, 20));
        painelTopo.setBounds(0, 0, 1400, 40);

        lblTurno = new JLabel();
        lblTurno.setForeground(Color.WHITE);
        lblTurno.setFont(new Font("Arial", Font.BOLD, 18));

        lblAviso = new JLabel("");
        lblAviso.setForeground(Color.RED);
        lblAviso.setFont(new Font("Arial", Font.BOLD, 16));

        painelTopo.add(lblTurno);
        painelTopo.add(lblAviso);
        desktop.add(painelTopo, JLayeredPane.DEFAULT_LAYER);

        mapaCidadePanel = new MapaCidadePanel(this);
        mapaCidadePanel.setBounds(0, 40, 1400, 530);
        desktop.add(mapaCidadePanel, JLayeredPane.DEFAULT_LAYER);

        painelHerois = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelHerois.setBounds(0, 570, 1400, 230);
        painelHerois.setBackground(new Color(18, 18, 22));
        desktop.add(painelHerois, JLayeredPane.DEFAULT_LAYER);

        carregarHerois();
        iniciarTurno();

        setVisible(true);
    }

    // =========================================================
    // HERÓIS
    // =========================================================
    private void carregarHerois() {
        painelHerois.removeAll();

        java.util.List<Heroi> herois = java.util.List.of(
                new Heroi("Dante Ravelli", "Dante.png", "inteligencia", 7, 6, 5, 4, 3, 2),
                new Heroi("Lysandra Vega", "Lysandra.png", "furtividade", 4, 8, 6, 7, 5, 3),
                new Heroi("Kael Morrow", "Kael.png", "forca", 8, 5, 6, 3, 4, 2),
                new Heroi("Naeli", "Naeli.png", "carisma", 3, 6, 9, 8, 7, 4),
                new Heroi("Ronin Asher", "Ronin.png", "velocidade", 9, 4, 3, 2, 6, 5),
                new Heroi("Cora", "Cora.png", "resistencia", 6, 7, 5, 6, 8, 4)
        );

        for (Heroi h : herois) {
            h.setMissaoListener(this);
            painelHerois.add(new HeroiCardPanel(h, this));
        }

        painelHerois.revalidate();
        painelHerois.repaint();
    }

    // =========================================================
    // TURNOS
    // =========================================================
    private void iniciarTurno() {

        if (turnoAtual > MAX_TURNOS) {
            mostrarResultadoFinal();
            return;
        }

        lblTurno.setText("TURNO " + turnoAtual + " / " + MAX_TURNOS);
        lblAviso.setText("");

        chamadosResolvidosNoTurno = 0;
        mapaCidadePanel.limparChamados();

        gerarProximoChamado();
    }

    private void gerarProximoChamado() {

        if (chamadosResolvidosNoTurno >= turnoAtual) {
            turnoAtual++;
            iniciarTurno();
            return;
        }

        Timer delay = new Timer(800, e -> {
            chamadoAtual = geradorChamados.gerarChamado();
            definirDificuldadeChamado(chamadoAtual);

            mapaCidadePanel.adicionarChamado(chamadoAtual);
            tocarSomAlerta();

            if (chamadoAtual.isDificil()) {
                iniciarContagemChamadoCritico();
            }
        });

        delay.setRepeats(false);
        delay.start();
    }

    // =========================================================
    // 🔴 DIFICULDADE PROGRESSIVA
    // =========================================================
    private void definirDificuldadeChamado(Chamado chamado) {

        int chance;

        switch (turnoAtual) {
            case 1 -> chance = 20;
            case 2 -> chance = 40;
            case 3 -> chance = 60;
            default -> chance = 75;
        }

        boolean dificil = random.nextInt(100) < chance;
        chamado.setDificil(dificil);
    }

    // =========================================================
    // ⏱ CHAMADO CRÍTICO
    // =========================================================
    private void iniciarContagemChamadoCritico() {

        cancelarTimerCritico();

        tempoRestante = 5;
        lblAviso.setText("⚠ CHAMADO CRÍTICO! RESPONDA EM " + tempoRestante + "s!");

        timerChamadoCritico = new Timer(1000, e -> {
            tempoRestante--;

            if (tempoRestante > 0) {
                lblAviso.setText("⚠ CHAMADO CRÍTICO! RESPONDA EM " + tempoRestante + "s!");
            } else {
                cancelarTimerCritico();

                if (chamadoAtual != null) {
                    totalFalhas++;
                    mapaCidadePanel.removerChamado(chamadoAtual);
                    chamadoAtual = null;
                    chamadosResolvidosNoTurno++;
                    gerarProximoChamado();
                }
            }
        });

        timerChamadoCritico.start();
    }

    private void cancelarTimerCritico() {
        lblAviso.setText("");
        if (timerChamadoCritico != null) {
            timerChamadoCritico.stop();
            timerChamadoCritico = null;
        }
    }

    // =========================================================
    // CHAMADOS
    // =========================================================
    public void abrirJanelaChamado(Chamado chamado) {

        if (janelaChamado != null) return;

        cancelarTimerCritico();

        janelaChamado = new JanelaChamado(this, chamado);
        janelaChamado.setBounds(880, 180, 360, 240);
        desktop.add(janelaChamado, JLayeredPane.POPUP_LAYER);
        janelaChamado.toFront();
    }

    public void notificarJanelaFechada() {
        janelaChamado = null;
    }

    // =========================================================
    // CALLBACK
    // =========================================================
    @Override
    public void onMissaoFinalizada(Heroi heroi) {

        cancelarTimerCritico();

        if (janelaChamado != null) {
            janelaChamado.dispose();
            janelaChamado = null;
        }

        finalizarChamadoAtual();
        atualizarSelecao();
    }

    private void finalizarChamadoAtual() {

        if (chamadoAtual == null) return;

        if (chamadoAtual.isSucesso()) totalSucessos++;
        else totalFalhas++;

        mapaCidadePanel.removerChamado(chamadoAtual);
        chamadoAtual = null;
        chamadosResolvidosNoTurno++;

        gerarProximoChamado();
    }

    // =========================================================
    // USADO AO FECHAR JANELA SEM HERÓI
    // =========================================================
    public void cancelarChamado() {

        cancelarTimerCritico();

        if (chamadoAtual != null) {
            totalFalhas++;
            mapaCidadePanel.removerChamado(chamadoAtual);
            chamadoAtual = null;
            chamadosResolvidosNoTurno++;
        }

        gerarProximoChamado();
    }

    // =========================================================
    // SELEÇÃO
    // =========================================================
    public void setHeroiSelecionado(Heroi heroi) {
        this.heroiSelecionado = heroi;
        atualizarSelecao();
    }

    private void atualizarSelecao() {
        for (Component c : painelHerois.getComponents()) {
            if (c instanceof HeroiCardPanel card) {
                card.setSelecionado(card.getHeroi() == heroiSelecionado);
            }
        }
    }

    // =========================================================
    // SOM
    // =========================================================
    private void tocarSomAlerta() {
        try {
            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(
                            getClass().getResource("/sounds/alerta.wav")
                    );

            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

        } catch (Exception e) {
            System.out.println("Erro ao tocar som de alerta");
        }
    }

    // =========================================================
    // RESULTADO FINAL
    // =========================================================
    private void mostrarResultadoFinal() {

        MusicPlayer.stop();
        dispose();

        SwingUtilities.invokeLater(() ->
                new TelaFinalSwing(totalSucessos, totalFalhas)
        );
    }

    // =========================================================
    // AUX
    // =========================================================
    public Heroi getHeroiSelecionado() {
        return heroiSelecionado;
    }

    public MapaCidadePanel getMapaCidadePanel() {
        return mapaCidadePanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaJogo::new);
    }
}
