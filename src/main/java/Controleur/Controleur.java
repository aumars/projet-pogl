package Controleur;

import Modele.*;
import Vue.*;
import java.awt.event.*;

public class Controleur implements ActionListener {
    private Modele modele;
    private Vue vue;
    private VueCommande commande;
    private Joueur joueur;

    public Controleur(Modele m, Vue v) {
        this.modele = m;
        this.vue = v;
        this.joueur = this.modele.getJoueurActuel();
        this.commande = this.vue.commande;

        this.commande.btn_move_top.addActionListener(this);
        this.commande.btn_move_bottom.addActionListener(this);
        this.commande.btn_move_left.addActionListener(this);
        this.commande.btn_move_right.addActionListener(this);

        this.commande.btn_assecher_top.addActionListener(this);
        this.commande.btn_assecher_bottom.addActionListener(this);
        this.commande.btn_assecher_left.addActionListener(this);
        this.commande.btn_assecher_right.addActionListener(this);
        this.commande.btn_assecher_center.addActionListener(this);

        this.commande.btn_next.addActionListener(this);
        this.commande.btn_prendre.addActionListener(this);
        this.commande.btn_clef.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        // Gere le deplacement du joueurs.
        if (this.commande.btn_move_top.getModel().isArmed()) {
            this.deplaceJoueur(Direction.HAUT);
        }

        if (this.commande.btn_move_bottom.getModel().isArmed()) {
            this.deplaceJoueur(Direction.BAS);
        }

        if (this.commande.btn_move_left.getModel().isArmed()) {
            this.deplaceJoueur(Direction.GAUCHE);
        }

        if (this.commande.btn_move_right.getModel().isArmed()) {
            this.deplaceJoueur(Direction.DROITE);
        }

        // Gere l'assechement du sol.
        if (this.commande.btn_assecher_top.getModel().isArmed()) {
            this.joueur.asseche(Direction.HAUT);
        }

        if (this.commande.btn_assecher_bottom.getModel().isArmed()) {
            this.joueur.asseche(Direction.BAS);
        }

        if (this.commande.btn_assecher_left.getModel().isArmed()) {
            this.joueur.asseche(Direction.GAUCHE);
        }

        if (this.commande.btn_assecher_right.getModel().isArmed()) {
            this.joueur.asseche(Direction.DROITE);
        }

        if (this.commande.btn_assecher_center.getModel().isArmed()) {
            this.joueur.asseche(Direction.NEUTRE);
        }

        // Gere les actions de la partie.
        if (this.commande.btn_prendre.getModel().isArmed()) {
            this.joueur.recupereArtefact();
            this.vue.grille.updateCase(this.joueur.getCoord());
            this.vue.state.update();
        }

        if (this.commande.btn_clef.getModel().isArmed()) {
            if (this.joueur.chercheCle())
                this.vue.inventory.updateAffichageObj();
        }

        if (this.commande.btn_next.getModel().isArmed()) {
            this.modele.tourSuivant();
            this.vue.state.update();
        }

        this.joueur = this.modele.getJoueurActuel();
        this.vue.commande.disableUnusedButton();
    }

    private boolean deplaceJoueur(Direction dir) {
        Coord prev = this.joueur.getCoord();
        Coord next = this.modele.getGrille().getCase(prev).adjacent(dir).coord;
        boolean est_fini = this.joueur.deplace(dir);
        this.vue.grille.updatePlayerMove(prev, next);
        return est_fini;
    }
}
