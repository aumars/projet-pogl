package Modele;

class Personnage {
    private boolean estVivant = true;
    public final Coord coord;

    public Personnage(int x, int y) {
        this.coord = new Coord(x, y);
    }

    public void meurt() {
        this.estVivant = false;
    }
}
