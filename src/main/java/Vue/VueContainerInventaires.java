package Vue;

import javax.swing.JPanel;

import Modele.Modele;

public class VueContainerInventaires extends JPanel {
    private Modele modele;
    public VueInventaire[] inventaires;

    public VueContainerInventaires(Modele m){
        this.modele = m;
        this.afficheInventaires();
    }   
    
    private void afficheInventaires(){
        int nb_joueurs = this.modele.getNbJoueurs();
        this.inventaires = new VueInventaire[nb_joueurs];
        
        for (int i = 0; i < nb_joueurs; i++) {
            this.inventaires[i] = new VueInventaire(this.modele, "Player"+i);
            this.add(this.inventaires[i]);
        }
    }
}
