package Vue;

import Modele.*;
import java.awt.*;
import javax.swing.*;

public class VueCommande extends JPanel {
    private Modele modele;
    public int id_radio_active = -1;

    public VueBouton btn_clef = new VueBouton("Recherche une clef autour [A]", "Clef");
    public VueBouton btn_prendre = new VueBouton("Récupère l'artefact [F]", "Artefact");
    public VueBoutonRadio btn_secher = new VueBoutonRadio(0, id_radio_active, "Récupère l'artefact [F]",
            "Sécher une case");

    public VueBouton btn_fin_tour = new VueBouton("Termine le tour [ENTER]", "Fin");

    public VueBoutonRadio btn_sac_sable = new VueBoutonRadio(1, id_radio_active, "Ensable une case", "Ensabler");
    public VueBoutonRadio btn_teleporte = new VueBoutonRadio(2, id_radio_active, "Se teleporter", "Téléporter");

    public VueCommande(Modele m) {
        this.modele = m;

        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Affiche le panel des actions.
        JPanel panel_actions = new JPanel();
        panel_actions.setLayout(new GridBagLayout());
        panel_actions.add(titre("Les actions :"), Utils.positionneGrille(0, 0, 2, 1, 0));
        panel_actions.add(this.btn_prendre, Utils.positionneGrille(0, 1, 3));
        panel_actions.add(this.btn_clef, Utils.positionneGrille(1, 1, 3));
        panel_actions.add(this.btn_secher, Utils.positionneGrille(2, 1, 3));
        panel_actions.add(this.btn_fin_tour, Utils.positionneGrille(3, 1, 3));
        this.add(panel_actions);

        // Affiche le panel des pouvoirs.
        JPanel panel_pouvoirs = new JPanel();
        panel_pouvoirs.setLayout(new GridBagLayout());
        panel_pouvoirs.add(titre("Les pouvoirs :"), Utils.positionneGrille(0, 0, 2, 1, 0));
        panel_pouvoirs.add(this.btn_sac_sable, Utils.positionneGrille(0, 1, 3));
        panel_pouvoirs.add(this.btn_teleporte, Utils.positionneGrille(1, 1, 3));
        this.add(panel_pouvoirs);

        this.gereVisibiliteBoutons();
    }

    public void gereVisibiliteBoutons() {
        boolean peut_jouer = this.modele.getJoueurActuel().estSonTour();
        
        this.btn_secher.setEnabled(this.estAdjacentJoueurInondee());
        this.btn_clef.setEnabled(peut_jouer);
        this.btn_prendre.setEnabled(peut_jouer && estCaseArtefact());
        this.btn_fin_tour.setEnabled(this.modele.tourPeutFinir());

        this.btn_sac_sable.setEnabled(this.modele.getJoueurActuel().aActionSpeciale());
        this.btn_teleporte.setEnabled(this.modele.getJoueurActuel().aActionSpeciale());

        this.revalidate();
        this.repaint();
    }

    private boolean estCaseArtefact() {
        return this.modele.getGrille().getCase(this.modele.getJoueurActuel().getCoord()).aObjet(Artefact.class);
    }

    private boolean estAdjacentJoueurInondee() {
        for (Direction dir : Direction.values()) {
            Coord coord_joueurs = this.modele.getJoueurActuel().getCoord();
            Case case_joueurs = this.modele.getGrille().getCase(coord_joueurs).adjacent(dir);
            
            if(case_joueurs.getEtat() == Inondation.INONDEE)
                return true;
        }

        return false;
    }

    private JLabel titre(String text) {
        JLabel titre = new JLabel(Utils.souligneLabel(text));
        titre.setFont(new Font(ConstsValue.FONT_FAMILY, Font.BOLD, 17));
        return titre;
    }

    public void metAJourRadioBouton(int id_pressed) {
        if (this.id_radio_active != id_pressed)
            this.id_radio_active = id_pressed;

        else
            this.id_radio_active = -1;

        this.btn_secher.id_active = this.id_radio_active;
        this.btn_sac_sable.id_active = this.id_radio_active;
        this.btn_teleporte.id_active = this.id_radio_active;

        this.btn_secher.update();
        this.btn_sac_sable.update();
        this.btn_teleporte.update();
    }

    public void metAJourRadioBouton(){
        this.metAJourRadioBouton(-1);
    }
}