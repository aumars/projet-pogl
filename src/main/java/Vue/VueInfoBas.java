package Vue;

import Modele.*;
import javax.swing.*;
import java.awt.*;

public class VueInfoBas extends JPanel {
    private Modele modele;
    
    public VueCommande vue_commande;
    public VueFinJeu vue_fin_jeu;
    public VueAide vue_aide;
    
    private boolean affiche_aide = false;
    private boolean est_fin_jeu = false;
    
    public VueInfoBas(Modele m){
        this.modele = m;
        this.vue_commande = new VueCommande(this.modele);
        this.vue_aide = new VueAide(this.modele);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(ConstsValue.BOX_SIZE * this.modele.getGrille().getWidth() * 3/7, 120));
        this.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));   

        this.add(this.vue_commande);
    }

    public boolean verifieFinJeu(){
        boolean jeuGagne = this.modele.verifieGagnants();

        if (jeuGagne || this.modele.tousJoueursMorts()) {
            this.vue_commande.setVisible(false);
            this.vue_fin_jeu = new VueFinJeu(this.modele, !jeuGagne);
            this.est_fin_jeu = true;
            this.add(this.vue_fin_jeu);
            return true;
        }

        return false;
    }
    
    public void affichePanneauAide(){
        if (!affiche_aide) {
            this.vue_commande.setVisible(false);
            this.add(this.vue_aide, BorderLayout.LINE_START);
        }
        
        else{
            this.vue_commande.setVisible(!this.est_fin_jeu);
            this.remove(this.vue_aide);
        }
        
        this.affiche_aide = !this.affiche_aide;
    }
}
