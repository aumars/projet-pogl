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
    public int posX() { return this.positionX; }
    public int posY() { return this.positionY; }
    void deplace(Direction dir) {
        switch (dir) {
            case HAUT: this.positionX--;
            case BAS: this.positionX++;
            case GAUCHE: this.positionY--;
            case DROITE: this.positionY++;
        }
    }
}
