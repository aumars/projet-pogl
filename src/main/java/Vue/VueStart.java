package Vue;

import java.awt.Color;
import java.awt.*;
import javax.swing.*;

public class VueStart extends JPanel implements ContainerBoutonRadio {
    public int id_difficulte_active = 0;

    public VueBoutonRadio btn_facile = new VueBoutonRadio(0, id_difficulte_active, "Mode de difficulté: Facile",
            "Facile");
    public VueBoutonRadio btn_normal = new VueBoutonRadio(1, id_difficulte_active, "Mode de difficulté: Normal",
            "Normal");
    public VueBoutonRadio btn_difficile = new VueBoutonRadio(2, id_difficulte_active, "Mode de difficulté: Difficile",
            "Difficile");
    public VueBoutonRadio btn_deterministe = new VueBoutonRadio(3, id_difficulte_active, "Mode de difficulté: Deterministe",
            "Déterministe");
    public VueBouton btn_jouer = new VueBouton("Lance une nouvelle partie.", "Jouer");

    /**
     * Affiche un menu de démarrage.
     */
    public VueStart() {
        this.setPreferredSize(new Dimension(500, 400));
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        this.setLayout(new BorderLayout());
        JPanel panel_titre = new JPanel();
        panel_titre.setLayout(new GridBagLayout());

        JLabel titre = new JLabel("L'ile Interdite", SwingConstants.CENTER);
        titre.setBackground(Color.RED);
        titre.setFont(new Font(ConstsValue.FONT_FAMILY, Font.BOLD, 30));
        panel_titre.add(titre, Utils.positionneGrille(0, 0));

        JLabel punchline = new JLabel("Un lieu magique où chaque filet d'eau a son chemin...",
                SwingConstants.CENTER);
        punchline.setFont(new Font(ConstsValue.FONT_FAMILY, Font.ITALIC, 13));
        panel_titre.add(punchline, Utils.positionneGrille(0, 1));

        this.add(panel_titre, BorderLayout.PAGE_START);

        JPanel corps = new JPanel();
        corps.setLayout(new GridBagLayout());
        corps.add(this.sousTitre("Les règles du jeu"), Utils.positionneGrille(0, 0));

        JTextArea regle_area = new JTextArea(ConstsValue.TEXTE_REGLE);

        JScrollPane regle = new JScrollPane(regle_area);
        regle.setPreferredSize(new Dimension(480, 100));
        regle.setBorder(BorderFactory.createEmptyBorder());
        
        regle_area.setBackground(ConstsValue.COLOR_DEFAULT);
        regle_area.setFocusable(false);
        regle_area.setEditable(false);
        regle_area.setFont(new Font(ConstsValue.FONT_FAMILY, Font.PLAIN, 13));
        
        corps.add(regle, Utils.positionneGrille(0, 1));
        
        corps.add(this.sousTitre("La difficulté"), Utils.positionneGrille(0, 2));
        
        JPanel panel_btn_difficulte = new JPanel();
        panel_btn_difficulte.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 35));
        panel_btn_difficulte.add(this.btn_deterministe);
        panel_btn_difficulte.add(this.btn_facile);
        panel_btn_difficulte.add(this.btn_normal);
        panel_btn_difficulte.add(this.btn_difficile);

        corps.add(panel_btn_difficulte, Utils.positionneGrille(0, 3));

        this.add(corps, BorderLayout.LINE_START);

        this.add(this.btn_jouer, BorderLayout.PAGE_END);
    }

    /**
     * Créer un label pour le sous-titre.
     * 
     * @param text Le texte du sous-titre.
     * @return le label au format sous-titre.
     */
    private JLabel sousTitre(String text) {
        JLabel label = new JLabel(Utils.souligneLabel(text));
        label.setFont(new Font(ConstsValue.FONT_FAMILY, Font.PLAIN, 13));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        return label;
    }

    /**
     * Mets à jour les boutons radio.
     * 
     * @param id_pressed L'id du bouton radio actif.
     */
    public void metAJourRadioBouton(int id_pressed) {
        if (this.id_difficulte_active != id_pressed)
            this.id_difficulte_active = id_pressed;

        else
            this.id_difficulte_active = 0;

        this.btn_difficile.id_active = this.id_difficulte_active;
        this.btn_normal.id_active = this.id_difficulte_active;
        this.btn_facile.id_active = this.id_difficulte_active;
        this.btn_deterministe.id_active = this.id_difficulte_active;

        this.btn_difficile.update();
        this.btn_normal.update();
        this.btn_facile.update();
        this.btn_deterministe.update();

        this.revalidate();
    }

    /**
     * Réinitialise les boutons radio.
     */
    public void metAJourRadioBouton() {
        this.metAJourRadioBouton(0);
    }
}
