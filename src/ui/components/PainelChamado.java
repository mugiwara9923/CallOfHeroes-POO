package ui.components;

import jogo.Chamado;

import javax.swing.*;
import java.awt.*;

public class PainelChamado extends JPanel {

    private Chamado chamado;
    private JButton btnEnviar;

    public PainelChamado() {
        setPreferredSize(new Dimension(0, 140));
        setBackground(new Color(20, 20, 25));
        setLayout(new BorderLayout());

        btnEnviar = new JButton("Enviar Herói");
        btnEnviar.setEnabled(false);

        add(btnEnviar, BorderLayout.EAST);
    }

    public void mostrarChamado(Chamado chamado) {
        this.chamado = chamado;
        removeAll();

        JTextArea texto = new JTextArea(
                "🚨 NOVO CHAMADO 🚨\n\n" +
                        chamado.getNome() + "\n" +
                        chamado.getDescricao() + "\n" +
                        "Local: " + chamado.getLocalizacao() + "\n" +
                        "Atributo-chave: " + chamado.getAtributoPrincipal()
        );

        texto.setEditable(false);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setForeground(Color.WHITE);
        texto.setBackground(new Color(20, 20, 25));

        add(texto, BorderLayout.CENTER);
        add(btnEnviar, BorderLayout.EAST);

        revalidate();
        repaint();
    }

    public void setEnviarListener(Runnable action) {
        btnEnviar.addActionListener(e -> action.run());
    }

    public void setBotaoAtivo(boolean ativo) {
        btnEnviar.setEnabled(ativo);
    }

    public Chamado getChamado() {
        return chamado;
    }
}
