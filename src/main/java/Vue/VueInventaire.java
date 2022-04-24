package Vue;

import Modele.*;
import java.awt.*;
import javax.swing.*;

/**
 * L'interface graphique d'un inventaire d'un Joueur
 */
public class VueInventaire extends JPanel {
    /**
     * Le modèle que l'interface graphique représente
     */
    private final Modele MODELE;

    /**
     * JLabel pour le nom du Joueur
     */
    private final JLabel LABEL_NOM_JOUEUR = new JLabel();

    /**
     * JPanel pour tous les objets que le Jouer possède
     */
    private final JPanel PANEL_OBJETS = new JPanel();

    /**
     * Nom du Joueur
     */
    private final String NOM;

    /**
     * Affiche un inventaire.
     *
     * @param m  Le modele.
     * @param joueur Le joueur qui possède cet inventaire.
     */
    public VueInventaire(Modele m, Joueur joueur) {
        this.MODELE = m;
        this.NOM = joueur.toString();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.LABEL_NOM_JOUEUR.setIcon(Utils.tailleImg(ConstsIcon.getImgAvatar(joueur.ID), 24, 24));
        this.LABEL_NOM_JOUEUR.setText(this.NOM);
        this.LABEL_NOM_JOUEUR.setPreferredSize(new Dimension(100, 50));
        this.LABEL_NOM_JOUEUR.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 0));
        this.LABEL_NOM_JOUEUR.setFont(new Font(ConstsValue.FONT_FAMILY, Font.BOLD, 20));
        this.LABEL_NOM_JOUEUR.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(LABEL_NOM_JOUEUR);

        this.PANEL_OBJETS.setLayout(new BoxLayout(this.PANEL_OBJETS, BoxLayout.PAGE_AXIS));
        this.PANEL_OBJETS.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        
        this.remplieInventaireVide();
        this.add(PANEL_OBJETS);
    }

    /**
     * Mets a jour l'affichage des objets dans l'inventaires.
     */
    public void metAJourAffichageObjet() {
        this.PANEL_OBJETS.removeAll();

        for (Objet o : this.MODELE.getJoueurActuel().getInventaire()) {
            this.PANEL_OBJETS.add(new VueObjet(o));
        }

        this.remplieInventaireVide();
        this.updateUI();
    }

    /**
     * Remplie les cases vides de l'inventaire par une image significatif.
     */
    private void remplieInventaireVide() {
        int nb_items = this.PANEL_OBJETS.getComponentCount();
        int max_items = 9;
        for (int i = 0; i < max_items - nb_items; i++)
            this.PANEL_OBJETS.add(new VueObjet(null));
    }

    /**
     * Mets a jour l'etat du joueur.
     *
     * @param j Le joueur.
     */
    public void metAJourEtatJoueur(Joueur j) {
        if (!j.estVivant()) {
            this.LABEL_NOM_JOUEUR.setText("<html><strike>" + this.NOM + "</strike></html>");
            this.LABEL_NOM_JOUEUR.setForeground(Color.RED);
        }
    }
}
