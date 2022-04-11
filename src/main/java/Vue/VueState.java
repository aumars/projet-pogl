package Vue;

import Modele.Modele;

import javax.swing.*;
import java.awt.*;

public class VueState extends JPanel {
    private Modele modele;

    public VueState(Modele m) {
        this.modele = m;
        
        String title = "Tour n°" + this.modele.getTour();
        JLabel label = new JLabel(title);
        label.setFont(new Font("Calibri", Font.BOLD, 25));
        
        this.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        this.add(label);
    }
}
