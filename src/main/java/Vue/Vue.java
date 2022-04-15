package Vue;

import Modele.*;

import javax.swing.*;
import java.awt.*;

public class Vue {
    private JFrame fenetre;
    private Modele modele;

    public VueInfoHaut vue_info_haut;
    public VueInventaires vue_inventaires;
    public VueGrille vue_grille;
    public VueInfoBas vue_info_bas;

    public Vue(Modele m) {
        this.modele = m;

        this.fenetre = new JFrame();
        this.fenetre.setTitle("L'Ile Interdite");
        this.fenetre.setLayout(new GridBagLayout());
        this.fenetre.setResizable(false);

        this.vue_info_haut = new VueInfoHaut(this.modele);
        this.vue_inventaires = new VueInventaires(this.modele, Utils.souligneLabel(this.modele.getJoueurActuel().nom));
        this.vue_grille = new VueGrille(this.modele);
        this.vue_info_bas = new VueInfoBas(this.modele);

        this.fenetre.add(this.vue_info_haut, Utils.positionneGrille(0, 0, 2, 1, 1));
        this.fenetre.add(this.vue_inventaires, Utils.positionneGrille(0, 1, 1, 3, 0));
        this.fenetre.add(this.vue_grille, Utils.positionneGrille(1, 1));
        this.fenetre.add(this.vue_info_bas, Utils.positionneGrille(1, 2));

        this.fenetre.pack();
        this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.fenetre.setLocationRelativeTo(null);
        this.fenetre.setVisible(true);
    }

    public JFrame getFenetre() {
        return this.fenetre;
    }
}