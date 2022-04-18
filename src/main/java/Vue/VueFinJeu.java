package Vue;

import Modele.Modele;
import javax.swing.*;
import java.awt.*;

public class VueFinJeu extends JPanel {
    private Modele modele;
    public VueBouton btn_rejouer = new VueBouton("Lance une nouvelle partie", "Rejouer");

    public VueFinJeu(Modele m, boolean est_partie_perdante) {
        this.modele = m;

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(ConstsValue.BOX_SIZE * this.modele.getGrille().getWidth() * 3 / 7, 100));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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
            lbl_titre.setText("BRAVO");
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
