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

    public VueInventaire(Modele m, String nom) {
        this.modele = m;
        this.nom = nom;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.label_nom_joueur.setText(nom);
        this.label_nom_joueur.setPreferredSize(new Dimension(100, 100));
        this.label_nom_joueur.setBorder(BorderFactory.createEmptyBorder(20, 25, 5, 0));
        this.label_nom_joueur.setFont(new Font(ConstsValue.FONT_FAMILY, Font.BOLD, 20));
        this.label_nom_joueur.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(label_nom_joueur);

        this.panel_objets.setLayout(new BoxLayout(this.panel_objets, BoxLayout.PAGE_AXIS));
        this.panel_objets.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        
        this.remplieInventaireVide();
        this.add(panel_objets);
    }

    public void metAJourAffichageObjet() {
        this.panel_objets.removeAll();

        for (Objet o : this.modele.getJoueurActuel().getInventaire()) {
            this.panel_objets.add(new VueObjet(o));
        }

        this.remplieInventaireVide();
        this.updateUI();
    }

    private void remplieInventaireVide() {
        int nb_items = this.panel_objets.getComponentCount();
        for (int i = 0; i < this.max_items-nb_items; i++)
            this.panel_objets.add(new VueObjet(null));
    }

    public void metAJourEtatJoueur(Joueur j) {
        if (!j.estVivant()) {
            this.label_nom_joueur.setText("<html><strike>" + this.nom + "</strike></html>");
            this.label_nom_joueur.setForeground(Color.RED);
        }
    }
}
