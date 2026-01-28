package ui.components;

import jogo.Heroi;
import ui.telas.TelaJogo;
import ui.util.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class HeroiCardPanel extends JPanel {

    private final Heroi heroi;
    private final TelaJogo telaJogo;

    private boolean selecionado = false;

    private JLabel imagemLabel;
    private JLabel lblAtributo;

    private ImageIcon imagemNormal;
    private ImageIcon imagemCinza;

    private Timer refreshTimer;

    public HeroiCardPanel(Heroi heroi, TelaJogo telaJogo) {
        this.heroi = heroi;
        this.telaJogo = telaJogo;

        setPreferredSize(new Dimension(150, 220));
        setBackground(new Color(30, 30, 40));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        // ================= ATRIBUTO CHAVE =================
        lblAtributo = new JLabel("", SwingConstants.CENTER);
        lblAtributo.setForeground(Color.CYAN);
        lblAtributo.setFont(new Font("Arial", Font.BOLD, 12));
        lblAtributo.setVisible(false);

        // ================= IMAGEM =================
        imagemLabel = new JLabel();
        imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);

        carregarImagens();

        // ================= NOME =================
        JLabel nome = new JLabel(heroi.getNome(), SwingConstants.CENTER);
        nome.setForeground(Color.WHITE);
        nome.setFont(new Font("Arial", Font.BOLD, 13));

        add(lblAtributo, BorderLayout.NORTH);
        add(imagemLabel, BorderLayout.CENTER);
        add(nome, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (!heroi.isDisponivel()) return;

                SoundPlayer.play("/sounds/click.wav");
                telaJogo.setHeroiSelecionado(heroi);
            }
        });

        iniciarRefreshVisual();
        atualizarVisual();
    }

    // ================= IMAGENS =================

    private void carregarImagens() {
        ImageIcon icon = new ImageIcon(
                getClass().getResource("/herois/" + heroi.getImagem())
        );

        Image img = icon.getImage().getScaledInstance(
                150, 170, Image.SCALE_SMOOTH
        );

        imagemNormal = new ImageIcon(img);
        imagemCinza = new ImageIcon(criarImagemCinza(img));
    }

    private Image criarImagemCinza(Image original) {
        BufferedImage buffered = new BufferedImage(
                original.getWidth(null),
                original.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g2d = buffered.createGraphics();
        g2d.drawImage(original, 0, 0, null);
        g2d.dispose();

        for (int y = 0; y < buffered.getHeight(); y++) {
            for (int x = 0; x < buffered.getWidth(); x++) {
                int rgba = buffered.getRGB(x, y);
                Color c = new Color(rgba, true);
                int cinza = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                buffered.setRGB(
                        x, y,
                        new Color(cinza, cinza, cinza, c.getAlpha()).getRGB()
                );
            }
        }

        return buffered;
    }

    // ================= VISUAL =================

    public Heroi getHeroi() {
        return heroi;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
        atualizarVisual();
    }

    public void atualizarVisual() {

        if (!heroi.isDisponivel()) {
            setBackground(new Color(60, 60, 60));
            setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            imagemLabel.setIcon(imagemCinza);
            lblAtributo.setVisible(false);

        } else {
            setBackground(new Color(30, 30, 40));
            imagemLabel.setIcon(imagemNormal);

            if (selecionado) {
                setBorder(BorderFactory.createLineBorder(Color.CYAN, 3));

                lblAtributo.setText("⭐ " + heroi.getEspecialidade().toUpperCase());
                lblAtributo.setVisible(true);

            } else {
                setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
                lblAtributo.setVisible(false);
            }
        }

        revalidate();
        repaint();
    }

    private void iniciarRefreshVisual() {
        refreshTimer = new Timer(500, e -> atualizarVisual());
        refreshTimer.start();
    }
}
