package Vue;

import Modele.Modele;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.lang.String;



class VueCommande extends JPanel {
    private Modele modele;

    public VueCommande(Modele m){
        this.modele = m;
        
        this.setLayout(new FlowLayout());
        this.add(controller());
        this.add(actions());
    }

    private JPanel controller(){
        JPanel panel = new JPanel();        
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
        
        JButton btn_move_top = buttonMove("⮝", true);
        JButton btn_move_bottom = buttonMove("⮟", true);
        JButton btn_move_left = buttonMove("⮜", true);
        JButton btn_move_right = buttonMove("⮞", true);
        JButton btn_move_center = buttonMove("◉", false);
        
        panel.add(btn_move_top, positionGrid(1, 0, 1, 1));
        panel.add(btn_move_bottom, positionGrid(1, 2, 1, 1));
        panel.add(btn_move_center, positionGrid(1, 1, 1, 1));
        panel.add(btn_move_left, positionGrid(0, 1, 1, 1));
        panel.add(btn_move_right, positionGrid(2, 1, 1, 1));

        return panel;
    }

    JButton buttonMove(String text, boolean is_active){
        JButton button = new JButton(text);
        
        button.setPreferredSize(new Dimension(40, 40));

        button.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        button.setFont(new Font("Times", Font.BOLD, 15));
        button.setBackground(Color.decode("#f6f6f6"));
        button.setFocusable(false);
        button.setEnabled(is_active);
        
        return button;
    }

    JPanel actions(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));

        JButton btn_dry = buttonAction("Assecher");
        JButton btn_get = buttonAction("Prendre");
        JButton btn_fly = buttonAction("Voler");
        JButton btn_next = buttonAction("Tour Suivant");
        
        panel.add(btn_dry, positionGrid(0, 0, 1, 1));
        panel.add(btn_get, positionGrid(1, 0, 1, 1));
        panel.add(btn_fly, positionGrid(2, 0, 1, 1));
        panel.add(btn_next, positionGrid(0, 1, 3, 2));

        return panel;
    }

    JButton buttonAction(String text){
        JButton button = new JButton(text);

        button.setPreferredSize(new Dimension(100, 50));
        button.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        button.setBackground(Color.decode("#f6f6f6"));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Times", Font.PLAIN, 15));
        button.setFocusable(false);
        
        return button;
    }

    GridBagConstraints positionGrid(int pos_x, int pos_y, int grid_width, int grid_height){
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = pos_x;
        gc.gridy = pos_y;
        gc.gridwidth = grid_width;
        gc.gridheight = grid_height;

        return gc;
    }
}