package Vue;

import javax.swing.*;

import Modele.Modele;

import java.awt.*;


public class VueEndGame extends JPanel {
    private Modele modele;

    public VueEndGame(Modele m, boolean partie_perdante){
        this.modele = m;

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(Constants.BOX_SIZE * this.modele.getGrille().getWidth() * 3/7, 100));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel panel_text = new JPanel();
        panel_text.setLayout(new BorderLayout());

        JLabel lbl_title = new JLabel("", SwingConstants.CENTER);    
        JLabel lbl_text = new JLabel("", SwingConstants.CENTER);    
        
        if (partie_perdante) {
            lbl_title.setText("GAME OVER...");
            lbl_title.setFont(new Font("", Font.BOLD, 20));
            
            lbl_text.setText("La défaite n'est pas de perdre mais, d'être mauvais perdant.");
            lbl_text.setFont(new Font("", Font.ITALIC, 12));
        }

        else{
            lbl_title.setText("BRAVO " + this.modele.getJoueurActuel().nom);
            lbl_title.setFont(new Font("", Font.BOLD, 20));
            
            lbl_text.setText("Deux choses comptent : gagner et s'amuser. Gagner sans s'amuser n'a aucun intérêt.");
            lbl_text.setFont(new Font("", Font.ITALIC, 12));
        }
        
        panel_text.add(lbl_title, BorderLayout.PAGE_START);
        panel_text.add(lbl_text, BorderLayout.CENTER);

        VueBouton new_game = new VueBouton("Lance une nouvelle partie", "Rejouer");
        new_game.setEnabled(false);
        panel_text.add(new_game, BorderLayout.LINE_END);
        
        this.add(panel_text, BorderLayout.CENTER);
    }
}
