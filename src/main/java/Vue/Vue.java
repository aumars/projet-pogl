package Vue;

import Modele.*;

import javax.swing.*;
import java.awt.*;

/**
 * L'interface graphique du jeu.
 */
public class Vue {
    /**
     * Le modèle que l'interface graphique représente
     */
    private final Modele MODELE;

    /**
     * La fenêtre de l'interface graphique
     */
    private final JFrame FENETRE_JEU;

    /**
     * La fenêtre du menu de démarrage
     */
    private final JFrame FENETRE_DEMARRAGE;

    /**
     * L'interface graphique du menu de démarrage
     */
    public VueStart vue_start;

    /**
     * L'interface graphique en haut de la fenêtre
     */
    public VueInfoHaut vue_info_haut;

    /**
     * L'interface graphique de l'ensemble des inventaires
     */
    public VueContainerInventaires vue_inventaires;

    /**
     * L'interface graphique de la grille
     */
    public VueGrille vue_grille;

    /**
     * L'interface graphique en bas de la fenêtre
     */
    public VueInfoBas vue_info_bas;

    /**
     * Drapeau pour afficher le menu de démarrage ou pas
     */
    public boolean afficheMenuDemarrage = true;

    /**
     * Controle l'affichage global du jeu.
     * 
     * @param m Le modele du jeu.
     */
    public Vue(Modele m) {
        this.MODELE = m;
        this.FENETRE_DEMARRAGE = new JFrame();
        this.FENETRE_JEU = new JFrame();

        this.vue_start = new VueStart();
        this.vue_info_haut = new VueInfoHaut(this.MODELE);
        this.vue_inventaires = new VueContainerInventaires(this.MODELE);
        this.vue_grille = new VueGrille(this.MODELE);
        this.vue_info_bas = new VueInfoBas(this.MODELE);

        this.afficheFenetre();
    }

    /**
     * Selectionne d'afficher la fenetre de démarrage ou de jeu.
     */
    public void afficheFenetre(){
        if (afficheMenuDemarrage) {
            this.FENETRE_DEMARRAGE.setTitle("L'Ile Interdite");
            this.FENETRE_DEMARRAGE.setResizable(false);

            this.FENETRE_DEMARRAGE.add(this.vue_start);

            this.FENETRE_DEMARRAGE.pack();
            this.FENETRE_DEMARRAGE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.FENETRE_DEMARRAGE.setLocationRelativeTo(null);
            this.FENETRE_DEMARRAGE.setVisible(true);
        }

        else{
            this.FENETRE_DEMARRAGE.setVisible(false);
            this.FENETRE_DEMARRAGE.dispose();

            this.FENETRE_JEU.setTitle("L'Ile Interdite");
            this.FENETRE_JEU.setLayout(new GridBagLayout());
            this.FENETRE_JEU.setResizable(false);

            this.commencer();

            this.FENETRE_JEU.pack();
            this.FENETRE_JEU.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.FENETRE_JEU.setLocationRelativeTo(null);
            this.FENETRE_JEU.setVisible(true);
        }
    }
    
    /**
     * Créer l'affichage du jeu.
     */
    public void commencer() {
        this.FENETRE_JEU.getContentPane().removeAll();

        this.vue_start = new VueStart();
        this.vue_info_haut = new VueInfoHaut(this.MODELE);
        this.vue_inventaires = new VueContainerInventaires(this.MODELE);
        this.vue_grille = new VueGrille(this.MODELE);
        this.vue_info_bas = new VueInfoBas(this.MODELE);

        this.FENETRE_JEU.add(this.vue_info_haut, Utils.positionneGrille(0, 0, 2, 1, 1));
        this.FENETRE_JEU.add(this.vue_inventaires, Utils.positionneGrille(0, 1, 0));
        this.FENETRE_JEU.add(this.vue_grille, Utils.positionneGrille(1, 1));
        this.FENETRE_JEU.add(this.vue_info_bas, Utils.positionneGrille(1, 2));

        this.FENETRE_JEU.revalidate();
        this.FENETRE_JEU.repaint();
    }

    /**
     * Récupere la fenetre global du jeu.
     */
    public JFrame getFenetre() {
        return this.FENETRE_JEU;
    }
}