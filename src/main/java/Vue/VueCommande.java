package Vue;

import Modele.Modele;

import java.awt.*;

import javax.swing.*;


public class VueCommande extends JPanel {
    private Modele modele;

    public JButton btn_move_top = new VueBouton("⮝");
    public JButton btn_move_bottom = new VueBouton("⮟");
    public JButton btn_move_left = new VueBouton("⮜");
    public JButton btn_move_right = new VueBouton("⮞");

    public JButton btn_assecher_top = new VueBouton("◉");
    public JButton btn_assecher_bottom = new VueBouton("◉");
    public JButton btn_assecher_left = new VueBouton("◉");
    public JButton btn_assecher_right = new VueBouton("◉");
    public JButton btn_assecher_center = new VueBouton("◉");

    public JButton btn_clef = new VueBouton("Chercher Clef");
    public JButton btn_next = new VueBouton("Tour Suivant");


    public VueCommande(Modele m){
        this.modele = m;
        
        this.setLayout(new FlowLayout());
        
        // Affiche le panel pour deplacer le joueurs.
        JPanel panel_deplacement = new JPanel();
        panel_deplacement.setLayout(new GridBagLayout());
        panel_deplacement.setPreferredSize(new Dimension(3*this.modele.getGrille().getWidth()*60/8, 100));
        panel_deplacement.add(this.btn_move_top, positionGrid(1, 0, 1, 1));
        panel_deplacement.add(this.btn_move_bottom, positionGrid(1, 2, 1, 1));
        panel_deplacement.add(this.btn_move_left, positionGrid(0, 1, 1, 1));
        panel_deplacement.add(this.btn_move_right, positionGrid(2, 1, 1, 1));
        this.add(panel_deplacement);

        // Affiche le panel des actions.
        JPanel panel_actions = new JPanel();
        panel_actions.setLayout(new GridBagLayout());
        panel_actions.add(this.btn_clef, positionGrid(0, 0, 1, 1));
        panel_actions.add(this.btn_next, positionGrid(0, 1, 1, 1));
        this.add(panel_actions);

        // Affiche le panel pour assecher une zone.
        JPanel panel_assecher = new JPanel();
        panel_assecher.setLayout(new GridBagLayout());
        panel_assecher.setPreferredSize(new Dimension(3*this.modele.getGrille().getWidth()*60/8, 100));
        panel_assecher.add(this.btn_assecher_top, positionGrid(1, 0, 1, 1));
        panel_assecher.add(this.btn_assecher_bottom, positionGrid(1, 2, 1, 1));
        panel_assecher.add(this.btn_assecher_center, positionGrid(1, 1, 1, 1));
        panel_assecher.add(this.btn_assecher_left, positionGrid(0, 1, 1, 1));
        panel_assecher.add(this.btn_assecher_right, positionGrid(2, 1, 1, 1));
        this.add(panel_assecher);
    }

    GridBagConstraints positionGrid(int pos_x, int pos_y, int grid_width, int grid_height){
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = pos_x;
        gc.gridy = pos_y;
        gc.gridwidth = grid_width;
        gc.gridheight = grid_height;

        return gc;
    }
}