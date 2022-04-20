package Vue;

import Modele.*;

import javax.swing.*;
import java.awt.*;

public class Vue {
    public JFrame fenetre_jeu;
    private JFrame fenetre_demarrage;
    private Modele modele;

    public VueStart vue_start;
    public VueInfoHaut vue_info_haut;
    public VueContainerInventaires vue_inventaires;
    public VueGrille vue_grille;
    public VueInfoBas vue_info_bas;

    public boolean afficheMenuDemarrage = true;

    /**
     * Controle l'affichage global du jeu.
     * 
     * @param m Le modele du jeu.
     */
    public Vue(Modele m) {
        this.modele = m;
        this.fenetre_demarrage = new JFrame();
        this.fenetre_jeu = new JFrame();

        this.vue_start = new VueStart();
        this.vue_info_haut = new VueInfoHaut(this.modele);
        this.vue_inventaires = new VueContainerInventaires(this.modele);
        this.vue_grille = new VueGrille(this.modele);
        this.vue_info_bas = new VueInfoBas(this.modele);

        this.afficheFenetre();
    }

    /**
     * Selectionne d'afficher la fenetre de démarrage ou de jeu.
     */
    public void afficheFenetre(){        
        if (afficheMenuDemarrage) {
            this.fenetre_demarrage.setTitle("L'Ile Interdite");
            this.fenetre_demarrage.setResizable(false);
            
            this.fenetre_demarrage.add(this.vue_start);

            this.fenetre_demarrage.pack();
            this.fenetre_demarrage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.fenetre_demarrage.setLocationRelativeTo(null);
            this.fenetre_demarrage.setVisible(true);
        }

        else{
            this.fenetre_demarrage.setVisible(false);
            this.fenetre_demarrage.dispose();

            this.fenetre_jeu.setTitle("L'Ile Interdite");
            this.fenetre_jeu.setLayout(new GridBagLayout());
            this.fenetre_jeu.setResizable(false);
    
            this.commencer();
    
            this.fenetre_jeu.pack();
            this.fenetre_jeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.fenetre_jeu.setLocationRelativeTo(null);
            this.fenetre_jeu.setVisible(true);
        }
    }
    
    /**
     * Créer l'affichage du jeu.
     */
    public void commencer() {
        this.fenetre_jeu.getContentPane().removeAll();

        this.vue_start = new VueStart();
        this.vue_info_haut = new VueInfoHaut(this.modele);
        this.vue_inventaires = new VueContainerInventaires(this.modele);
        this.vue_grille = new VueGrille(this.modele);
        this.vue_info_bas = new VueInfoBas(this.modele);

        this.fenetre_jeu.add(this.vue_info_haut, Utils.positionneGrille(0, 0, 2, 1, 1));
        this.fenetre_jeu.add(this.vue_inventaires, Utils.positionneGrille(0, 1, 0));
        this.fenetre_jeu.add(this.vue_grille, Utils.positionneGrille(1, 1));
        this.fenetre_jeu.add(this.vue_info_bas, Utils.positionneGrille(1, 2));

        this.fenetre_jeu.revalidate();
        this.fenetre_jeu.repaint();
    }

    /**
     * Récupere la fenetre global du jeu.
     */
    public JFrame getFenetre() {
        return this.fenetre_jeu;
    }
}