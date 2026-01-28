package ui;

import javax.swing.*;
import java.awt.*;

public class TelaFinalSwing extends JFrame {

    private int sucessos;
    private int falhas;

    // =====================================================
    // CONSTRUTOR USADO PELO JOGO
    // =====================================================
    public TelaFinalSwing(int sucessos, int falhas) {
        this.sucessos = sucessos;
        this.falhas = falhas;

        criarTela(
                "<html><div style='text-align:center;'>"
                        + "<div style='font-size:48px; margin-bottom:20px;'>FIM DE JOGO</div>"
                        + "<div style='font-size:22px;'>"
                        + "✔ Sucessos: " + sucessos + "<br>"
                        + "✖ Falhas: " + falhas + "<br><br>"
                        + "Relatório final da operação concluído."
                        + "</div>"
                        + "</div></html>"
        );
    }

    // =====================================================
    // MÉTODO CENTRAL
    // =====================================================
    private void criarTela(String textoHtml) {

        setTitle("Call of Heroes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        JLabel lblTexto = new JLabel(textoHtml, SwingConstants.CENTER);
        lblTexto.setForeground(Color.WHITE);

        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.addActionListener(e -> {
            dispose();
            new EpilogoSwing(sucessos, falhas);
        });

        JPanel rodape = new JPanel();
        rodape.setOpaque(false);
        rodape.add(btnContinuar);

        painel.add(lblTexto, BorderLayout.CENTER);
        painel.add(rodape, BorderLayout.SOUTH);

        add(painel);
        setVisible(true);
    }
}
