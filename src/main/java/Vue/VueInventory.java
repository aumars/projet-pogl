package Vue;

import java.awt.*;
import javax.swing.*;

import Modele.*;

public class VueInventory extends JPanel {
    private Modele modele;
    private JLabel label_player = new JLabel();
    private JPanel panel_items = new JPanel();

    public VueInventory(Modele m, String name) {
        this.modele = m;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        this.label_player.setText(name);
        this.label_player.setFont(new Font("Times", Font.PLAIN, 20));
        this.add(label_player);
        
        this.panel_items.setLayout(new BoxLayout(this.panel_items, BoxLayout.Y_AXIS));
        this.add(panel_items);
    }

    public void updateAffichageObj() {
        this.panel_items.add(new VueObjet());
        this.updateUI();
    }
}
