package ui.panels;

import jogo.Heroi;

import javax.swing.*;
import java.awt.*;

public class HeroiEmMovimento {

    private final Heroi heroi;
    private double x;
    private double y;
    private final double destinoX;
    private final double destinoY;

    private final double velocidade = 3.0;
    private final Image imagem;

    public HeroiEmMovimento(Heroi heroi, double x, double y, double destinoX, double destinoY) {
        this.heroi = heroi;
        this.x = x;
        this.y = y;
        this.destinoX = destinoX;
        this.destinoY = destinoY;

        ImageIcon icon = new ImageIcon(
                getClass().getResource("/herois/" + heroi.getImagem())
        );

        this.imagem = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
    }

    public boolean atualizar() {
        double dx = destinoX - x;
        double dy = destinoY - y;
        double distancia = Math.sqrt(dx * dx + dy * dy);

        if (distancia < velocidade) {
            x = destinoX;
            y = destinoY;
            return true; // chegou
        }

        x += (dx / distancia) * velocidade;
        y += (dy / distancia) * velocidade;

        return false;
    }

    public void desenhar(Graphics2D g2d) {
        g2d.drawImage(imagem, (int) x - 16, (int) y - 16, null);
    }
}
