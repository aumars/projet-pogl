package Vue;

import Modele.*;
import java.awt.*;
import javax.swing.*;

public class VueCommande extends JPanel{
    private Modele modele;

    public JButton btn_move_top = new VueBouton("⮝");
    public JButton btn_move_bottom = new VueBouton("⮟");
    public JButton btn_move_left = new VueBouton("⮜");
    public JButton btn_move_right = new VueBouton("⮞");

    public JButton btn_assecher_top = new VueBouton("◉");
    public JButton btn_assecher_bottom = new VueBouton("◉");
    public JButton btn_assecher_left = new VueBouton("◉");
    public JButton btn_assecher_right = new VueBouton("◉");
    public JButton btn_assecher_center = new VueBouton("◉");

    public JButton btn_clef = new VueBouton("Chercher Clef");
    public JButton btn_next = new VueBouton("Tour Suivant");

    public VueCommande(Modele m) {
        this.modele = m;

        this.setLayout(new FlowLayout());

        // Affiche le panel pour deplacer le joueurs.
        JPanel panel_deplacement = new JPanel();
        panel_deplacement.setLayout(new GridBagLayout());
        panel_deplacement.setPreferredSize(new Dimension(3 * this.modele.getGrille().getWidth() * 60 / 8, 100));
        panel_deplacement.add(this.btn_move_top, Utils.positionGrid(1, 0, 1, 1));
        panel_deplacement.add(this.btn_move_bottom, Utils.positionGrid(1, 2, 1, 1));
        panel_deplacement.add(this.btn_move_left, Utils.positionGrid(0, 1, 1, 1));
        panel_deplacement.add(this.btn_move_right, Utils.positionGrid(2, 1, 1, 1));
        this.add(panel_deplacement);

        // Affiche le panel des actions.
        JPanel panel_actions = new JPanel();
        panel_actions.setLayout(new GridBagLayout());
        panel_actions.add(this.btn_clef, Utils.positionGrid(0, 0, 1, 1));
        panel_actions.add(this.btn_next, Utils.positionGrid(0, 1, 1, 1));
        this.add(panel_actions);

        // Affiche le panel pour assecher une zone.
        JPanel panel_assecher = new JPanel();
        panel_assecher.setLayout(new GridBagLayout());
        panel_assecher.setPreferredSize(new Dimension(3 * this.modele.getGrille().getWidth() * 60 / 8, 100));
        panel_assecher.add(this.btn_assecher_top, Utils.positionGrid(1, 0, 1, 1));
        panel_assecher.add(this.btn_assecher_bottom, Utils.positionGrid(1, 2, 1, 1));
        panel_assecher.add(this.btn_assecher_center, Utils.positionGrid(1, 1, 1, 1));
        panel_assecher.add(this.btn_assecher_left, Utils.positionGrid(0, 1, 1, 1));
        panel_assecher.add(this.btn_assecher_right, Utils.positionGrid(2, 1, 1, 1));
        this.add(panel_assecher);

        disableUnusedButton();
    }

    public void disableUnusedButton() {
        boolean peut_jouer = this.modele.getJoueurActuel().estSonTour();
        this.btn_move_top.setEnabled(peut_jouer && checkCaseTraversable(Direction.HAUT));
        this.btn_move_bottom.setEnabled(peut_jouer && checkCaseTraversable(Direction.BAS));
        this.btn_move_left.setEnabled(peut_jouer && checkCaseTraversable(Direction.GAUCHE));
        this.btn_move_right.setEnabled(peut_jouer && checkCaseTraversable(Direction.DROITE));

        this.btn_assecher_top.setEnabled(peut_jouer && checkCaseInonder(Direction.HAUT));
        this.btn_assecher_bottom.setEnabled(peut_jouer && checkCaseInonder(Direction.BAS));
        this.btn_assecher_center.setEnabled(peut_jouer && checkCaseInonder(Direction.NEUTRE));
        this.btn_assecher_left.setEnabled(peut_jouer && checkCaseInonder(Direction.GAUCHE));
        this.btn_assecher_right.setEnabled(peut_jouer && checkCaseInonder(Direction.DROITE));

        this.btn_clef.setEnabled(peut_jouer);
    }

    public boolean checkCaseTraversable(Direction dir) {
        return adjacentJoueur(dir).estTraversable();
    }

    public boolean checkCaseInonder(Direction dir) {
        return adjacentJoueur(dir).getEtat() == Inondation.INONDEE;
    }

    public Case adjacentJoueur(Direction dir) {
        Coord coord_joueurs = this.modele.getJoueurActuel().getCoord();
        return this.modele.getGrille().getCase(coord_joueurs).adjacent(dir);
    }
}