package Vue;

import javax.swing.*;
import java.awt.*;

public class VueFinJeu extends JPanel {
    public VueBouton btn_rejouer = new VueBouton("Lance une nouvelle partie", "Rejouer");

    /**
     * Affiche le menu de fin de jeu.
     * 
     * @param est_partie_perdante True si la partie est perdu. False sinon.
     */
    public VueFinJeu(boolean est_partie_perdante) {
        this.setLayout(new BorderLayout());

        JPanel panel_text = new JPanel();
        panel_text.setLayout(new BorderLayout());

        JLabel lbl_titre = new JLabel("", SwingConstants.CENTER);
        JLabel lbl_text = new JLabel("", SwingConstants.CENTER);

        if (est_partie_perdante) {
            lbl_titre.setText("GAME OVER...");
            lbl_titre.setFont(new Font(ConstsValue.FONT_FAMILY, Font.BOLD, 20));

            lbl_text.setText("La défaite n'est pas de perdre mais, d'être mauvais perdant.");
            lbl_text.setFont(new Font(ConstsValue.FONT_FAMILY, Font.ITALIC, 12));
        }

        else {
            lbl_titre.setText("BRAVO ");
            lbl_titre.setFont(new Font(ConstsValue.FONT_FAMILY, Font.BOLD, 20));

            lbl_text.setText("Deux choses comptent : gagner et s'amuser. Gagner sans s'amuser n'a aucun intérêt.");
            lbl_text.setFont(new Font(ConstsValue.FONT_FAMILY, Font.ITALIC, 12));
        }

        panel_text.add(lbl_titre, BorderLayout.PAGE_START);
        panel_text.add(lbl_text, BorderLayout.CENTER);
        panel_text.add(this.btn_rejouer, BorderLayout.LINE_END);

        this.add(panel_text, BorderLayout.CENTER);
    }
}
