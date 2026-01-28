package ui.panels;

import jogo.Chamado;
import jogo.Heroi;
import ui.telas.TelaJogo;
import ui.util.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class JanelaChamado extends JInternalFrame {

    private final TelaJogo telaJogo;
    private final Chamado chamado;
    private boolean missaoIniciada = false;

    public JanelaChamado(TelaJogo telaJogo, Chamado chamado) {
        super("NOVO CHAMADO", false, true, false, false);

        this.telaJogo = telaJogo;
        this.chamado = chamado;

        setSize(360, 240);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
        setClosable(true);

        JTextArea texto = new JTextArea(
                chamado.getNome() + "\n\n" +
                        chamado.getDescricao() + "\n\n" +
                        "Local: " + chamado.getLocalizacao() + "\n" +
                        "Atributo-chave: " + chamado.getAtributoPrincipal()
        );

        texto.setEditable(false);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setOpaque(false);
        texto.setForeground(Color.WHITE);
        texto.setFont(new Font("Arial", Font.PLAIN, 13));

        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(new Color(20, 20, 30));
        centro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centro.add(texto, BorderLayout.CENTER);

        JButton enviar = new JButton("Enviar Herói");
        enviar.setFocusPainted(false);
        enviar.addActionListener(e -> {
            SoundPlayer.play("/sounds/click.wav");
            enviarHeroi();
        });

        add(centro, BorderLayout.CENTER);
        add(enviar, BorderLayout.SOUTH);

        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {

                // 🔧 CORREÇÃO DO BUG
                telaJogo.notificarJanelaFechada();

                if (!missaoIniciada) {
                    telaJogo.notificarJanelaFechada();
                }
            }
        });

        setVisible(true);
    }

    private void enviarHeroi() {

        Heroi heroi = telaJogo.getHeroiSelecionado();

        if (heroi == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um herói antes!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        missaoIniciada = true;

        chamado.ativar(heroi);
        heroi.enviarParaMissao();

        telaJogo.getMapaCidadePanel().enviarHeroiParaChamado(heroi, chamado);

        telaJogo.setHeroiSelecionado(null);
        dispose();
    }
}
