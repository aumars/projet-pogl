package Controleur;

import Modele.*;
import Vue.*;

import java.awt.Cursor;
import java.awt.event.*;

public class Controleur implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
    private final Modele modele;
    private final Vue vue;
    private VueCommande vue_commande;
    private Joueur joueur;
    private boolean est_presse_touche = false;

    /**
     * Controlleur du jeu.
     * 
     * @param m Le modele.
     * @param v La vue.
     */
    public Controleur(Modele m, Vue v) {
        this.modele = m;
        this.vue = v;
        this.joueur = this.modele.getJoueurActuel();
        this.metsAJourEventListener();
    }

    /**
     * Mets à jours les events listener.
     */
    private void metsAJourEventListener() {
        this.vue.getFenetre().addKeyListener(this);
        this.vue_commande = this.vue.vue_info_bas.vue_commande;

        this.vue.vue_start.btn_facile.addActionListener(this);
        this.vue.vue_start.btn_normal.addActionListener(this);
        this.vue.vue_start.btn_difficile.addActionListener(this);
        this.vue.vue_start.btn_deterministe.addActionListener(this);
        this.vue.vue_start.btn_jouer.addActionListener(this);

        this.vue_commande.btn_secher.addActionListener(this);

        this.vue_commande.btn_fin_tour.addActionListener(this);
        this.vue_commande.btn_prendre.addActionListener(this);
        this.vue_commande.btn_clef.addActionListener(this);

        this.vue_commande.btn_sac_sable.addActionListener(this);
        this.vue_commande.btn_teleporte.addActionListener(this);

        this.vue.vue_info_haut.btn_aide.addActionListener(this);
        this.vue.vue_grille.addMouseMotionListener(this);

        this.vue.vue_grille.addMouseListener(this);
    }

    /**
     * Methode appelé lorsqu'une touche est préssée.
     */
    public void keyPressed(KeyEvent e) {
        if (est_presse_touche)
            return;
        else
            this.est_presse_touche = true;

        if (!this.modele.getFinJeu()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_Z:
                    this.deplaceJoueur(Direction.HAUT);
                    break;

                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    this.deplaceJoueur(Direction.BAS);
                    break;

                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_Q:
                    this.deplaceJoueur(Direction.GAUCHE);
                    break;

                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    this.deplaceJoueur(Direction.DROITE);
                    break;

                case KeyEvent.VK_ENTER:
                    this.tourSuivant();
                    break;

                case KeyEvent.VK_A:
                    this.chercheClef();
                    break;

                case KeyEvent.VK_F:
                    this.prendArtefact();
                    break;

                case KeyEvent.VK_O:
                    this.joueur.asseche(Direction.HAUT);
                    break;

                case KeyEvent.VK_K:
                    this.joueur.asseche(Direction.GAUCHE);
                    break;

                case KeyEvent.VK_L:
                    this.joueur.asseche(Direction.BAS);
                    break;

                case KeyEvent.VK_M:
                    this.joueur.asseche(Direction.DROITE);
                    break;

                case KeyEvent.VK_SPACE:
                    this.joueur.asseche(Direction.NEUTRE);
                    break;

                case KeyEvent.VK_ESCAPE:
                    this.vue.getFenetre().setVisible(false);
                    this.vue.getFenetre().dispose();
                    break;

                case KeyEvent.VK_H:
                    this.vue.vue_info_bas.affichePanneauAide();
                    break;

                default:
                    break;
            }

            this.metAJourApresAction();
        }
    }

    /**
     * Methode appelé lorsque la souris quitte la VueGrille.
     */
    public void mouseExited(MouseEvent e) {
        this.vue.vue_grille.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * Methode appelé lorsque la souris se deplace.
     */
    public void mouseMoved(MouseEvent e) {
        if (this.vue_commande.btn_secher.estActive()) {
            Case case_over = this.getMouseCase(e.getX(), e.getY());

            if (case_over.getEtat() == Inondation.INONDEE && this.joueur.getCoord().estAdjacent(case_over.COORD))
                this.vue.vue_grille.setCursor(ConstsIcon.CURSEUR_SECHER);

            else
                this.vue.vue_grille.setCursor(ConstsIcon.CURSEUR_INTERDIT);
        }

        else if (this.vue_commande.btn_sac_sable.estActive()) {
            if (this.getMouseCase(e.getX(), e.getY()).getEtat() == Inondation.INONDEE)
                this.vue.vue_grille.setCursor(ConstsIcon.CURSEUR_SECHER);

            else
                this.vue.vue_grille.setCursor(ConstsIcon.CURSEUR_INTERDIT);
        }

        else if (this.vue_commande.btn_teleporte.estActive()) {
            if (this.getMouseCase(e.getX(), e.getY()).estTraversable())
                this.vue.vue_grille.setCursor(ConstsIcon.CURSEUR_TELEPORTE);

            else
                this.vue.vue_grille.setCursor(ConstsIcon.CURSEUR_INTERDIT);
        }

        else {
            Case case_over = this.getMouseCase(e.getX(), e.getY());

            if (this.joueur.estSonTour() && this.joueur.getCoord().estAdjacent(case_over.COORD)
                    && case_over.estTraversable())
                this.vue.vue_grille.setCursor(ConstsIcon.CURSEUR_TELEPORTE);

            else
                this.vue.vue_grille.setCursor(ConstsIcon.CURSEUR_INTERDIT);
        }
    }

    /**
     * Methode appelé l'on clique sur la souris.
     */
    public void mousePressed(MouseEvent e) {
        if (this.vue_commande.btn_secher.estActive()) {
            Case case_pressed = this.getMouseCase(e.getX(), e.getY());

            if (this.joueur.getCoord().estAdjacent(case_pressed.COORD)) {
                this.joueur.asseche(case_pressed);
                this.vue.vue_grille.metAJourCase(case_pressed.COORD);
            }
        }

        else if (this.vue_commande.btn_sac_sable.estActive()) {
            Case case_pressed = this.getMouseCase(e.getX(), e.getY());

            if (case_pressed.getEtat() == Inondation.INONDEE) {
                this.joueur.asseche(case_pressed);
                this.vue.vue_grille.metAJourCase(case_pressed.COORD);
            }
        }

        else if (this.vue_commande.btn_teleporte.estActive()) {
            Coord case_prev_joueur = this.joueur.getCoord();
            Case case_pressed = this.getMouseCase(e.getX(), e.getY());

            if (case_pressed.estTraversable()) {
                this.joueur.helicoptere(case_pressed);
                this.vue.vue_grille.metAJourDeplacementJoueur(case_prev_joueur, case_pressed.COORD);
            }
        }

        else {
            Case case_pressed = this.getMouseCase(e.getX(), e.getY());

            if (this.joueur.estSonTour() && this.joueur.getCoord().estAdjacent(case_pressed.COORD)
                    && case_pressed.estTraversable())
                this.deplaceJoueur(this.joueur.getCoord().adjacentDir(case_pressed.COORD));
        }

        this.vue_commande.metAJourRadioBouton();
        this.metAJourApresAction();
    }

    /**
     * Methode appelé lorsqu'on clique sur un bouton.
     */
    public void actionPerformed(ActionEvent e) {
        // Gere les actions de la partie.
        if (this.vue_commande.btn_prendre.getModel().isArmed()) {
            this.prendArtefact();
        }

        if (this.vue_commande.btn_clef.getModel().isArmed()) {
            this.chercheClef();
        }

        if (this.vue_commande.btn_fin_tour.getModel().isArmed()) {
            this.tourSuivant();
        }

        if (this.vue.vue_info_haut.btn_aide.getModel().isArmed()) {
            this.vue.vue_info_bas.affichePanneauAide();
        }

        // Gere les radio boutons.
        if (this.vue_commande.btn_sac_sable.getModel().isArmed()) {
            this.vue_commande.metAJourRadioBouton(this.vue_commande.btn_sac_sable.getId());
        }

        if (this.vue_commande.btn_teleporte.getModel().isArmed()) {
            this.vue_commande.metAJourRadioBouton(this.vue_commande.btn_teleporte.getId());
        }

        if (this.vue_commande.btn_secher.getModel().isArmed()) {
            this.vue_commande.metAJourRadioBouton(this.vue_commande.btn_secher.getId());
        }

        if (this.vue.vue_start.btn_facile.getModel().isArmed()) {
            this.vue.vue_start.metAJourRadioBouton(this.vue.vue_start.btn_facile.getId());
            this.modele.setDifficulte(Difficulte.FACILE);
        }

        if (this.vue.vue_start.btn_normal.getModel().isArmed()) {
            this.vue.vue_start.metAJourRadioBouton(this.vue.vue_start.btn_normal.getId());
            this.modele.setDifficulte(Difficulte.MOYEN);
        }

        if (this.vue.vue_start.btn_difficile.getModel().isArmed()) {
            this.vue.vue_start.metAJourRadioBouton(this.vue.vue_start.btn_difficile.getId());
            this.modele.setDifficulte(Difficulte.DIFFICILE);
        }

        if (this.vue.vue_start.btn_deterministe.getModel().isArmed()) {
            this.vue.vue_start.metAJourRadioBouton(this.vue.vue_start.btn_deterministe.getId());
            this.modele.setDifficulte(Difficulte.DETERMINISTE);
        }

        if (this.vue.vue_start.btn_jouer.getModel().isArmed()) {
            this.vue.afficheMenuDemarrage = false;
            this.vue.afficheFenetre();
            this.metsAJourEventListener();
        }

        if (this.vue.vue_info_bas.vue_fin_jeu != null
                && this.vue.vue_info_bas.vue_fin_jeu.btn_rejouer.getModel().isArmed()) {
            this.metAJourApresAction();
            this.modele.restart();
            this.vue.commencer();
        }

        this.metAJourApresAction();
    }

    /**
     * Deplace le joueur.
     * 
     * @param dir La direction de deplacement.
     */
    private void deplaceJoueur(Direction dir) {
        Coord prev = this.joueur.getCoord();
        Coord next = this.modele.getGrille().getCase(prev).adjacent(dir).COORD;
        this.joueur.deplace(dir);
        this.vue.vue_grille.metAJourDeplacementJoueur(prev, next);
        this.verifieFinJeu();
    }

    /**
     * Verifie si c'est la fin du jeu.
     */
    private void verifieFinJeu() {
        boolean fin_jeu = this.vue.vue_info_bas.verifieFinJeu();
        this.vue.vue_info_haut.metAJourApresAction();

        for (Joueur j : this.modele.getJoueurs()) {
            this.vue.vue_inventaires.inventaires[j.ID].metAJourEtatJoueur(j);
        }
        if (fin_jeu) {
            this.vue.vue_info_bas.vue_fin_jeu.btn_rejouer.addActionListener(this);
        }
    }

    /**
     * Mets a jours lorsque l'utilisateur realise une action.
     */
    private void metAJourApresAction() {
        this.verifieFinJeu();
        this.vue.vue_info_bas.vue_log.ajoutLog(this.joueur.getLogString());
        this.vue.vue_grille.metAJourCase(this.joueur.getCoord());
        this.vue.vue_info_bas.vue_commande.gereVisibiliteBoutons();
        this.vue.vue_grille.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.joueur = this.modele.getJoueurActuel();
    }

    /**
     * Change de tour de la partie.
     */
    private void tourSuivant() {
        this.vue_commande.metAJourRadioBouton();
        this.modele.tourSuivant();
        this.vue.vue_info_haut.metAJourApresAction();
    }

    /**
     * Le joueur cherche une clef.
     */
    private void chercheClef() {
        this.vue_commande.metAJourRadioBouton();

        if (this.joueur.chercheCle() != null) {
            this.vue.vue_inventaires.inventaires[this.joueur.ID].metAJourAffichageObjet();
        }

        this.vue.vue_grille.metAJourCase(this.joueur.getCoord());
        this.verifieFinJeu();
    }

    /**
     * Le joueur prend un artefact.
     */
    private void prendArtefact() {
        this.vue_commande.metAJourRadioBouton();

        if (this.joueur.recupereArtefact() != null) {
            this.vue.vue_inventaires.inventaires[this.joueur.ID].metAJourAffichageObjet();
            this.vue.vue_grille.metAJourCase(this.joueur.getCoord());
        }

        this.verifieFinJeu();
    }

    /**
     * Calcul les coordonnées de la case à partir de coordonnées de la souris.
     * 
     * @param m_x Les coordonnées en x de la souris.
     * @param m_y Les coordonnées en y de la souris.
     * @return les coordonnées de la case.
     */
    private Coord getMouseCoord(int m_x, int m_y) {
        int coord_x = Math.floorDiv(m_x - ConstsValue.BORDER_GRID, ConstsValue.BOX_SIZE + ConstsValue.GAP_CASE);
        int coord_y = Math.floorDiv(m_y, ConstsValue.BOX_SIZE + ConstsValue.GAP_CASE);

        int grid_width = this.modele.getGrille().getWidth();
        int grid_height = this.modele.getGrille().getHeight();

        coord_x = Math.max(coord_x, 0);
        coord_y = Math.max(coord_y, 0);

        coord_x = Math.min(coord_x, grid_width - 1);
        coord_y = Math.min(coord_y, grid_height - 1);

        return new Coord(coord_x, coord_y);
    }

    /**
     * Récupère la case à partir de coordonnées de la souris.
     * 
     * @param m_x Les coordonnées en x de la souris.
     * @param m_y Les coordonnées en y de la souris.
     * @return la case qui correspond au coordonnées de la souris.
     */
    private Case getMouseCase(int m_x, int m_y) {
        return this.modele.getGrille().getCase(getMouseCoord(m_x, m_y));
    }

    /**
     * Methode appelé lorsqu'on relache une touche.
     */
    public void keyReleased(KeyEvent e) {
        this.est_presse_touche = false;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }
}
