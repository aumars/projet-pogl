package Vue;

import Modele.Modele;

import javax.swing.*;
import java.awt.*;

public class VueState extends JPanel {
    private Modele modele;
    private JLabel label_round;

    public VueState(Modele m) {
        this.modele = m;
        
        label_round = new JLabel("Tour n°" + this.modele.getTour());
        label_round.setFont(new Font("Calibri", Font.BOLD, 25));

        this.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        this.add(this.label_round);
    }

    public void update(){
        super.repaint();
        this.label_round.setText("Tour n°" + this.modele.getTour());
    }
}
