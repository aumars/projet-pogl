package Vue;

import Modele.Modele;

import javax.swing.*;
import java.awt.*;

public class VueGameInfo extends JPanel {
    private Modele modele;
    private JLabel label_round;
    public VueBouton btn_help = new VueBouton("Affiche l'écran d'aide", "Besoin d'aide ?");

    public VueGameInfo(Modele m) {
        this.modele = m;
        
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        String title = "Tour n°" + this.modele.getTour() + " - " + this.modele.getJoueurActuel().nom;
        label_round = new JLabel(title, SwingConstants.CENTER);
        label_round.setFont(new Font("Calibri", Font.BOLD, 25));
        this.add(this.label_round, BorderLayout.CENTER);
        
        this.add(this.btn_help, BorderLayout.LINE_END);
    }

    public void update(){
        super.repaint();
        String title = "Tour n°" + this.modele.getTour() + " - " + this.modele.getJoueurActuel().nom;
        this.label_round.setText(title);
    }
}
