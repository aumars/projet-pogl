package Vue;

import javax.swing.JPanel;

import Modele.Modele;

public class VueStart extends JPanel {
    private Modele modele;

    public VueStart(Modele m){
        this.modele = m;
        
        int height = this.modele.getGrille().getHeight();
        int width = this.modele.getGrille().getWidth();

        
    }
}
