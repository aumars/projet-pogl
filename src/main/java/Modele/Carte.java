package Modele;

import java.util.Objects;
import java.util.Scanner;

/**
 * La modèle de la carte du jeu. Cette classe sert à traduire un fichier texte
 * modélisant une carte en données de jeu.
 */
public final class Carte {
    /**
     * Tableau de tableaux de {@link Terrain} qui modélise la carte.
     */
    public final Terrain[][] map;

    /**
     * Lit un fichier texte et le traduit.
     * 
     * @param map_path Chemin de fichier de la carte.
     */
    Carte(String map_path) {
        Scanner map_reader = new Scanner(
                Objects.requireNonNull(Modele.class.getClassLoader().getResourceAsStream(map_path)));
        int hauteur = map_reader.nextInt();
        int largeur = map_reader.nextInt();
        map_reader.skip("\n");
        this.map = new Terrain[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            String line = map_reader.nextLine();
            for (int j = 0; j < largeur; j++) {
                this.map[i][j] = terrainByID(line.charAt(j));
            }
        }
    }

    /**
     * Associe un {@link Terrain} par sa chaine de caractères associée.
     * 
     * @param c Une chaine de caractères.
     * @return Son {@link Terrain} associé.
     */
    private static Terrain terrainByID(char c) {
        switch (c) {
            case '-':
                return Terrain.MER;
            case '*':
                return Terrain.TERRE;
            case 'h':
                return Terrain.HELIPAD;
            default:
                throw new IllegalArgumentException(
                        String.format("Le caractère %s n'est pas reconnu comme un terrain.", c));
        }
    }
}
