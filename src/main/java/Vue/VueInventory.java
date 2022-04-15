package Vue;

import java.awt.*;
import javax.swing.*;

import Modele.*;

public class VueInventory extends JPanel {
    private final int max_items = 9;
    private Modele modele;
    private JLabel label_player = new JLabel();
    private JPanel panel_items = new JPanel();
    private String name;

    public VueInventory(Modele m, String name) {
        this.modele = m;
        this.name = name;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.label_player.setText(name);
        this.label_player.setPreferredSize(new Dimension(100, 100));
        this.label_player.setBorder(BorderFactory.createEmptyBorder(20, 25, 5, 0));
        this.label_player.setFont(new Font("", Font.BOLD, 20));
        this.label_player.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(label_player);

        this.panel_items.setLayout(new BoxLayout(this.panel_items, BoxLayout.PAGE_AXIS));
        this.panel_items.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        this.remplieInventaireVide();
        this.add(panel_items);
    }

    public void updateAffichageObj(Objet object_to_add) {
        this.panel_items.removeAll();

        for (Objet o : this.modele.getJoueurActuel().getInventaire()) {
            this.panel_items.add(new VueObjet(o));
        }

        this.remplieInventaireVide();
        this.updateUI();
    }

    private void remplieInventaireVide() {
        int nb_items = this.panel_items.getComponentCount();
        for (int i = 0; i < this.max_items-nb_items; i++)
            this.panel_items.add(new VueObjet(null));
    }

    public void updateEtatJoueur() {
        if (!this.modele.getJoueurActuel().estVivant()) {
            this.label_player.setText("<html><strike>" + this.name + "</strike></html>");
            this.label_player.setForeground(Color.RED);
        }
    }
}
