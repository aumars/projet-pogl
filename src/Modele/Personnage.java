package Modele;

class Personnage {
    private boolean estVivant = true;
    private int positionX;
    private int positionY;

    public Personnage(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    public void meurt() {
        this.estVivant = false;
    }

}
