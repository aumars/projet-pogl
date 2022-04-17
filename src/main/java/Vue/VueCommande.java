package Vue;

import Modele.*;
import java.awt.*;
import javax.swing.*;

public class VueCommande extends JPanel {
    private final ImageIcon SCEAU = Utils.tailleImg(ConstsIcon.SCEAU, 20, 20);

    private Modele modele;

    public JButton btn_secher_haut = new VueBouton("Assèche la case du haut [O]", this.SCEAU);
    public JButton btn_secher_bas = new VueBouton("Assèche la case du bas [L]", this.SCEAU);
    public JButton btn_secher_gauche = new VueBouton("Assèche la case de gauche [K]", this.SCEAU);
    public JButton btn_secher_droite = new VueBouton("Assèche la case de droite [M]", this.SCEAU);
    public JButton btn_secher_centre = new VueBouton("Assèche la case du centre [SPACE]", this.SCEAU);

    public JButton btn_clef = new VueBouton("Recherche une clef autour [A]", "Clef");
    public JButton btn_prendre = new VueBouton("Récupère l'artefact [F]", "Artefact");
    public JButton btn_fin_tour = new VueBouton("Termine le tour [ENTER]", "Fin");

    public VueCommande(Modele m) {
        this.modele = m;

        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Affiche le panel pour assecher une zone.
        JPanel panel_secher = new JPanel();
        panel_secher.setLayout(new GridBagLayout());
        panel_secher.setPreferredSize(
                new Dimension(ConstsValue.BOX_SIZE * this.modele.getGrille().getWidth() * 3 / 7, 100));
        panel_secher.add(this.btn_secher_haut, Utils.positionneGrille(1, 0));
        panel_secher.add(this.btn_secher_bas, Utils.positionneGrille(1, 2));
        panel_secher.add(this.btn_secher_centre, Utils.positionneGrille(1, 1));
        panel_secher.add(this.btn_secher_gauche, Utils.positionneGrille(0, 1));
        panel_secher.add(this.btn_secher_droite, Utils.positionneGrille(2, 1));
        this.add(panel_secher);

        // Affiche le panel des actions.
        JPanel panel_actions = new JPanel();
        panel_actions.setLayout(new GridBagLayout());
        panel_actions.add(this.btn_clef, Utils.positionneGrille(0, 0, 5));
        panel_actions.add(this.btn_prendre, Utils.positionneGrille(1, 0, 5));
        panel_actions.add(this.btn_fin_tour, Utils.positionneGrille(2, 0, 5));
        this.add(panel_actions);

        gereVisibiliteBoutons();
    }

    public void gereVisibiliteBoutons() {
        boolean peut_jouer = this.modele.getJoueurActuel().estSonTour();

        this.btn_secher_haut.setEnabled(peut_jouer && estCaseInonder(Direction.HAUT));
        this.btn_secher_bas.setEnabled(peut_jouer && estCaseInonder(Direction.BAS));
        this.btn_secher_centre.setEnabled(peut_jouer && estCaseInonder(Direction.NEUTRE));
        this.btn_secher_gauche.setEnabled(peut_jouer && estCaseInonder(Direction.GAUCHE));
        this.btn_secher_droite.setEnabled(peut_jouer && estCaseInonder(Direction.DROITE));

        this.btn_clef.setEnabled(peut_jouer);
        this.btn_prendre.setEnabled(peut_jouer && estCaseArtefact());
        this.btn_fin_tour.setEnabled(this.modele.tourPeutFinir());
    }

    private boolean estCaseArtefact() {
        return this.modele.getGrille().getCase(this.modele.getJoueurActuel().getCoord()).aObjet(Artefact.class);
    }

    private boolean estCaseInonder(Direction dir) {
        return caseAdjacentJoueur(dir).getEtat() == Inondation.INONDEE;
    }

    private Case caseAdjacentJoueur(Direction dir) {
        Coord coord_joueurs = this.modele.getJoueurActuel().getCoord();
        return this.modele.getGrille().getCase(coord_joueurs).adjacent(dir);
    }
}