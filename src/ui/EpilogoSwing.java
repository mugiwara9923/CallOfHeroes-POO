package ui;

import historia.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class EpilogoSwing extends JFrame {

    private List<Cena> cenas;
    private int indiceCena = 0;
    private int indiceTexto = 0;

    private JLabel lblImagem;
    private JLabel lblLegenda;

    private static final int FRASES_POR_BLOCO = 3;

    // =====================================================
    public EpilogoSwing(int sucessos, int falhas) {

        GerenciadorCenasEpilogo gc =
                new GerenciadorCenasEpilogo(sucessos, falhas);
        cenas = gc.getCenas();

        setTitle("Call of Heroes - Epílogo");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= IMAGEM =================
        lblImagem = new JLabel("", SwingConstants.CENTER);
        add(lblImagem, BorderLayout.CENTER);

        // ================= LEGENDA =================
        lblLegenda = new JLabel();
        lblLegenda.setForeground(Color.WHITE);
        lblLegenda.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel legendaWrapper = new JPanel(new BorderLayout());
        legendaWrapper.setOpaque(false);
        legendaWrapper.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        legendaWrapper.add(lblLegenda, BorderLayout.CENTER);

        JPanel rodape = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 200));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        rodape.setPreferredSize(new Dimension(0, 110));
        rodape.setOpaque(false);
        rodape.add(legendaWrapper, BorderLayout.CENTER);

        JButton btnAvancar = new JButton("Avançar");
        btnAvancar.addActionListener(e -> avancar());
        rodape.add(btnAvancar, BorderLayout.EAST);

        add(rodape, BorderLayout.SOUTH);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                carregarImagem();
            }
        });

        carregarImagem();
        atualizarTexto();

        setVisible(true);
    }

    // =====================================================
    private void avancar() {

        indiceTexto += FRASES_POR_BLOCO;

        if (indiceTexto >= cenas.get(indiceCena).getTextos().size()) {
            indiceTexto = 0;
            indiceCena++;

            if (indiceCena >= cenas.size()) {
                dispose();
                new MenuPrincipal();
                return;
            }

            carregarImagem();
        }

        atualizarTexto();
    }

    // =====================================================
    private void atualizarTexto() {

        Cena cena = cenas.get(indiceCena);
        List<String> textos = cena.getTextos();

        StringBuilder bloco = new StringBuilder();
        int fim = Math.min(indiceTexto + FRASES_POR_BLOCO, textos.size());

        for (int i = indiceTexto; i < fim; i++) {
            bloco.append(textos.get(i)).append("<br>");
        }

        lblLegenda.setText(
                "<html><div style='text-align:center; font-size:18px;'>"
                        + bloco +
                        "</div></html>"
        );
    }

    // =====================================================
    private void carregarImagem() {

        try {
            ImageIcon icon =
                    new ImageIcon(getClass().getResource(
                            cenas.get(indiceCena).getImagemPath()
                    ));

            Image img = icon.getImage();
            int w = lblImagem.getWidth();
            int h = lblImagem.getHeight();

            if (w > 0 && h > 0) {
                lblImagem.setIcon(new ImageIcon(
                        img.getScaledInstance(w, h, Image.SCALE_SMOOTH)
                ));
            }

        } catch (Exception e) {
            lblImagem.setIcon(null);
        }
    }
}
