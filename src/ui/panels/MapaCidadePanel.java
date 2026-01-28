package ui.panels;

import jogo.Chamado;
import jogo.Heroi;
import ui.telas.TelaJogo;
import ui.util.SoundPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MapaCidadePanel extends JPanel {

    private final List<Chamado> chamados = new ArrayList<>();
    private final List<HeroiEmMovimento> heroisEmMovimento = new ArrayList<>();

    private final TelaJogo telaJogo;
    private Image mapa;

    private int mapaOriginalLargura;
    private int mapaOriginalAltura;

    private Timer timerAnimacao;

    public MapaCidadePanel(TelaJogo telaJogo) {
        this.telaJogo = telaJogo;

        setBackground(Color.BLACK);
        setFocusable(true);

        carregarMapa();
        configurarClique();
        iniciarAnimacao();
    }

    private void iniciarAnimacao() {
        timerAnimacao = new Timer(30, e -> atualizarAnimacao());
        timerAnimacao.start();
    }

    private void atualizarAnimacao() {
        heroisEmMovimento.removeIf(HeroiEmMovimento::atualizar);
        repaint();
    }

    private void carregarMapa() {
        try (InputStream is = getClass().getResourceAsStream("/mapas/mapa_cidade.png")) {

            if (is == null) {
                throw new RuntimeException("Mapa NÃO encontrado em /mapas/mapa_cidade.png");
            }

            mapa = ImageIO.read(is);
            mapaOriginalLargura = mapa.getWidth(null);
            mapaOriginalAltura = mapa.getHeight(null);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar mapa", e);
        }
    }

    private void configurarClique() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (chamados.isEmpty()) return;

                double escalaX = (double) getWidth() / mapaOriginalLargura;
                double escalaY = (double) getHeight() / mapaOriginalAltura;

                int cliqueX = (int) (e.getX() / escalaX);
                int cliqueY = (int) (e.getY() / escalaY);

                int raioClique = 18;

                for (Chamado chamado : chamados) {
                    int dx = cliqueX - chamado.getX();
                    int dy = cliqueY - chamado.getY();

                    if ((dx * dx + dy * dy) <= raioClique * raioClique) {

                        SoundPlayer.play("/sounds/click.wav");
                        telaJogo.abrirJanelaChamado(chamado);
                        return;
                    }
                }
            }
        });
    }

    // ================= CHAMADOS =================

    public void limparChamados() {
        chamados.clear();
        repaint();
    }

    public void adicionarChamado(Chamado chamado) {
        chamados.add(chamado);
        repaint();
    }

    public void removerChamado(Chamado chamado) {
        chamados.remove(chamado);
        repaint();
    }

    // ================= MOVIMENTO =================

    public void enviarHeroiParaChamado(Heroi heroi, Chamado chamado) {

        double escalaX = (double) getWidth() / mapaOriginalLargura;
        double escalaY = (double) getHeight() / mapaOriginalAltura;

        double destinoX = chamado.getX() * escalaX;
        double destinoY = chamado.getY() * escalaY;

        double inicioX = getWidth() / 2.0;
        double inicioY = getHeight() / 2.0;

        heroisEmMovimento.add(
                new HeroiEmMovimento(heroi, inicioX, inicioY, destinoX, destinoY)
        );
    }

    // ================= DESENHO =================

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (mapa != null) {
            g.drawImage(mapa, 0, 0, getWidth(), getHeight(), this);
        }

        double escalaX = (double) getWidth() / mapaOriginalLargura;
        double escalaY = (double) getHeight() / mapaOriginalAltura;

        for (Chamado chamado : chamados) {
            int x = (int) (chamado.getX() * escalaX);
            int y = (int) (chamado.getY() * escalaY);

            // 🔴 ou 🟡 conforme dificuldade
            if (chamado.isDificil()) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.YELLOW);
            }

            g.fillOval(x - 10, y - 10, 20, 20);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("!", x - 4, y + 6);
        }

        Graphics2D g2d = (Graphics2D) g;
        for (HeroiEmMovimento h : heroisEmMovimento) {
            h.desenhar(g2d);
        }
    }
}
