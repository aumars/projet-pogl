package Vue;

import java.awt.*;

/**
 * Un bouton réactif, avec la couleur
 */
public class VueBoutonRadio extends VueBouton {
    /**
     * Couleur actuelle du bouton
     */
    private final Color COLOR_ACTIVE = new Color(194, 255, 161);

    /**
     * L'ID du bouton
     */
    private final int ID;

    /**
     * Marqueur pour dire si l'ID est active.
     */
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

        this.ID = id;
        this.id_active = id_active;
        this.update();
    }

    /**
     * Renvoie si le bouton est activé.
     * 
     * @return True si le bouton est activé. False sinon.
     */
    public boolean estActive() {
        return this.ID == this.id_active;
    }

    /**
     * Récupère l'ID du bouton.
     * 
     * @return l'ID du bouton.
     */
    public int getID() {
        return this.ID;
    }

    /**
     * Mets a jours l'affichage du bouton radio.
     */
    public void update() {
        if (this.estActive())
            this.setBackground(this.COLOR_ACTIVE);

        else
            this.setBackground(ConstsValue.COLOR_DEFAULT);
    }
}
