package Vue;

import Modele.*;
import java.awt.*;
import javax.swing.*;

public class VueInventaire extends JPanel {
    private final int max_items = 9;
    private final Modele modele;
    private final JLabel label_nom_joueur = new JLabel();
    private final JPanel panel_objets = new JPanel();
    private final String nom;

    /**
     * Affiche un inventaire.
     *
     * @param m  Le modele.
     * @param joueur Le joueur qui possède cet inventaire.
     */
    public VueInventaire(Modele m, Joueur joueur) {
        this.modele = m;
        this.nom = joueur.toString();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.label_nom_joueur.setIcon(Utils.tailleImg(ConstsIcon.getImgAvatar(joueur.ID), 24, 24));
        this.label_nom_joueur.setText(this.nom);
        this.label_nom_joueur.setPreferredSize(new Dimension(100, 50));
        this.label_nom_joueur.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 0));
        this.label_nom_joueur.setFont(new Font(ConstsValue.FONT_FAMILY, Font.BOLD, 20));
        this.label_nom_joueur.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label_nom_joueur);

        this.panel_objets.setLayout(new BoxLayout(this.panel_objets, BoxLayout.PAGE_AXIS));
        this.panel_objets.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        
        this.remplieInventaireVide();
        this.add(panel_objets);
    }

    /**
     * Mets a jour l'affichage des objets dans l'inventaires.
     */
    public void metAJourAffichageObjet() {
        this.panel_objets.removeAll();

        for (Objet o : this.modele.getJoueurActuel().getInventaire()) {
            this.panel_objets.add(new VueObjet(o));
        }

        this.remplieInventaireVide();
        this.updateUI();
    }

    /**
     * Remplie les cases vides de l'inventaire par une image significatif.
     */
    private void remplieInventaireVide() {
        int nb_items = this.panel_objets.getComponentCount();
        for (int i = 0; i < this.max_items - nb_items; i++)
            this.panel_objets.add(new VueObjet(null));
    }

    /**
     * Mets a jour l'etat du joueur.
     *
     * @param j Le joueur.
     */
    public void metAJourEtatJoueur(Joueur j) {
        if (!j.estVivant()) {
            this.label_nom_joueur.setText("<html><strike>" + this.nom + "</strike></html>");
            this.label_nom_joueur.setForeground(Color.RED);
        }
    }
}
