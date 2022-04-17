package Vue;

import Modele.Modele;
import javax.swing.*;
import java.awt.*;

public class VueInfoHaut extends JPanel {
    private Modele modele;

    private JLabel label_tour;
    public VueBouton btn_aide = new VueBouton("Affiche l'écran d'aide", "Besoin d'aide ?");

    public VueInfoHaut(Modele m) {
        this.modele = m;
        
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        label_tour = new JLabel(this.getTextTitre(), SwingConstants.CENTER);
        label_tour.setFont(new Font(ConstsValue.FONT_FAMILY, Font.BOLD, 25));
        this.add(this.label_tour, BorderLayout.CENTER);
        
        this.add(this.btn_aide, BorderLayout.LINE_END);
    }

    public void metAJourApresAction(){
        super.repaint();
        this.label_tour.setText(this.getTextTitre());
    }

    private String getTextTitre(){
        return "Tour n°" + this.modele.getTour() + " - Player" + this.modele.getJoueurActuel().id;
    }
}
