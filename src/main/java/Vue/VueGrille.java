package Vue;

import Modele.*;

import javax.swing.*;
import java.awt.*;

class VueGrille extends JPanel {
    private final int WIDTH;
    private final int HEIGHT;
    private final int GAP = 2;
    private final int GAP_BORDER = 10;

    private Modele modele;

    public VueGrille(Modele m) {
        this.modele = m;

        this.WIDTH = this.modele.getGrille().getWidth();
        this.HEIGHT = this.modele.getGrille().getHeight();
        
        GridLayout layout = new GridLayout(this.WIDTH, this.HEIGHT);
        layout.setHgap(this.GAP);
        layout.setVgap(this.GAP);

        this.setLayout(layout);
        this.setBorder(BorderFactory.createEmptyBorder(0, this.GAP_BORDER, 0, this.GAP_BORDER));
        this.paintGrid();
    }

    private void paintGrid(){
        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                Case c = this.modele.getGrille().getCase(j, i);
                this.add(new VueCase(this.modele, c));
            }
        }
    }
}
