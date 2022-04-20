package Modele;

/**
 * La clef associée à un artefact (qui est, par transitivité, associée à un
 * élément).
 */
public class Clef extends Objet {
    public Clef(Element el) {
        super(el);
    }

    @Override
    public String toString() {
        return String.format("Clef (%s)", this.element);
    }
}
