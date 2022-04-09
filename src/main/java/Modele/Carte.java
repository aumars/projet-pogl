package Modele;

import java.util.Objects;
import java.util.Scanner;

public final class Carte {
    public final char[][] map;
    Carte(String map_path) {
        Scanner map_reader = new Scanner(Objects.requireNonNull(Modele.class.getClassLoader().getResourceAsStream(map_path)));
        int hauteur = map_reader.nextInt();
        int largeur = map_reader.nextInt();
        map_reader.skip("\n");
        this.map = new char[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            String line = map_reader.nextLine();
            for (int j = 0; j < largeur; j++) {
                this.map[i][j] = line.charAt(j);
            }
        }
    }
}
