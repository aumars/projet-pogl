package Vue;

import Modele.Modele;
import javax.swing.*;
import java.awt.*;

/**
 * L'interface graphique en haut de la fenêtre
 */
public class VueInfoHaut extends JPanel {
    /**
     * Le modèle que l'interface graphique représente
     */
    private final Modele MODELE;

    /**
     * Un label pour le tour du jeu
     */
    private final JLabel LABEL_TOUR;

    /**
     * Un bouton pour afficher l'interface aide.
     */
    public VueBouton btn_aide = new VueBouton("Affiche l'écran d'aide", "Besoin d'aide ?");

    /**
     * Affichage du menu du haut où est affiché le tour actuel, le joueur qui joue
     * et le bouton d'aide.
     *
     * @param m Le modèle.
     */
    public VueInfoHaut(Modele m) {
        this.MODELE = m;
        
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        LABEL_TOUR = new JLabel(this.getTextTitre(), SwingConstants.CENTER);
        LABEL_TOUR.setFont(new Font(ConstsValue.FONT_FAMILY, Font.BOLD, 25));
        this.add(this.LABEL_TOUR, BorderLayout.CENTER);
        
        this.add(this.btn_aide, BorderLayout.LINE_END);
    }

    /**
     * Mets à jour l'affichage après qu'une action soit réalisée par le joueur.
     */
    public void metAJourApresAction() {
        super.repaint();
        this.btn_aide.setEnabled(!this.MODELE.getFinJeu());
        this.LABEL_TOUR.setText(this.getTextTitre());
    }

    /**
     * Récupère le titre du texte.
    */
    private String getTextTitre(){
        return String.format("Tour %d | %s (%d)", this.MODELE.getTour(), this.MODELE.getJoueurActuel(), this.MODELE.getJoueurActuel().nbActions());
    }
}
