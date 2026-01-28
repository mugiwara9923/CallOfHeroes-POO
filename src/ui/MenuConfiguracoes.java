package ui;

import ui.util.MusicPlayer;

import javax.swing.*;
import java.awt.*;

public class MenuConfiguracoes extends JFrame {

    private JCheckBox chkMusica;
    private JSlider sliderVolume;

    public MenuConfiguracoes(JFrame menuAnterior) {

        setTitle("Configurações");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painel = new JPanel();
        painel.setBackground(new Color(20, 20, 25));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("CONFIGURAÇÕES");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(titulo);
        painel.add(Box.createVerticalStrut(20));

        //  Música
        chkMusica = new JCheckBox("Música ligada");
        chkMusica.setSelected(true);
        chkMusica.setForeground(Color.WHITE);
        chkMusica.setBackground(new Color(20, 20, 25));
        chkMusica.setAlignmentX(Component.CENTER_ALIGNMENT);

        chkMusica.addActionListener(e -> {
            if (chkMusica.isSelected()) {
                MusicPlayer.playLoop("/sounds/menu.wav", sliderVolume.getValue());
            } else {
                MusicPlayer.stop();
            }
        });

        painel.add(chkMusica);
        painel.add(Box.createVerticalStrut(15));

        // Volume
        JLabel lblVolume = new JLabel("Volume");
        lblVolume.setForeground(Color.WHITE);
        lblVolume.setAlignmentX(Component.CENTER_ALIGNMENT);

        sliderVolume = new JSlider(-30, 0, -10);
        sliderVolume.setAlignmentX(Component.CENTER_ALIGNMENT);
        sliderVolume.addChangeListener(e -> {
            if (chkMusica.isSelected()) {
                MusicPlayer.playLoop("/sounds/menu.wav", sliderVolume.getValue());
            }
        });

        painel.add(lblVolume);
        painel.add(sliderVolume);
        painel.add(Box.createVerticalStrut(20));

        // Voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnVoltar.addActionListener(e -> {
            dispose();
            menuAnterior.setVisible(true);
        });

        painel.add(btnVoltar);

        add(painel, BorderLayout.CENTER);
        setVisible(true);
    }
}
