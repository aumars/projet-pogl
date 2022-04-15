package Vue;

import javax.swing.*;

import Modele.Joueur;
import Modele.Modele;

import java.awt.*;

public class VueBottom extends JPanel {
    private Modele modele;
    public VueCommande commande;
    public VueEndGame endgame;
    public VueAide aide;
    private boolean affiche_aide = false;
    private boolean est_fin_jeu = false;
    
    public VueBottom(Modele m){
        this.modele = m;
        this.commande = new VueCommande(this.modele);
        this.aide = new VueAide(this.modele);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(Constants.BOX_SIZE * this.modele.getGrille().getWidth() * 3/7, 120));
        this.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));    

        this.add(this.commande);
    }

    public void updateEndGame(){
        Joueur gagnant = this.modele.verifieGagnants();
        
        if(gagnant != null || this.modele.tousJoueursMorts()){
            this.commande.setVisible(false);
            this.endgame = new VueEndGame(this.modele, gagnant == null);
            this.est_fin_jeu = true;
        }
    }
    
    public void affichePanneauAide(){
        if (!affiche_aide) {
            this.commande.setVisible(false);
            this.add(this.aide, BorderLayout.LINE_START);
        }
        
        else{
            this.commande.setVisible(!this.est_fin_jeu);
            this.remove(aide);
        }
        
        this.affiche_aide = !this.affiche_aide;
    }
}
