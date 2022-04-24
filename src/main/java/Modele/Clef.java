package Modele;

/**
 * La clef associée à un artefact (qui est, par transitivité, associée à un élément).
 */
public class Clef extends Objet {
    /**
     * Construit une clef associée à un élément.
     * @param el Un élément
     */
    public Clef(Element el) {
        super(el);
    }

    /**
     * @return Une chaine de caractère de la clef
     */
    @Override
    public String toString() {
        return String.format("Clef (%s)", this.ELEMENT);
    }
}
