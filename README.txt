Projet POGL (2021-2022) : L'Ile interdite
Auteurs : Pradana AUMARS, Manitas BAHRI

(1/4) Parties du sujet traitées
-------------------------------
Nous avons traité toutes les parties obligatoires du sujet.

* La grille: on a implémenté l'ile comme une grille de zones (ou cases), avec chaque zone ayant un type terrain, soit MER, soit TERRE, soit HELIPORT. Ce type terrain détermine sa traversabilité et son inondabilité.
* Les objets: chaque objet a un type élément. Un Artefact est toujours visible dans la grille, par contre une Clef est d'abord invisible et devient visible dès qu'elle est cherchée. Chaque objet est instancié dès l'initialisation du jeu et reste dans une case spécifique pendant le jeu entier. On ne peut pas aussi supprimer un objet dans une case.
* Les joueurs: chaque joueur a un inventaire qui contient tous les objets qui le joueur possède. Ils meurent ssi les cases adjacentes sont submergées.
* Le jeu: Le jeu est gagné si tous les joueurs sont sur le helipad et tous les artefacts sont récupérés, il est perdu quand tous les joueurs sont morts.

Pour étendre l'application, nous avons choisi d'implémenter les actions spéciales.

(2/4) Choix d'architecture
--------------------------
Un diagramme de classe peut etre consulté dans classDiagram.png.

Pour modéliser la carte, on l'a codé dans un fichier texte et implémenté une classe Carte pour le lire et le traduire en données jeu. De meme, pour modéliser le jeu (la position de chaque objet et de chaque joueur), on l'a codé dans un fichier XML et implémenté une classe Jeu pour le lire. Ceci permet de changer facilement la carte que l'on veut utiliser et les objets que l'on veut, ainsi que leur position dans cette carte.

On a eu des difficultés en gagnant le jeu, donc on a ajouté aussi des niveaux de difficulté qui change le nombre de cases inondés en chaque tour. On voulait aussi choisir la difficulté au début, donc on a ajouté une fenêtre de début.

Pour l'interface graphique, on a principalement utilisé le souris (pour le mouvement et pour sélectionner des cases) et des boutons (pour des actions) pour rendre le jeu le plus agréable possible.

(3/4) Problèmes actuels
-------------------------
Aucun problème grave est présent.


(4/4) Morceaux de code empruntés
--------------------------------
La méthode Coord.hashCode est copié et collé.
