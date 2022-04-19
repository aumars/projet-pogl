package Vue;

import Modele.Modele;
import javax.swing.*;
import java.awt.*;

public class VueInfoHaut extends JPanel {
    private Modele modele;

    private JLabel label_tour;
    public VueBouton btn_aide = new VueBouton("Affiche l'écran d'aide", "Besoin d'aide ?");

    /**
     * Affichage du menu du haut où sont afficher le tour actuel, le joueur qui joue
     * et le bouton d'aide.
     * 
     * @param m Le modele.
     */
    public VueInfoHaut(Modele m) {
        this.modele = m;

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        label_tour = new JLabel(this.getTextTitre(), SwingConstants.CENTER);
        label_tour.setFont(new Font(ConstsValue.FONT_FAMILY, Font.BOLD, 25));
        this.add(this.label_tour, BorderLayout.CENTER);

        this.add(this.btn_aide, BorderLayout.LINE_END);
    }

    /**
     * Mets a jour l'affichage après qu'une action soit réalisée par le joueur.
     */
    public void metAJourApresAction() {
        super.repaint();
        this.btn_aide.setEnabled(!this.modele.getFinJeu());
        this.label_tour.setText(this.getTextTitre());
    }

    /**
     * Recupere le titre du texte.
     * 
     * @return le texte du titre.
     */
    private String getTextTitre() {
        return String.format("Tour %d | %s", this.modele.getTour(),
                ConstsValue.getNomJoueur(this.modele.getJoueurActuel().id));
    }
}
