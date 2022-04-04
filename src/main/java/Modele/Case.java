package Modele;

public class Case {
    private final Coord coord;
    public final Terrain terrain;
    public final boolean helipad;
    private Inondation etat = Inondation.SECHE;
    private Objet objet;

    public Case(int i, int j, char c) {
        if (i < 0 || j < 0) {
            throw new IllegalArgumentException(String.format("(i, j) = (%d, %d) doit avoir des composantes positives.", i, j));
        }
        this.coord = new Coord(i, j);
        switch (c) {
            case '-': this.terrain = Terrain.MER; this.helipad = false; break;
            case '*': this.terrain = Terrain.TERRE; this.helipad = false; break;
            case 'h': this.terrain = Terrain.TERRE; this.helipad = true; break;
            default: throw new IllegalArgumentException(String.format("Le caractère %s n'est pas reconnu.", c));
        }
    }

    public boolean estTraversable() {
        return this.terrain == Terrain.TERRE && this.etat != Inondation.SUBMERGEE;
    }

    void ajoutObjet(Objet o) {
        this.objet = o;
    }

    public boolean aObjet() { return this.objet != null; }

    public boolean aObjet(Class c) { return this.objet.getClass() == c; }

    public Objet getObjet() { return this.objet; }

    public Inondation getEtat() { return this.etat; }

    public boolean asseche() {
        switch (this.getEtat()) {
            case SECHE: return true;
            case INONDEE: this.etat = Inondation.SECHE; return true;
            case SUBMERGEE:
            default: return false;
        }
    }

    public void monteEaux() {
        switch (this.getEtat()) {
            case SECHE: this.etat = Inondation.INONDEE; break;
            case INONDEE: this.etat = Inondation.SUBMERGEE; break;
        }
    }
}
