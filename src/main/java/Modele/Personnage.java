package Modele;

class Personnage {
    private boolean estVivant = true;
    public final Coord coord;

    public Personnage(int x, int y) {
        this.coord = new Coord(x, y);
    }

    public Personnage(Coord c) {
        this.coord = c;
    }
    public static Personnage personnageById(String name, Coord c) {
        if (name.equals("joueur")) {
            return new Joueur(c);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void meurt() {
        this.estVivant = false;
    }
}
