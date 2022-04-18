package Vue;

import java.awt.*;

public class VueBoutonRadio extends VueBouton {
    private Color color_active = new Color(194, 255, 161);

    private int id;
    public int id_active = -1;

    public VueBoutonRadio(int id, int id_active, String tooltip, String text) {
        super(tooltip, text);

        if (id == -1)
            throw new IllegalArgumentException("L'id du radio bouton doit être differents de -1");

        this.id = id;
        this.id_active = id_active;
    }

    public boolean estActive() {
        return this.id == this.id_active;
    }

    public int getId() {
        return this.id;
    }

    public void update() {
        if (this.estActive())
            this.setBackground(this.color_active);

        else
            this.setBackground(ConstsValue.COLOR_DEFAULT);
    }
}
