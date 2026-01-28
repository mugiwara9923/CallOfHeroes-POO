package principal;

import javax.swing.SwingUtilities;
import ui.MenuPrincipal;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new MenuPrincipal();
        });

    }
}
