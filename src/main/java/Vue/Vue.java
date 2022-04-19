package Vue;

import Modele.*;

import javax.swing.*;
import java.awt.*;

public class Vue {
    private JFrame fenetre;
    private Modele modele;

    public VueInfoHaut vue_info_haut;
    public VueContainerInventaires vue_inventaires;
    public VueGrille vue_grille;
    public VueLog vue_log;
    public VueInfoBas vue_info_bas;

    public Vue(Modele m) {
        this.modele = m;

        this.fenetre = new JFrame();
        this.fenetre.setTitle("L'Ile Interdite");
        this.fenetre.setLayout(new GridBagLayout());
        this.fenetre.setResizable(false);

        this.commencer();

        this.fenetre.pack();
        this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.fenetre.setLocationRelativeTo(null);
        this.fenetre.setVisible(true);
    }
    
    public void commencer(){
        this.fenetre.getContentPane().removeAll();
        
        this.vue_info_haut = new VueInfoHaut(this.modele);
        this.vue_inventaires = new VueContainerInventaires(this.modele);
        this.vue_grille = new VueGrille(this.modele);
        this.vue_info_bas = new VueInfoBas(this.modele);
        this.vue_log = new VueLog();

        this.fenetre.add(this.vue_info_haut, Utils.positionneGrille(0, 0, 2, 1, 1));
        this.fenetre.add(this.vue_inventaires, Utils.positionneGrille(0, 1, 0));
        this.fenetre.add(this.vue_grille, Utils.positionneGrille(1, 1));
        this.fenetre.add(this.vue_info_bas, Utils.positionneGrille(1, 2));
        this.fenetre.add(this.vue_log, Utils.positionneGrille(1, 3, 3, 1, 1));

        this.fenetre.repaint();
        this.fenetre.revalidate();
    }

    public JFrame getFenetre() {
        return this.fenetre;
    }
}