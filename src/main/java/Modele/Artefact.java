package Modele;

/**
 * L'artefact associé à un élément.
 */
public class Artefact extends Objet {
    public Artefact(Element el) {
        super(el);
    }

    @Override
    public String toString() {
        return String.format("Artefact (%s)", this.element);
    }
}
