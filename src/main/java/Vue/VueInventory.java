package Vue;

import java.awt.*;
import javax.swing.*;

import Modele.*;

public class VueInventory extends JPanel {
    private Modele modele;
    private JLabel label_player = new JLabel();
    private JPanel panel_items = new JPanel();
    private String name;

    public VueInventory(Modele m, String name) {
        this.modele = m;
        this.name = name;

        // this.setBackground(Color.CYAN    );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        this.label_player.setText(name);
        this.label_player.setPreferredSize(new Dimension(100, 100));
        this.label_player.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 0));
        this.label_player.setFont(new Font("Arial", Font.BOLD, 20));
        this.label_player.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label_player);

        this.panel_items.setLayout(new BoxLayout(this.panel_items, BoxLayout.PAGE_AXIS));
        this.panel_items.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        this.add(panel_items);
    }

    public void updateAffichageObj(Objet o) {
        if (this.panel_items.getComponentCount() < 7) {
            this.panel_items.add(new VueObjet(o));
            this.updateUI();
        }
    }

    public void updateEtatJoueur(){
        if (!this.modele.getJoueurActuel().estVivant()) {
            this.label_player.setText("<html><strike>"+this.name+"</strike></html>");
            this.label_player.setForeground(Color.RED);
        }
    }
}
