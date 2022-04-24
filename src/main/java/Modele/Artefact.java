package Modele;

/**
 * L'artefact associé à un élément.
 */
public class Artefact extends Objet {
    /**
     * Construit un artefact associé à un élément.
     * @param el Un élément
     */
    public Artefact(Element el) {
        super(el);
    }

    /**
     * @return Une chaine de caractère de l'artefact
     */
    @Override
    public String toString() {
        return String.format("Artefact (%s)", this.ELEMENT);
    }
}
