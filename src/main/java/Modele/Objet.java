package Modele;

abstract class Objet {
    public final Element element;
    public Objet(Element el) {
        this.element = el;
    }
    public static Objet objetByID(String id, Element e) {
        Objet o;
        if (id.equals("artefact")) {
            o = new Artefact(e);
        }
        else if (id.equals("clef")) {
            o = new Clef(e);
        }
        else {
            throw new IllegalArgumentException(String.format("%s n'est pas un objet reconnu.", id));
        }
        return o;
    }
}
