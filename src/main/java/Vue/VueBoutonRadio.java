package Vue;

import java.awt.*;

public class VueBoutonRadio extends VueBouton {
    private Color color_active = new Color(194, 255, 161);

    private int id;
    public int id_active = -1;

    /**
     * Affiche un bouton radio.
     * 
     * @param id        L'ID du bouton.
     * @param id_active L'ID du bouton pressée.
     * @param tooltip   Le texte à afficher lorsque la souris survole le bouton.
     * @param text      Le texte du bouton.
     * 
     * @exception IllegalArgumentException Si l'ID du bouton est egal à 1.
     */
    public VueBoutonRadio(int id, int id_active, String tooltip, String text) {
        super(tooltip, text);

        if (id == -1)
            throw new IllegalArgumentException("L'id du radio bouton doit être differents de -1");

        this.id = id;
        this.id_active = id_active;
    }

    /**
     * Renvoie si le bouton est activé.
     * 
     * @return True si le bouton est activé. False sinon.
     */
    public boolean estActive() {
        return this.id == this.id_active;
    }

    /**
     * Récupère l'ID du bouton.
     * 
     * @return l'ID du bouton.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Mets a jours l'affichage du bouton radio.
     */
    public void update() {
        if (this.estActive())
            this.setBackground(this.color_active);

        else
            this.setBackground(ConstsValue.COLOR_DEFAULT);
    }
}
