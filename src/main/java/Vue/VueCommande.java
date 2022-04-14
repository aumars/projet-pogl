package Vue;

import Modele.*;
import java.awt.*;
import javax.swing.*;

public class VueCommande extends JPanel {
    private Modele modele;

    public JButton btn_assecher_top = new VueBouton("Séche case du haut", "◉");
    public JButton btn_assecher_bottom = new VueBouton("Séche case du bas", "◉");
    public JButton btn_assecher_left = new VueBouton("Séche case de gauche", "◉");
    public JButton btn_assecher_right = new VueBouton("Séche case de droite", "◉");
    public JButton btn_assecher_center = new VueBouton("Séche case du centre", "◉");

    public JButton btn_clef = new VueBouton("recherche une clef [SPACE]", "Clef");
    public JButton btn_prendre = new VueBouton("récupére un artefact [F]", "Artefact");
    public JButton btn_next = new VueBouton("fin du tour [ENTER]", "Fin");

    public VueCommande(Modele m) {
        this.modele = m;

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        // this.setBackground(Color.GRAY);

        // Affiche le panel pour assecher une zone.
        JPanel panel_assecher = new JPanel();
        panel_assecher.setLayout(new GridBagLayout());
        panel_assecher.setPreferredSize(new Dimension(Constants.BOX_SIZE * this.modele.getGrille().getWidth() * 3/7, 100));
        panel_assecher.add(this.btn_assecher_top, Utils.positionGrid(1, 0));
        panel_assecher.add(this.btn_assecher_bottom, Utils.positionGrid(1, 2));
        panel_assecher.add(this.btn_assecher_center, Utils.positionGrid(1, 1));
        panel_assecher.add(this.btn_assecher_left, Utils.positionGrid(0, 1));
        panel_assecher.add(this.btn_assecher_right, Utils.positionGrid(2, 1));
        this.add(panel_assecher);

        // Affiche le panel des actions.
        JPanel panel_actions = new JPanel();
        panel_actions.setLayout(new GridBagLayout());
        panel_actions.add(this.btn_clef, Utils.positionGrid(0, 0, 5));
        panel_actions.add(this.btn_prendre, Utils.positionGrid(1, 0, 5));
        panel_actions.add(this.btn_next, Utils.positionGrid(2, 0, 5));
        this.add(panel_actions);

        disableUnusedButton();
    }

    public void disableUnusedButton() {
        boolean peut_jouer = this.modele.getJoueurActuel().estSonTour();

        this.btn_assecher_top.setEnabled(peut_jouer && checkCaseInonder(Direction.HAUT));
        this.btn_assecher_bottom.setEnabled(peut_jouer && checkCaseInonder(Direction.BAS));
        this.btn_assecher_center.setEnabled(peut_jouer && checkCaseInonder(Direction.NEUTRE));
        this.btn_assecher_left.setEnabled(peut_jouer && checkCaseInonder(Direction.GAUCHE));
        this.btn_assecher_right.setEnabled(peut_jouer && checkCaseInonder(Direction.DROITE));

        this.btn_clef.setEnabled(peut_jouer);
        this.btn_prendre.setEnabled(peut_jouer && checkCaseArtefact());
    }

    public boolean checkCaseArtefact() {
        return this.modele.getGrille().getCase(this.modele.getJoueurActuel().getCoord()).aObjet(Artefact.class);
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

    // public int computeSize(int n_case) {
    //     // int w = this.modele.getGrille().getWidth();
    //     // return (int) w * Constants.BOX_SIZE * (w-1) * Constants.GAP_CASE;
    // }
}