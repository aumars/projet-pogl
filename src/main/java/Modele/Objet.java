package Modele;

/**
 * Objet associé à un élément, récupérable par un joueur et visible dans la grille.
 */
public abstract class Objet {
    /**
     * Element auquel l'objet est associé.
     */
    public final Element element;

    /**
     * Construit un objet associé à un élément.
     * @param el Element de l'objet
     */
    public Objet(Element el) {
        this.element = el;
    }
}
