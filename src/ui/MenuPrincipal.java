package ui;

import ui.util.MusicPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Call of Heroes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setResizable(true);

        // Música do menu
        MusicPlayer.playLoop("/sounds/menu.wav", -10.0f);

        JPanel painelFundo = new JPanel() {

            Image bg = new ImageIcon(
                    MenuPrincipal.class.getResource("/menu/menu_fundo.png")
            ).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };

        painelFundo.setLayout(null);

        painelFundo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int w = painelFundo.getWidth();
                int h = painelFundo.getHeight();

                // HITBOXES AJUSTADAS
                Rectangle areaJogar = new Rectangle(
                        (int) (w * 0.07),
                        (int) (h * 0.80),
                        (int) (w * 0.26),
                        (int) (h * 0.12)
                );

                Rectangle areaConfig = new Rectangle(
                        (int) (w * 0.37),
                        (int) (h * 0.80),
                        (int) (w * 0.30),
                        (int) (h * 0.12)
                );

                Rectangle areaSair = new Rectangle(
                        (int) (w * 0.72),
                        (int) (h * 0.80),
                        (int) (w * 0.24),
                        (int) (h * 0.12)
                );

                Point p = e.getPoint();

                if (areaJogar.contains(p)) {
                    MusicPlayer.playOnce("/sounds/click.wav");
                    MusicPlayer.stop();
                    dispose();
                    new IntroSwing();
                }

                if (areaConfig.contains(p)) {
                    MusicPlayer.playOnce("/sounds/click.wav");

                    // 🔧 Abre menu de configurações
                    setVisible(false);
                    new MenuConfiguracoes(MenuPrincipal.this);
                }

                if (areaSair.contains(p)) {
                    MusicPlayer.playOnce("/sounds/click.wav");
                    MusicPlayer.stop();
                    System.exit(0);
                }
            }
        });

        add(painelFundo);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuPrincipal::new);
    }
}
