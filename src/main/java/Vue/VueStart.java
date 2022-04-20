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
    public VueBouton btn_jouer = new VueBouton("Lance une nouvelle partie.", "Jouer");

    public VueStart() {
        this.setPreferredSize(new Dimension(500, 400));
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        this.setLayout(new BorderLayout());
        this.affichePanelTitre();
        this.affichePanelCorps();
        this.add(this.btn_jouer, BorderLayout.PAGE_END);
    }

    private JLabel sousTitre(String text) {
        JLabel label = new JLabel(Utils.souligneLabel(text));
        label.setFont(new Font(ConstsValue.FONT_FAMILY, Font.PLAIN, 13));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        return label;
    }

    private void affichePanelTitre() {
        JPanel panel_titre = new JPanel();
        panel_titre.setLayout(new GridBagLayout());

        JLabel titre = new JLabel("L'ile Interdite", SwingConstants.CENTER);
        titre.setBackground(Color.RED);
        titre.setFont(new Font(ConstsValue.FONT_FAMILY, Font.BOLD, 30));
        panel_titre.add(titre, Utils.positionneGrille(0, 0));

        JLabel punchline = new JLabel("Là y aura une petite phrase, je sais pas trop quoi encore...",
                SwingConstants.CENTER);
        punchline.setFont(new Font(ConstsValue.FONT_FAMILY, Font.ITALIC, 13));
        panel_titre.add(punchline, Utils.positionneGrille(0, 1));

        this.add(panel_titre, BorderLayout.PAGE_START);
    }

    private void affichePanelCorps() {
        JPanel corps = new JPanel();
        corps.setLayout(new GridBagLayout());
        corps.add(this.sousTitre("Les règles du jeu"), Utils.positionneGrille(0, 0));

        JTextArea regle_area = new JTextArea("Alors la tu fais ça, y a ca qui se passe. ");

        JScrollPane regle = new JScrollPane(regle_area);
        regle.setPreferredSize(new Dimension(480, 50));
        regle.setBorder(BorderFactory.createEmptyBorder());

        regle_area.setBackground(ConstsValue.COLOR_DEFAULT);
        regle_area.setFocusable(false);
        regle_area.setEditable(false);
        regle_area.setFont(new Font(ConstsValue.FONT_FAMILY, Font.PLAIN, 13));

        corps.add(regle, Utils.positionneGrille(0, 1));

        corps.add(this.sousTitre("La difficultée"), Utils.positionneGrille(0, 2));

        JPanel panel_btn_difficulte = new JPanel();
        panel_btn_difficulte.add(this.btn_facile);
        panel_btn_difficulte.add(this.btn_normal);
        panel_btn_difficulte.add(this.btn_difficile);

        corps.add(panel_btn_difficulte, Utils.positionneGrille(0, 3));

        this.add(corps, BorderLayout.LINE_START);
    }

    public void metAJourRadioBouton(int id_pressed) {
        if (this.id_difficulte_active != id_pressed)
            this.id_difficulte_active = id_pressed;

        else
            this.id_difficulte_active = 0;

        this.btn_difficile.id_active = this.id_difficulte_active;
        this.btn_normal.id_active = this.id_difficulte_active;
        this.btn_facile.id_active = this.id_difficulte_active;

        this.btn_difficile.update();
        this.btn_normal.update();
        this.btn_facile.update();

        this.revalidate();
    }

    public void metAJourRadioBouton() {
        this.metAJourRadioBouton(0);
    }
}
