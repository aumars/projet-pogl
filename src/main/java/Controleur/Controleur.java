package Controleur;

import Modele.*;
import Vue.*;
import java.awt.event.*;

public class Controleur implements ActionListener, KeyListener {
    private Modele modele;
    private Vue vue;
    private VueCommande vue_commande;
    private Joueur joueur;

    public Controleur(Modele m, Vue v) {
        this.modele = m;
        this.vue = v;
        this.joueur = this.modele.getJoueurActuel();
        this.vue_commande = this.vue.vue_info_bas.vue_commande;

        this.vue.getFenetre().addKeyListener(this);

        this.vue_commande.btn_secher_haut.addActionListener(this);
        this.vue_commande.btn_secher_bas.addActionListener(this);
        this.vue_commande.btn_secher_gauche.addActionListener(this);
        this.vue_commande.btn_secher_droite.addActionListener(this);
        this.vue_commande.btn_secher_centre.addActionListener(this);

        this.vue_commande.btn_fin_tour.addActionListener(this);
        this.vue_commande.btn_prendre.addActionListener(this);
        this.vue_commande.btn_clef.addActionListener(this);

        this.vue.vue_info_haut.btn_aide.addActionListener(this);
    }

    public void keyPressed(KeyEvent e) {
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

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        // Gere l'assechement du sol.
        if (this.vue_commande.btn_secher_haut.getModel().isArmed()) {
            this.joueur.asseche(Direction.HAUT);
        }

        if (this.vue_commande.btn_secher_bas.getModel().isArmed()) {
            this.joueur.asseche(Direction.BAS);
        }

        if (this.vue_commande.btn_secher_gauche.getModel().isArmed()) {
            this.joueur.asseche(Direction.GAUCHE);
        }

        if (this.vue_commande.btn_secher_droite.getModel().isArmed()) {
            this.joueur.asseche(Direction.DROITE);
        }

        if (this.vue_commande.btn_secher_centre.getModel().isArmed()) {
            this.joueur.asseche(Direction.NEUTRE);
        }

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

        this.metAJourApresAction();
    }

    private boolean deplaceJoueur(Direction dir) {
        Coord prev = this.joueur.getCoord();
        Coord next = this.modele.getGrille().getCase(prev).adjacent(dir).coord;
        boolean est_fini = this.joueur.deplace(dir);
        this.vue.vue_grille.metAJourDeplacementJoueur(prev, next);
        this.verifieFinJeu();
        return est_fini;
    }

    private void verifieFinJeu() {
        this.vue.vue_info_bas.verifieFinJeu();
        this.vue.vue_info_haut.metAJourApresAction();
        this.vue.vue_inventaires.metAJourEtatJoueur();
    }

    private void metAJourApresAction() {
        this.vue.vue_grille.metAJourCase(this.joueur.getCoord());
        this.joueur = this.modele.getJoueurActuel();
        this.vue.vue_info_bas.vue_commande.gereVisibiliteBoutons();
    }

    private void tourSuivant() {
        this.modele.tourSuivant();
        this.vue.vue_info_haut.metAJourApresAction();
    }

    private void chercheClef() {
        Objet clef = this.joueur.chercheCle();

        if (clef != null) {
            this.vue.vue_inventaires.metAJourAffichageObjet(clef);
        }

        this.vue.vue_grille.metAJourCase(this.joueur.getCoord());
        this.verifieFinJeu();
    }

    private void prendArtefact() {
        Objet artefact = this.joueur.recupereArtefact();

        if (artefact != null) {
            this.vue.vue_inventaires.metAJourAffichageObjet(artefact);
            this.vue.vue_grille.metAJourCase(this.joueur.getCoord());
        }

        this.verifieFinJeu();
    }
}
