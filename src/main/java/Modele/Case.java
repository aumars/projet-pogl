package Modele;

public class Case {
    public final Coord coord;
    public final Terrain terrain;
    public final boolean helipad;
    private Inondation etat = Inondation.SECHE;
    private Objet objet;
    private boolean objetVisibilite = false;
    private static Grille grille;

    public Case(int i, int j, char c, Grille g) {
        if (i < 0 || j < 0) {
            throw new IllegalArgumentException(String.format("(i, j) = (%d, %d) doit avoir des composantes positives.", i, j));
        }
        this.coord = new Coord(j, i);
        grille = g;
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
        this.objetVisibilite =  !this.objet.getClass().equals(Clef.class);
    }

    public boolean aObjet() { return this.objet != null; }

    public boolean aObjet(Class c) { return this.objet != null && this.objet.getClass().equals(c); }

    public Objet getObjet() { return this.objet; }

    public boolean getObjetVisibilite() { return this.objetVisibilite; }

    public void setObjetVisibilite(boolean b) { this.objetVisibilite = b; }

    public boolean detruitObjet() {
        if (this.objet != null) {
            this.objet = null;
            return true;
        }
        return false;
    }

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

    public Case adjacent(Direction dir) {
        int x = this.coord.x();
        int y = this.coord.y();
        switch (dir) {
            case HAUT: return grille.getCase(x, y - 1);
            case BAS: return grille.getCase(x, y + 1);
            case GAUCHE: return grille.getCase(x - 1, y);
            case DROITE: return grille.getCase(x + 1, y);
            case NEUTRE:
            default: return this;
        }
    }

    public boolean adjacentSubmergee() {
        return !adjacent(Direction.HAUT).estTraversable()
                && !adjacent(Direction.BAS).estTraversable()
                && !adjacent(Direction.GAUCHE).estTraversable()
                && !adjacent(Direction.DROITE).estTraversable()
                && !adjacent(Direction.NEUTRE).estTraversable();
    }
}
