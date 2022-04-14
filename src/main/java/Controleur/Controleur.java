package Controleur;

import Modele.*;
import Vue.*;
import java.awt.event.*;

public class Controleur implements ActionListener, KeyListener {
    private Modele modele;
    private Vue vue;
    private VueCommande commande;
    private Joueur joueur;

    public Controleur(Modele m, Vue v) {
        this.modele = m;
        this.vue = v;
        this.joueur = this.modele.getJoueurActuel();
        this.commande = this.vue.bottom.commande;

        this.vue.getFrame().addKeyListener(this);

        this.commande.btn_assecher_top.addActionListener(this);
        this.commande.btn_assecher_bottom.addActionListener(this);
        this.commande.btn_assecher_left.addActionListener(this);
        this.commande.btn_assecher_right.addActionListener(this);
        this.commande.btn_assecher_center.addActionListener(this);

        this.commande.btn_next.addActionListener(this);
        this.commande.btn_prendre.addActionListener(this);
        this.commande.btn_clef.addActionListener(this);
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

            case KeyEvent.VK_SPACE:
                this.chercheClef();
                break;
            
            case KeyEvent.VK_F:
                this.prendArtefact();
                break;

            case KeyEvent.VK_ESCAPE:
                this.vue.getFrame().setVisible(false);
                this.vue.getFrame().dispose();
                break;

            default:
                break;
        }

        this.update();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
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
            this.prendArtefact();
        }

        if (this.commande.btn_clef.getModel().isArmed()) {
            this.chercheClef();
        }

        if (this.commande.btn_next.getModel().isArmed()) {
            this.tourSuivant();
        }

        this.update();
    }

    private boolean deplaceJoueur(Direction dir) {
        Coord prev = this.joueur.getCoord();
        Coord next = this.modele.getGrille().getCase(prev).adjacent(dir).coord;
        boolean est_fini = this.joueur.deplace(dir);
        this.vue.grille.updatePlayerMove(prev, next);
        return est_fini;
    }

    private void update() {
        this.vue.grille.updateCase(this.joueur.getCoord());
        this.joueur = this.modele.getJoueurActuel();
        this.vue.bottom.commande.disableUnusedButton();
    }

    private void tourSuivant() {
        this.modele.tourSuivant();
        this.vue.state.update();
        this.vue.inventory.updateEtatJoueur();
        this.vue.bottom.updateEndGame();
    }

    private void chercheClef(){
        Objet clef = this.joueur.chercheCle();

        if (clef != null){
            this.vue.inventory.updateAffichageObj(clef);
            this.vue.grille.updateCase(this.joueur.getCoord());
            this.vue.state.update();
        }
    }

    private void prendArtefact(){
        Objet artefact = this.joueur.recupereArtefact();
    
        if (artefact != null) {
            this.vue.inventory.updateAffichageObj(artefact);
            this.vue.grille.updateCase(this.joueur.getCoord());
            this.vue.state.update();
        }
    }
}
